package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_429  429. N 叉树的层序遍历
 * @Description: https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @date 2022/4/8 10:30
 */
public class task_429 {
    public List<List<Integer>> levelOrder( Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null==root) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                list.add( poll.val );
                List<Node> children = poll.children;
                queue.addAll( children );
            }
            res.add( list );
        }
        return res;
    }
}
