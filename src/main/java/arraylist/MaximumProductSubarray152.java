package arraylist;

/**
 * 152.     Medium      乘积最大子数组
 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 并返回该子数组所对应的乘积。
 */

public class MaximumProductSubarray152 {

    /**
     * 方法一
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = max, mn = min;
            max = Math.max(mx * nums[i], Math.max(mn * nums[i], nums[i]));
            min = Math.min(mn * nums[i], Math.min(mx * nums[i], nums[i]));
            res = Math.max(max, res);
        }
        return res;
    }

    /**
     * 方法二
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, mx = 1, mn = 1;
        for (int num : nums) {
            if (num < 0) {
                int temp = mx;
                mx = mn;
                mn = temp;
            }

            mx = Math.max(mx * num, num);
            mn = Math.min(mn * num, num);
            max = Math.max(max, mx);
        }
        return max;
    }

}
