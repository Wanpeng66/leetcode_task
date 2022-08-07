package com.wp;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import java.util.List;
import java.util.Random;

/**
 * @author: wp
 * @Title: task_1206  1206. 设计跳表
 * @Description: https://leetcode.cn/problems/design-skiplist/
 * @date 2022/7/26 10:02
 */
public class task_1206 {
    public static void main( String[] args ) {
        List<String> lines = FileUtil.readUtf8Lines( "C:\\Users\\plantdata-nb-0001\\Desktop\\param.txt" );
        JSONArray array = JSONArray.parseArray( lines.get( 0 ) );
        JSONArray values = JSONArray.parseArray( lines.get( 1 ) );
        Skiplist skiplist = new Skiplist();
        for (int i = 0; i < array.size(); i++) {
            String opt = array.getString( i );
            int value = values.getIntValue( i );
            if (opt.equals( "add" )) {
                skiplist.add( value );
            } else if (opt.equals( "search" )) {
                System.out.println( "search:"+skiplist.search( value ) );
            }else{
                System.out.println( "del:"+skiplist.erase( value ) );
            }
        }

    }
    static class Skiplist {
        private static final int MAX_LEVEL = 16;
        private int levelCount = 1;
        private Node head = new Node(MAX_LEVEL);
        private Random r = new Random();

        public boolean search(int value) {
            Node p = head;
            for (int i = levelCount - 1; i >= 0; --i) {
                while (p.forwards[i] != null && p.forwards[i].data < value) {
                    p = p.forwards[i];
                }
            }

            if (p.forwards[0] != null && p.forwards[0].data == value) {
                return true;
            } else {
                return false;
            }
        }

        public void add(int value) {
            int level = head.forwards[0] == null ? 1 : randomLevel();
            // 每次只增加一层，如果条件满足
            if (level > levelCount) {
                level = ++levelCount;
            }
            Node newNode = new Node(level);
            newNode.data = value;
            Node p = head;
            // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
            for (int i = levelCount - 1; i >= 0; --i) {
                while (p.forwards[i] != null && p.forwards[i].data < value) {
                    // 找到前一节点
                    p = p.forwards[i];
                }
                // levelCount 会 > level，所以加上判断
                if (level > i) {
                    if (p.forwards[i] == null) {
                        p.forwards[i] = newNode;
                    } else {
                        Node next = p.forwards[i];
                        p.forwards[i] = newNode;
                        newNode.forwards[i] = next;
                    }
                }

            }
        }

        public boolean erase(int value) {
            boolean flag = false;
            Node[] update = new Node[levelCount];
            Node p = head;
            for (int i = levelCount - 1; i >= 0; --i) {
                while (p.forwards[i] != null && p.forwards[i].data < value) {
                    p = p.forwards[i];
                }
                update[i] = p;
            }

            if (p.forwards[0] != null && p.forwards[0].data == value) {
                flag = true;
                for (int i = levelCount - 1; i >= 0; --i) {
                    if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                        update[i].forwards[i] = update[i].forwards[i].forwards[i];
                    }
                }
            }

            while (levelCount>1&&head.forwards[levelCount]==null){
                levelCount--;
            }
            return flag;
        }

        private int randomLevel() {
            int level = 1;
            for (int i = 1; i < MAX_LEVEL; ++i) {
                if (r.nextInt() % 2 == 1) {
                    level++;
                }
            }
            return level;
        }

        class Node {
            private int data = -1;
            private Node forwards[];
            private int maxLevel = 0;

            public Node( int level ) {
                forwards = new Node[level];
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append("{ data: ");
                builder.append(data);
                builder.append("; levels: ");
                builder.append(maxLevel);
                builder.append(" }");

                return builder.toString();
            }
        }
    }
}
