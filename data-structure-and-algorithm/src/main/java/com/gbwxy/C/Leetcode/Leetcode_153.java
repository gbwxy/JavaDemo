package com.gbwxy.C.Leetcode;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/24
 */

/**
 * 题目：寻找旋转排列数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 请找出其中最小的元素。
 */
public class Leetcode_153 {

    /**
     * 方法一：二分查找
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        // If the list has just one element then return that element.
        if (nums.length == 1) {
            return nums[0];
        }

        // initializing left and right pointers.
        int left = 0, right = nums.length - 1;

        // if the last element is greater than the first element then there is no rotation.
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
        // Hence the smallest element is first element. A[0]
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        // Binary search way
        while (right >= left) {
            // Find the mid element
            int mid = left + (right - left) / 2;

            // if the mid element is greater than its next element then mid+1 element is the smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // if the mid element is lesser than its previous element then mid element is the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with elements
            // greater than nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value is somewhere to
                // the left
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 第一，原来是升序排序，因此只要进行了旋转，那么数组的最后一个元素一定小于第一个元素。
     * 如果没有旋转那么数组的最后一个元素一定大于第一个元素。
     * 第二，如果进行了旋转，那么在某个位置一定会出现当前元素比下一个元素大的情况。
     * 出现这个情况时，下一个元素一定是旋转过来的那部分元素的第一个值，即最小值。
     * 因此可以使用一个指针，当检测到后面的元素比当前元素小时，那么后面的元素一定是最小值。
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        //指针刚开始在第一个元素
        int needle = 0;
        //判断是否进行了旋转以及数组是否只有一个元素
        if (nums[nums.length - 1] >= nums[0]) {
            //未旋转或者只有一个元素那么直接返回第一个元素
            return nums[0];
        } else {
            //找到当前元素比下一个元素大的位置
            while (needle < nums.length - 1 && nums[needle] < nums[needle + 1]) {
                needle += 1;
            }
            return nums[needle + 1];
        }
    }

}
