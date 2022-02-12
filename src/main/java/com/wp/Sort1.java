package com.wp;

/**
 * @author: wp
 * @Title: Sort1
 * @Description: 排序算法实现
 * @date 2022/1/29 13:28
 */
public class Sort1 {

    public static void main( String[] args ) {
        int[] array = {2,3,1,55,0,3};
        //bubbleSort( array );
        //insertionSort(array);
        sectionSort( array );
        System.out.println( array );
    }

    //冒泡排序 从小到大
    public static void bubbleSort(int[] array){
        if (null==array || 0 == array.length) {
            return;
        }
        int size = array.length;
        for (int i = 0; i < size; i++) {
            boolean isChanged = false;
            for(int j = 0;j<size-i-1;j++){
                if (array[j]>array[j+1]) {
                    int tmp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = tmp;
                    isChanged = true;
                }
            }
            //如果此次循环没有移动元素 则表明元素已有序 跳出循环
            if (!isChanged) {
                break;
            }
        }
    }

    //插入排序 从小到大
    public static void insertionSort(int[] array){
        if (null==array || 0 == array.length) {
            return;
        }
        int size = array.length;
        //将数组分为两个区域 排序区域和未排序区域 默认第一个元素为有序  之后元素都无序
        for (int i = 1; i < size; i++) {
            //将待排序元素取出
            int value = array[i];
            //定义插入的下标
            int offset = i;
            for(int j = i - 1; j >= 0; j--){
                //从后往前比较排序区域元素与待排序元素的大小 大则将排序区域元素往后挪 并且插入下标变成挪动的那个元素的下标
                if (array[j]>value) {
                    array[j+1] = array[j];
                    offset--;
                }else{
                    break;
                }
            }
            //比较结束后 将待排序元素插入到合适位置
            array[offset] = value;
        }
    }


    //选择排序 从小到大
    //每次循环选择当前最小值插入到已排序区域后方
    public static void sectionSort(int[] array){
        if (null==array || 0 == array.length) {
            return;
        }
        int size = array.length;
        for (int i = 0; i < size; i++) {
            //定义最小值
            Integer min = null;
            //定义最小值所在的下标
            int offset = -1;
            for(int j=i;j<size;j++){
                //如果最小值是null或者当前值比最小值小 则将当前值赋值给最小值 并记录下标
                if (null==min || array[j] < min) {
                    min = array[j];
                    offset = j;
                }
            }
            //循环结束后得到当前最小值 将最小值赋值到前方已排序区域
            array[offset] = array[i];
            array[i] = min;
        }
    }
}
