package arraylist;

import java.util.HashMap;
import java.util.Map;

/**
 * 560.     Medium      和为K的子数组
  给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 */
public class SubarraySumEqualsK560 {

    /**
     * 方法一  前缀和
     * shijian/空间复杂度    O(n)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // 记录每一个前缀和的个数
        Map<Integer, Integer> map = new HashMap<>();
        int prevSum = 0;
        map.put(prevSum, 1);
        for (int num : nums) {
            prevSum += num;
            if (map.containsKey(prevSum - k)) {
                count += map.get(prevSum - k);
            }
            map.put(prevSum, map.getOrDefault(prevSum, 0) + 1);
        }
        return count;
    }

    /**
     * 方法二
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >=0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
