package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author: wp
 * @Title: task_592  592. 分数加减运算
 * @Description: https://leetcode.cn/problems/fraction-addition-and-subtraction/
 * @date 2022/7/27 11:29
 */
public class task_592 {
    public static void main( String[] args ) {
        String expression = "1/3-1/2";
        System.out.println( fractionAddition( expression ) );
    }
    public static String fractionAddition(String expression) {
        char[] chars = expression.toCharArray();
        Stack<String> list = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if ((aChar=='-'|| aChar=='+') && sb.length()>0) {
                list.push( sb.toString() );
                sb.setLength( 0 );
            }
            sb.append( aChar );
        }
        if (sb.length()>0) {
            list.push( sb.toString() );
        }
        String res = list.pop();
        while (!list.isEmpty()) {
            String pop = list.pop();
            String[] split = res.split( "/" );
            String[] popSplit = pop.split( "/" );
            Calculator add = Calculator.add( new Calculator( Integer.parseInt( split[0] ), Integer.parseInt( split[1] ) ), new Calculator( Integer.parseInt( popSplit[0] ), Integer.parseInt( popSplit[1] ) ) );
            res = add.getMolecular()+"/"+add.getDenominator();
        }
        return res;
    }
   static class Calculator {
       private Integer molecular;
       private Integer denominator;
       public Calculator() {
       }
       public Calculator( Integer molecular, Integer denominator) {
           if (molecular == 0 || denominator == 0){
               throw new RuntimeException("分子或者分母不能为0");
           }
           this.molecular = molecular;
           this.denominator = denominator;
       }

       public Integer getMolecular() {
           return molecular;
       }

       public void setMolecular(Integer molecular) {
           this.molecular = molecular;
       }

       public Integer getDenominator() {
           return denominator;
       }

       public void setDenominator(Integer denominator) {
           if (denominator == 0 ){
               throw new RuntimeException("分母不能为0");
           }
           this.denominator = denominator;
       }
       /**
        * 最大公约数
        * @param x
        * @param y
        * @return
        */
       public static int approximation(Integer x,Integer y){
           return x.compareTo(y)>0 ? ((x%y) == 0 ? y : approximation(y,x%y)) : ((y%x) == 0 ? x : approximation(x,y%x));
       }
       /**
        * 最小公倍数
        * @param x
        * @param y
        * @return
        */
       public static int multiple(Integer x,Integer y){
           return x*y/approximation(x,y);
       }

       /**
        * 分数相加
        * @param a
        * @param b
        * @return
        */
       public static Calculator add(Calculator a,Calculator b){
           Calculator add = new Calculator();
           //获取最小公倍数--分母
           Integer denominator = multiple(a.getDenominator(), b.getDenominator());
           //分子
           Integer molecular = (a.getMolecular()*(denominator/a.getDenominator())) + (b.getMolecular()*(denominator/b.getDenominator()));
           //分子分母约分 获取最大公约数
           Integer approximation = molecular==0?denominator:approximation(denominator, Math.abs(molecular));
           add.setMolecular(molecular/approximation);
           add.setDenominator(denominator/approximation);
           return add;
       }
    }
}
