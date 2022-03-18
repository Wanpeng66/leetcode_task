package com.wp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author: wp
 * @Title: SearchExpression
 * @Description:
 * @date 2022/3/16 13:48
 */
public class SearchExpression {
    public static void main( String[] args ) {
        String str = "(((摘要:\"信息管理\" and 主题:java) or (作者:wp not wlj)) and (作者:wp))";
        JSONObject res = new JSONObject();
        //str = "摘要:\"信息管理\" and 主题:java";
        AnalyzerExpression( str, res );


    }

    private static JSONObject AnalyzerExpression( String str,JSONObject res ) {
        if (checkValidString( str )) {
            str = str.substring( 1, str.length()-1 );
            System.out.println( str );
            //以(开头
            if (str.startsWith( "(" )) {
                //(摘要:"信息管理" and 主题:java)
                int offset = getSuffixOffset( str );
                if (offset==-1) {
                    System.out.println("格式错误");
                    return null;
                }
                String half = str.substring( 0, offset + 1 );
                String aHalf = str.substring( offset + 1 );
                offset = getPrefixOffset(aHalf);
                String ysf = aHalf.substring( 0, offset ).trim();
                aHalf = aHalf.replaceFirst( ysf,"" ).trim();
                JSONArray array = new JSONArray();
                if (ysf.equals( "and" )) {
                    res.put( "must",array );
                } else if (ysf.endsWith( "or" )) {
                    res.put( "should",array );
                }else{
                    System.out.println("格式错误");
                }
                array.add( AnalyzerExpression( half,new JSONObject() ) );
                array.add( AnalyzerExpression( aHalf,new JSONObject() ) );
            }else{
                //str = "摘要:\"信息管理\" and 主题:java";
                //作者:((wp not wlj) and www) and 主题:java
                JSONArray array = new JSONArray();
                List<String> list = new ArrayList<>();
                if (str.contains( " and " )) {
                    list.addAll( Arrays.asList(str.split( " and " )) );
                    res.put( "must",array );
                } else if (str.contains( " or " )) {
                    list.addAll( Arrays.asList(str.split( " or " )) );
                    res.put( "should",array );
                }else{
                    //作者:wp not wlj
                    //作者:((wp not wlj) and www)

                    list.add( str );
                }
            }
        }
        return res;
    }

    //获取第一个(的小标
    private static int getPrefixOffset( String aHalf ) {
        char[] chars = aHalf.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='(')
                return i;
        }
        return -1;
    }

    //获取与第一个'('相匹配的')'的下标
    private static int getSuffixOffset( String str ) {
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        stack.push( chars[0] );
        for (int i = 1; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar=='(') {
                stack.push( aChar );
            } else if (aChar==')') {
                stack.pop();
            }
            if (stack.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkValidString(String s) {
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c=='（') {
                minCount++;
                maxCount++;
            } else if(c == ')' || c=='）'){
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                if (maxCount < 0) {
                    return false;
                }
            }
        }
        return minCount == 0;
    }

    public static int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

}
