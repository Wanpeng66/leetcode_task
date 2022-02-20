package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: Node
 * @Description:
 * @date 2022/2/17 9:52
 */
public class Node {
    public int val;
    public List<Node> neighbors;
    public Node left;
    public Node right;
    public Node next;


    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node  array2graph(int[][] arrays){
        int max = arrays.length;
        Node[] nodes = new Node[max+1];
        for (int i = 1; i < max; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 1; i < arrays.length; i++) {
            int[] array = arrays[i];
            Node currentNode = nodes[i];
            for (int k : array) {
                currentNode.neighbors.add( nodes[k] );
            }
        }
        return nodes[1];
    }
}
