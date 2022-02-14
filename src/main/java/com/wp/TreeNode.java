package com.wp;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: wp
 * @Title: TreeNode
 * @Description:
 * @date 2022/2/10 17:09
 */
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

      //数组生成二叉树
      public static TreeNode array2Tree(Integer[] array){
          if (null==array || array.length==0) {
              return null;
          }
          Integer[] nArray = new Integer[array.length+1];
          nArray[0] = null;
          for (int i = 0; i < array.length; i++) {
              nArray[i+1] = array[i];
          }
          Map<Integer,TreeNode> nodes = new HashMap<>();
          for (int i = 1; i < nArray.length; i++) {
              Integer val = nArray[i];
              TreeNode node = null;
              if (val!=null) {
                  node = new TreeNode(val);
              }
              nodes.putIfAbsent( i,node );
              if (i!=1) {
                  int index = i/2;
                  TreeNode parent = nodes.get( index );
                  if (null!=parent) {
                      if (i%2==0) {
                          parent.left = node;
                      }else{
                          parent.right = node;
                      }
                  }
              }
          }
        return nodes.get( 1 );
      }
}
