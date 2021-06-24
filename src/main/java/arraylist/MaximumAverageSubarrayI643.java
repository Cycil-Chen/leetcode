package arraylist;

/**
 * 643.     Easy     子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 */
public class MaximumAverageSubarrayI643 {
    /**
     * 方法一  暴力求解
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            int j = 0;
            while (j < k) {
                sum += nums[i + j];
                j++;
            }
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }

    /**
     * 方法二  滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage2(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }
}
