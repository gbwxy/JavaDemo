package com.gbwxy.C.Leetcode;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/24
 */

/**
 * 跳跃游戏:
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 */
public class Leetcode_55 {

    /**
     * 我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 xx，
     * 如果它在 最远可以到达的位置 的范围内，那么我们就可以从起点通过若干次跳跃到达该位置，
     * 因此我们可以用 x + \textit{nums}[x]x+nums[x] 更新 最远可以到达的位置。
     * <p>
     * 在遍历的过程中，
     * 如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。
     * 反之，如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 作为答案。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
