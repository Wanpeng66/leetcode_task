package com.wp;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_133  133. 克隆图
 * @Description: https://leetcode-cn.com/problems/clone-graph/
 * @date 2022/2/17 9:51
 */
public class task_133 {

    public Node cloneGraph(Node node) {
        if(null==node){
            return null;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        boolean[] check = new boolean[101];
        Queue<Node> queue = new LinkedList<>();
        queue.offer( node );
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (check[poll.val]) {
                    continue;
                }
                List<Node> neighbors = poll.neighbors;
                Set<Integer> set = map.getOrDefault( poll.val, new HashSet<>() );
                set.addAll( neighbors.stream().mapToInt( value -> value.val ).boxed().collect( Collectors.toSet()) );
                map.put( poll.val, set );
                check[poll.val] = true;
                for (Node neighbor : neighbors) {
                    queue.offer( neighbor );
                }
            }
        }
        Node[] nodes = new Node[101];
        for (Integer key : map.keySet()) {
            nodes[key] = new Node(key);
        }
        for (Integer key : map.keySet()) {
            Set<Integer> set = map.get( key );
            Node currentNode = nodes[key];
            List<Node> neighbors = currentNode.neighbors;
            for (Integer val : set) {
                neighbors.add( nodes[val] );
            }
        }
        return nodes[1];
    }
}
