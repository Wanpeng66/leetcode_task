package com.wp;

/**
 * @author: wp
 * @Title: task_146   146. LRU 缓存
 * @Description: https://leetcode-cn.com/problems/lru-cache/
 * @date 2022/2/10 10:47
 */
public class task_146 {

    public static void main( String[] args ) {
        LRUCache lRUCache = new LRUCache(10);
        lRUCache.put(10, 13);
        lRUCache.put(3, 17);
        lRUCache.put(6, 11);
        lRUCache.put(10, 5);
        lRUCache.put(9, 10);
        System.out.println( lRUCache.get( 13 ) );
        lRUCache.put(2, 19);
        System.out.println( lRUCache.get( 2 ) );
        System.out.println( lRUCache.get( 3 ) );
        lRUCache.put(5, 25);
        System.out.println( lRUCache.get( 8 ) );
        lRUCache.put(9, 22);
        lRUCache.put(5, 5);
        lRUCache.put(1, 30);
        System.out.println( lRUCache.get( 11 ) );
        lRUCache.put(9, 12);
        System.out.println( lRUCache.get( 7 ) );
        System.out.println( lRUCache.get( 5 ) );
        System.out.println( lRUCache.get( 8 ) );
        System.out.println( lRUCache.get( 9 ) );
        lRUCache.put(4, 30);
        lRUCache.put(9, 3);
        System.out.println( lRUCache.get( 9 ) );
        System.out.println( lRUCache.get( 10 ) );
        System.out.println( lRUCache.get( 10 ) );
        lRUCache.put(6, 14);
        lRUCache.put(3, 1);
        System.out.println( lRUCache.get( 3 ) );
        lRUCache.put(10, 11);
        System.out.println( lRUCache.get( 8 ) );
        lRUCache.put(2, 14);
        System.out.println( lRUCache.get( 1 ) );
        System.out.println( lRUCache.get( 5 ) );
        System.out.println( lRUCache.get( 4 ) );
        lRUCache.put(11, 4);
        lRUCache.put(12, 24);
        lRUCache.put(5, 18);
        System.out.println( lRUCache.get( 13 ) );
        lRUCache.put(7, 23);
        System.out.println( lRUCache.get( 8 ) );
        System.out.println( lRUCache.get( 12 ) );
        lRUCache.put(3, 27);
        lRUCache.put(2, 12);
        System.out.println( lRUCache.get( 5 ) );
        lRUCache.put(2, 9);
        lRUCache.put(13, 4);
        lRUCache.put(8, 18);
        lRUCache.put(1, 7);
        System.out.println( lRUCache.get( 6 ) );
        lRUCache.put(9, 29);
        lRUCache.put(8, 21);
        System.out.println( lRUCache.get( 5 ) );
        lRUCache.put(6, 30);
        lRUCache.put(1, 12);
        System.out.println( lRUCache.get( 10 ) );
        lRUCache.put(4, 15);
        lRUCache.put(7, 22);
        lRUCache.put(11, 26);
        lRUCache.put(8, 17);
        lRUCache.put(9, 29);
        System.out.println( lRUCache.get( 5 ) );
        lRUCache.put(3, 4);
        lRUCache.put(11, 30);
        System.out.println( lRUCache.get( 12 ) );
        lRUCache.put(4, 29);
        System.out.println( lRUCache.get( 3 ) );
        System.out.println( lRUCache.get( 9 ) );
        System.out.println( lRUCache.get( 6 ) );
        lRUCache.put(3, 4);
        System.out.println( lRUCache.get( 1 ) );
        System.out.println( lRUCache.get( 10 ) );
        lRUCache.put(3, 29);
        lRUCache.put(10, 28);
        lRUCache.put(1, 20);
        lRUCache.put(11, 13);
        System.out.println( lRUCache.get( 3 ) );
        lRUCache.put(3, 12);
        lRUCache.put(3, 8);
        lRUCache.put(10, 9);
        lRUCache.put(3, 26);
        System.out.println( lRUCache.get( 8 ) );
        System.out.println( lRUCache.get( 7 ) );
        System.out.println( lRUCache.get( 5 ) );
        lRUCache.put(13, 17);
        lRUCache.put(2, 27);
        lRUCache.put(11, 15);
        System.out.println( lRUCache.get( 12 ) );
        lRUCache.put(9, 19);
        lRUCache.put(2, 15);
        lRUCache.put(3, 16);
        System.out.println( lRUCache.get( 1 ) );
        lRUCache.put(12, 17);
        lRUCache.put(9, 1);
        lRUCache.put(6, 19);
        System.out.println( lRUCache.get( 4 ) );
        System.out.println( lRUCache.get( 5 ) );
        System.out.println( lRUCache.get( 5 ) );
        lRUCache.put(8, 1);
        lRUCache.put(11, 7);
        lRUCache.put(5, 2);
        lRUCache.put(9, 28);
        System.out.println( lRUCache.get( 1 ) );
        lRUCache.put(2, 2);
        lRUCache.put(7, 4);
        lRUCache.put(4, 22);
        lRUCache.put(7, 24);
        lRUCache.put(9, 26);
        lRUCache.put(13, 28);
        lRUCache.put(11, 26);


    }

    static class LRUCache {
        //数组的容量
        public int capacity;
        //数组
        public Node[] array;
        //双向循环链接的头结点(哨兵节点)
        public Node head;
        //存储的元素个数
        public int num = 0;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            array = new Node[capacity];
            head = new Node( -1,-1 );
        }

        public int get(int key) {
            //根据key得到对应节点
            Node node = getNode( key );
            if (null==node) {
                return -1;
            }
            //将此节点移动到双向循环链表的末尾
            Node prefix = node.prefix;
            Node next = node.next;
            //先从双向循环链表中去掉自己 然后再拼到链接的末尾
            prefix.next = next;
            next.prefix = prefix;
            Node p1 = head.prefix;
            p1.next = node;
            node.prefix = p1;
            node.next = head;
            head.prefix = node;
            return node.getVal();

        }

        private Node getNode( int key ) {
            //对key取capacity模然后得到数组下标
            int hKey = key % capacity;
            //拿到头结点
            Node node = array[hKey];
            if (null==node) {
                return null;
            }
            //按链表往后查找
            while (null!=node && node.key!= key) {
                node = node.hNext;
            }
            return node;
        }

        public void put(int key, int value) {
            //根据key得到对应节点
            Node node = getNode( key );
            if (null==node) {
                //元素个数加加
                num++;
                //如果超过容量 则去掉链表头一个非哨兵节点
                if (num>capacity) {
                    Node next = head.next;
                    if (null==next) {
                        return;
                    }
                    int dKey = next.getKey();
                    //根据待删除节点的key求得节点所在的数组下标
                    int hdKey = dKey % capacity;
                    Node tmp = array[hdKey];
                    Node tPrefix = null;
                    while (null!=tmp && tmp.getKey()!=dKey) {
                        tPrefix = tmp;
                        tmp = tmp.hNext;
                    }
                    //删除数组槽位的链表中的此节点
                    if (null!=tPrefix) {
                        tPrefix.hNext = next.hNext;
                    }else{
                        array[hdKey] = next.hNext;
                    }
                    //删除循环双向链表中的此节点
                    node = head;
                    while (null!=node.next && node.next.getKey()!=dKey) {
                        node = node.next;
                    }
                    node.next = next.next;
                    next.next.prefix = node;
                    num--;
                }
                //定义新节点 将节点插入到数组槽位中的链表 同时也插入双向循环链表
                Node nNode = new Node( key,value );
                int hKey = key % capacity;
                Node prefix = array[hKey];
                if (null==prefix) {
                    array[hKey] = nNode;
                }else{
                    while (null!=prefix.hNext) {
                        prefix = prefix.hNext;
                    }
                    prefix.hNext = nNode;
                }
                prefix = head.prefix;
                if (null==prefix) {
                    head.prefix = nNode;
                    head.next = nNode;
                    nNode.prefix = head;
                    nNode.next = head;
                }else{
                    prefix.next = nNode;
                    nNode.prefix = prefix;
                    nNode.next = head;
                    head.prefix = nNode;
                }
            }else{
                //有此节点 则更新value 且将此节点移动到链表末尾
                node.setVal( value );
                node.prefix.next = node.next;
                node.next.prefix = node.prefix;
                Node prefix = head.prefix;
                prefix.next = node;
                node.prefix = prefix;
                node.next = head;
                head.prefix = node;
            }
        }

        //节点对象
        class Node {
            int key;
            int val;
            //双向循环链表的上个节点
            Node prefix;
            //双向循环链表的下个节点
            Node next;
            //数组槽位的链表的下个节点
            Node hNext;


            public Node( int key, int val ) {
                this.key = key;
                this.val = val;
            }

            public int getKey() {
                return key;
            }

            public int getVal() {
                return val;
            }

            public void setVal( int val ) {
                this.val = val;
            }
        }
    }

}
