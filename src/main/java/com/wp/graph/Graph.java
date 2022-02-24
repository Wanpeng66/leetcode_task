package com.wp.graph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author: wp
 * @Title: Graph
 * @Description:  图以及Dijkstra算法
 * @date 2022/2/24 14:40
 */
public class Graph {
    LinkedList<Edge>[] adj;
    int total;

    public Graph( int total ) {
        this.total = total;
        adj = new LinkedList[total];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    private class Edge{
        Integer fid;
        Integer tid;
        Integer weight;

        public Edge( Integer fid, Integer tid, Integer weight ) {
            this.fid = fid;
            this.tid = tid;
            this.weight = weight;
        }
    }

    public void addEdge(int fid, int tid, int weight){
        adj[fid].add( new Edge( fid, tid, weight ) );
    }

    private class Vertex{
        public Integer id;
        public Integer dist;

        public Vertex( Integer id, Integer dist ) {
            this.id = id;
            this.dist = dist;
        }
    }

    private class PriorityQueue{
        java.util.PriorityQueue<Vertex> queue;
        int count;

        public PriorityQueue( int count ) {
            this.count = count;
            queue = new java.util.PriorityQueue<>( count, ( o1, o2 ) -> o1.dist-o2.dist );
        }

        public Vertex poll(){
            return queue.poll();
        }

        public void add(Vertex vertex){
            queue.add( vertex );
        }

        public void update(Vertex vertex){
            Iterator<Vertex> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Vertex next = iterator.next();
                if (next.id == vertex.id) {
                    next.dist = vertex.dist;
                    break;
                }
            }
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public void dijkstra(int s, int t) {
        int[] predecessor = new int[this.total]; // 用来还原最短路径
        Vertex[] vertices = new Vertex[this.total];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex( i,Integer.MAX_VALUE );
        }
        vertices[s].dist = 0;

        PriorityQueue queue = new PriorityQueue( this.total );
        boolean[] inQueue = new boolean[this.total];
        queue.add( vertices[s] );
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            if (poll.id==t) {
                break;
            }
            LinkedList<Edge> edges = adj[poll.id];
            for (Edge edge : edges) {
                Vertex next = vertices[edge.tid];
                int tmp = poll.dist + edge.weight;
                if (tmp< next.dist) {
                    next.dist = tmp;
                    predecessor[next.id] = poll.id;
                    if (inQueue[next.id]) {
                        queue.update( next );
                    }else{
                        queue.add( next );
                        inQueue[next.id] = true;
                    }
                }
            }
        }
        System.out.print(s);
        print( s,t,predecessor );
    }

    private void print(int s, int t, int[] predecessor) {
        if (s==t) {
            return;
        }
        print( s, predecessor[t], predecessor );
        System.out.print("->"+t);
    }

    public static void main( String[] args ) {
        Graph graph = new Graph( 6 );
        graph.addEdge( 0,1,10 );
        graph.addEdge( 0,4,15 );
        graph.addEdge( 1,2,15 );
        graph.addEdge( 1,3,2 );
        graph.addEdge( 2,5,5 );
        graph.addEdge( 3,2,1 );
        graph.addEdge( 3,5,12 );
        graph.addEdge( 4,5,10 );
        graph.dijkstra( 0,5 );
    }
}
