package arraylist;

import java.util.Arrays;

/**
 * 209.     Medium      长度最小的子数组
 给定一个含有 n 个正整数的数组和一个正整数 target 。

 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组
 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class MinimumSizeSubarraySum209 {

    /**
     * 方法一  前缀和 + 二分查找
     * 时间复杂度    O(nlogn)
     * 空间复杂度    O(n)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int [] sum = new int[nums.length + 1];
        // 计算前缀和
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int i = 1; i < sum.length; i++) {
            // 因为是前缀和，所以每次计算下标时要加上前面一个数，
            // 用于计算数量时去掉前面一个数
            int s = target + sum[i - 1];
            int index = Arrays.binarySearch(sum, s);
            if (index < 0) {
                // 二分查找如果找不到相等的下标，就是当前左边界加一的负数，-（low + 1）, low = mid + 1
                index = - index - 1;
            }

            if (index <= sum.length - 1) {
                // 因为前缀和数组0下标不计算数量，而i从1开始，所以最终数量要加1
                res = Math.min(res, index - (i - 1));
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 方法二  双指针
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0, end = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


        public static void main(String []args){
        int [] nums = new  int[] {1,2,3,4,5};
        int res = new MinimumSizeSubarraySum209().minSubArrayLen(11, nums);
        System.out.println(res);
    }
}
