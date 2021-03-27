package com.gbwxy.others.DeduplicationSorting;

import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/26
 */
public class mySort {


//     * 归并排序
//     * 采用的是分治(divide-and-conquer)法思想。
//     * 基本思想：将待排序元素分成大小大致相同的2个子集合，分别对2个子集合进行排序，最终将排好序的子集合合并成为所要求的排好序的集合。

//    第一, 分解: 把待排序的 n 个元素的序列分解成两个子序列, 每个子序列包括 n/2 个元素.
//    第二, 治理: 对每个子序列分别调用归并排序MergeSort, 进行递归操作
//    第三, 合并: 合并两个排好序的子序列,生成排序结果.


    public static int[] mergeSort(int[] a) {
        return mergeSort(a, 0, a.length - 1);
    }

    /**
     * 归并排序
     *
     * @param a    要排序的数组
     * @param low  数组中要排序的最小下标
     * @param high 数组中要排序的最大下标
     * @return
     */
    public static int[] mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
        return a;
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }


    //* 计数排序
    //  如果一个数组里所有元素都是整数，而且都在0-k以内。
    //  那对于数组里每个元素来说，如果我能知道数组里有多少项小于或等于该元素。我就能准确地给出该元素在排序后的数组的位置。

    /**
     * 计数排序
     *
     * @param a 要排序的list
     * @return
     */
    public static int[] countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for (int i = 0; i < a.length; ++i) {
            c[a[i] - min] += 1;//优化过的地方，减小了数组c的大小
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; --i) {
            b[--c[a[i] - min]] = a[i];//按存取的方式取出c的元素
        }
        return b;
    }

    public static int[] countSort(List<Integer> a) {
        int length = a.size();
        int b[] = new int[length];
        int max = a.get(0), min = a.get(0);
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for (int i = 0; i < length; ++i) {
            c[a.get(i) - min] += 1;//优化过的地方，减小了数组c的大小
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = length - 1; i >= 0; --i) {
            b[--c[a.get(i) - min]] = a.get(i);//按存取的方式取出c的元素
        }
        return b;
    }

    /**
     * @param array
     * @param k
     * @return
     */
    private static int[] countSort(int[] array, int k) {
        int[] C = new int[k + 1];//构造C数组
        int length = array.length, sum = 0;//获取A数组大小用于构造B数组
        int[] B = new int[length];//构造B数组
        for (int i = 0; i < length; i++) {
            C[array[i]] += 1;// 统计A中各元素个数，存入C数组
        }
        for (int i = 0; i < k + 1; i++)//修改C数组
        {
            sum += C[i];
            C[i] = sum;
        }
        for (int i = length - 1; i >= 0; i--)//遍历A数组，构造B数组
        {

            B[C[array[i]] - 1] = array[i];//将A中该元素放到排序后数组B中指定的位置
            C[array[i]]--;//将C中该元素-1，方便存放下一个同样大小的元素

        }
        return B;//将排序好的数组返回，完成排序

    }

}
