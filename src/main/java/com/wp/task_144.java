package com.wp;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_144 144. 二叉树的前序遍历
 * @Description: https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * @date 2022/2/10 17:15
 */
public class task_144 {
    public static void main( String[] args ) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = preorderTraversal( root );
        System.out.println( list );
    }

    static class ColorNode{
        TreeNode node;
        String color;

        public ColorNode( TreeNode node, String color ) {
            this.node = node;
            this.color = color;
        }

        public TreeNode getNode() {
            return node;
        }

        public String getColor() {
            return color;
        }

        public void setColor( String color ) {
            this.color = color;
        }
    }

    public static List<Integer> preorderTraversal( TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        ColorNode node = new ColorNode( root, "White" );
        stack.push( node );
        while (!stack.isEmpty()) {
            ColorNode pop = stack.pop();
            if (null==pop.getNode()) {
                continue;
            }
            if (pop.getColor().equals( "White" )) {
                stack.push( new ColorNode( pop.getNode().right, "White" ) );
                pop.setColor( "Gray" );
                stack.push( pop );
                stack.push( new ColorNode( pop.getNode().left,"White" ) );
            }else{
                list.add( pop.getNode().val );
            }
        }

        return list;
    }

    private List<Integer> method1( TreeNode root ) {
        List<Integer> list = new ArrayList<>();
        preorder( root,list);
        return list;
    }

    private void preorder( TreeNode node, List<Integer> list ) {
        if (null==node) {
            return;
        }
        list.add( node.val );
        preorder( node.left,list );
        preorder( node.right,list );
    }
}
