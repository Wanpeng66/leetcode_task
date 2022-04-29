package com.wp;

/**
 * @author: wp
 * @Title: task_427  427. 建立四叉树
 * @Description: https://leetcode-cn.com/problems/construct-quad-tree/
 * @date 2022/4/29 12:37
 */
public class task_427 {

    public static void main( String[] args ) {
        int[][] grid = {{0,1},{1,0}};
        Node node = construct( grid );
        System.out.println( node );
    }
    public static Node construct(int[][] grid) {
        int x = grid.length-1;
        int y = grid[0].length-1;
        return construct( grid, 0, 0, x, y );
    }

    private static Node construct( int[][] grid, int xs, int ys, int x, int y ) {
        boolean equals = true;
        int val = -1;
        for (int i = xs; i <= x; i++) {
            for (int j = ys; j <= y-1; j++) {
                if (grid[i][j]!=grid[i][j+1]) {
                    equals = false;
                    break;
                }
            }
            if (val==-1) {
                val = grid[i][ys];
            }
            if (!equals ) {
                break;
            }else if (val!=grid[i][ys]) {
                equals = false;
                break;
            }
            val = grid[i][ys];
        }
        Node node = new Node();
        //如果格子值都相等
        if (equals) {
            node.val = val != 0;
            node.isLeaf = true;
        }else{
            node.val = true;
            node.isLeaf = false;
            int xHalf = ( xs + x ) / 2;
            int yHalf = ( ys + y ) / 2;
            node.topLeft = construct( grid, xs, ys, xHalf, yHalf );
            node.topRight = construct( grid, xs, yHalf+1, xHalf, y );
            node.bottomLeft = construct( grid, xHalf+1, ys, x, yHalf );
            node.bottomRight = construct( grid, xHalf+1, yHalf+1, x, y );
        }
        return node;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
