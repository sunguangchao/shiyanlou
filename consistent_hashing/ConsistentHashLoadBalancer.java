package consistent_hashing;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 1:01 PM
 * 一致性哈希
 */
public class ConsistentHashLoadBalancer implements LoadBalancer{
    private HashStrategy hashStrategy = new FnvHashStrategy();

    /**
     * 虚拟节点的个数
     */
    private static final int VIRTUAL_NODE_SIZE = 10;

    private static final String VIRTUAL_NODE_SUFFIX  = "&&";

    @Override
    public Server select(List<Server> servers, Invocation invocation) {
        int invocationHashCode = hashStrategy.getHashCode(invocation.getHashKey());
        TreeMap<Integer, Server> ring = buildConsistentHashing(servers);
        Server server = locate(ring, invocationHashCode);
        return server;
    }

    private Server locate(TreeMap<Integer, Server> ring, int invocationHashCode){
        //ceilingEntry:返回与该建至少大于等于给定建，如果不存在这样的键，返回null
        //向右找第一个key
        Map.Entry<Integer, Server> locateEntry = ring.ceilingEntry(invocationHashCode);
        //Returns the first Entry in the TreeMap
        //想象成一个环，如果是尾部则取第一个key
        if (locateEntry == null){
            locateEntry = ring.firstEntry();
        }
        return locateEntry.getValue();
    }


    private TreeMap<Integer, Server> buildConsistentHashing(List<Server> servers){
        TreeMap<Integer, Server> virtualNodeRing = new TreeMap<Integer, Server>();
        //每有一个实际的server节点，新增10个虚拟节点
        for (Server server : servers){
            for (int i = 0; i < VIRTUAL_NODE_SIZE; i++){
                virtualNodeRing.put(hashStrategy.getHashCode(server + VIRTUAL_NODE_SUFFIX + i), server);
            }
        }
        return virtualNodeRing;
    }
}
