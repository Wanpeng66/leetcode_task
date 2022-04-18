package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_385  385. 迷你语法分析器
 * @Description: https://leetcode-cn.com/problems/mini-parser/
 * @date 2022/4/15 10:28
 */
public class task_385 {
    public static void main( String[] args ) {
        NestedInteger nestedInteger = deserialize( "[123,[456,[789]]]" );
        System.out.println( nestedInteger );
    }
    public static NestedInteger deserialize(String s) {
        return method1( s );
    }

    private static NestedInteger method2( String s ) {
        LinkedList<NestedInteger> st = new LinkedList<>();
        NestedInteger res = new NestedInteger();
        st.add(res);
        int len = s.length();
        for (int i = 0; i < len; ) {
            char c = s.charAt(i);
            if (c == ',') {
                i++;
            } else if (c == '[') {
                NestedInteger li = new NestedInteger();
                st.add(li);
                i++;
            } else if (c == ']') {
                NestedInteger poll = st.pollLast();
                st.peekLast().add(poll);
                i++;
            } else {
                boolean pos = true;
                if (s.charAt(i) == '-') {
                    pos = false;
                    i++;
                }
                int v = 0;
                while (i < len && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    v = v * 10 + ( s.charAt(i) - '0');
                    i++;
                }
                if (!pos) v = -v;
                st.peekLast().add(new NestedInteger(v));
            }
        }
        return res.getList().get(0);
    }

    private static NestedInteger method1( String s ) {
        if (!s.startsWith( "[" )) {
            return new NestedInteger(Integer.parseInt( s ));
        }
        s = s.substring( 1 ).substring( 0, s.length()-2 );
        NestedInteger ni = new NestedInteger();
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int prefixNum = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar!=',') {
                if (aChar=='[') {
                    prefixNum++;
                }
                if (aChar==']') {
                    prefixNum--;
                }
                sb.append( aChar );
            }else{
                if (prefixNum!=0) {
                    sb.append( aChar );
                    continue;
                }else{
                    ni.add( method1( sb.toString() ) );
                    sb.setLength( 0 );
                }
            }
        }
        if (sb.length()>0) {
            ni.add( method1( sb.toString() ) );
        }
        return ni;
    }
}
class NestedInteger {
    Integer v;
    List<NestedInteger> l;
    boolean single;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        single = false;
        l = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        single = true;
        v = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return single;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return single ? v : null;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        if (single) {
            v = value;
        }
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        if (!single) {
            l.add(ni);
        }
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return single ? null : l;
    }
}
