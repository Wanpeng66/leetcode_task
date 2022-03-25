package com.wp;

/**
 * @author: wp
 * @Title: task_661  661. 图片平滑器
 * @Description: https://leetcode-cn.com/problems/image-smoother/
 * @date 2022/3/24 14:15
 */
public class task_661 {

    public static void main( String[] args ) {
        int[][] imgs = {{100,200,100},{200,50,200},{100,200,100}};
        imgs = imageSmoother( imgs );
        System.out.println("...........");
    }

    public static int[][] imageSmoother(int[][] imgs) {
        int rowNum = imgs.length;
        int colNum = imgs[0].length;
        int[][] nArray = new int[rowNum][colNum];
        for (int i = 0; i < imgs.length; i++) {
            int[] img = imgs[i];
            for (int j = 0; j < img.length; j++) {
                int total = 0;
                int num = 0;
                for(int k=-1;k<=1;k++){
                    if (i+k>=0 && i+k<=rowNum-1) {
                        for(int m=-1;m<2;m++){
                            if (j+m>=0 && j+m<=colNum-1) {
                                total += imgs[i+k][j+m];
                                num++;
                            }
                        }
                    }
                }
                int avg = total/num;
                nArray[i][j] = avg;
            }
        }
        return nArray;
    }
}
