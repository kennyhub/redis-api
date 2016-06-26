package org.top.redis.pipeline;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.JedisClusterCRC16;
import redis.clients.util.SafeEncoder;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yubin on 16/5/25.
 */
public class RedisPipeline extends Pipeline {

    protected JedisCluster cluster = null;
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    private Queue<Client> clients = new LinkedList<Client>();
    private Map<Integer, String> slotsAndNodes = new HashMap<Integer, String>();
    private Map<String, Client> clusterClients = new HashMap<String, Client>();

    private static final int MASTER_NODE_INDEX = 2;

    protected void setJedisCluster(JedisCluster cluster) {
        this.cluster = cluster;
        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();
        Iterator<Map.Entry<String, JedisPool>> clusterNodesIte = clusterNodes.entrySet().iterator();
        boolean is_discoved = false;
        while (clusterNodesIte.hasNext()) {
            Map.Entry<String, JedisPool> iterator = clusterNodesIte.next();
            if (iterator == null || iterator.getValue() == null) {
                continue;
            }
            Jedis jedis = null;
            try {
                jedis = iterator.getValue().getResource();
                clusterClients.put(iterator.getKey(), jedis.getClient());
                if (!is_discoved) {
                    discoverClusterNodesAndSlots(jedis);
                    is_discoved = true;
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                releaseConnection(jedis);
            }
        }
    }

    @Override
    public void setClient(Client client) {
        clients.add(client);
        super.setClient(client);
    }

    @Override
    protected Client getClient(byte[] key) {
        if (cluster == null) {
            return super.getClient(key);
        } else {
            String node = slotsAndNodes.get(JedisClusterCRC16.getSlot(key));
            client = clusterClients.get(node);
            clients.add(client);
            return client;
        }
    }

    @Override
    protected Client getClient(String key) {
        return this.getClient(key.getBytes());
    }


    private List<FutureResult> results = new ArrayList<FutureResult>();

    private static class FutureResult {
        private Client client;

        public FutureResult(Client client) {
            this.client = client;
        }

        public Object get() {
            return client.getOne();
        }
    }

    public List<Object> getResults() {
        List<Object> r = new ArrayList<Object>();
        for (FutureResult fr : results) {
            r.add(fr.get());
        }
        return r;
    }

    /**
     * Syncronize pipeline by reading all responses. This operation closes the pipeline. In order to
     * get return values from pipelined commands, capture the different Response&lt;?&gt; of the
     * commands you execute.
     */
    public void sync() {
        if (cluster == null) {
            super.sync();
            return;
        }
        for (Client client : clients) {
            try {
                generateResponse(client.getOne());
            } catch (Exception e) {

            } finally {
                releaseConnection(client);
            }
        }
    }

    /**
     * Syncronize pipeline by reading all responses. This operation closes the pipeline. Whenever
     * possible try to avoid using this version and use ShardedJedisPipeline.sync() as it won't go
     * through all the responses and generate the right response type (usually it is a waste of time).
     *
     * @return A list of all the responses in the order you executed them.
     */
    public List<Object> syncAndReturnAll() {
        if (cluster == null) return super.syncAndReturnAll();
        List<Object> formatteds = new ArrayList<Object>();
        for (Client client : clients) {
            try {
                formatteds.add(generateResponse(client.getOne()).get());
            } catch (Exception e) {

            } finally {
                releaseConnection(client);
            }
        }
        return formatteds;
    }


    public void discoverClusterNodesAndSlots(Jedis jedis) {
        w.lock();

        try {
            this.slotsAndNodes.clear();

            List<Object> slots = jedis.clusterSlots();

            for (Object slotInfoObj : slots) {
                List<Object> slotInfo = (List<Object>) slotInfoObj;

                if (slotInfo.size() <= MASTER_NODE_INDEX) {
                    continue;
                }

                List<Integer> slotNums = getAssignedSlotArray(slotInfo);

                // hostInfos
                int size = slotInfo.size();
                for (int i = MASTER_NODE_INDEX; i < size; i++) {
                    List<Object> hostInfos = (List<Object>) slotInfo.get(i);
                    if (hostInfos.size() <= 0) {
                        continue;
                    }

                    HostAndPort targetNode = generateHostAndPort(hostInfos);
                    if (i == MASTER_NODE_INDEX) {
                        for (Integer slot : slotNums) {
                            slotsAndNodes.put(slot, JedisClusterInfoCache.getNodeKey(targetNode));
                        }
                    }
                }
            }
        } finally {
            w.unlock();
        }
    }

    private List<Integer> getAssignedSlotArray(List<Object> slotInfo) {
        List<Integer> slotNums = new ArrayList<Integer>();
        for (int slot = ((Long) slotInfo.get(0)).intValue(); slot <= ((Long) slotInfo.get(1))
                .intValue(); slot++) {
            slotNums.add(slot);
        }
        return slotNums;
    }

    private HostAndPort generateHostAndPort(List<Object> hostInfos) {
        return new HostAndPort(SafeEncoder.encode((byte[]) hostInfos.get(0)),
                ((Long) hostInfos.get(1)).intValue());
    }

    private void releaseConnection(Jedis connection) {
        if (connection != null) {
            connection.close();
        }
    }

    private void releaseConnection(Client connection) {
        if (connection != null) {
            connection.close();
        }
    }
}