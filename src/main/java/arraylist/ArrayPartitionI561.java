package arraylist;

import java.util.Arrays;

/**
 * 561.     Easy    数组拆分 I
 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对,
 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 返回该 最大总和 。
 */
public class ArrayPartitionI561 {

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}
