package arraylist;

import java.util.*;

/**
 * 532.     Medium      数组中的 k-diff 数对
 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，
 并返回不同的 k-diff 数对 的数目。
 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 */
public class KdiffPairsinArray532 {

    /**
     * 方法一  排序 + 双指针
     * 方法二四五最好
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0, right = 1;
        while (right < nums.length) {
            // right - 1已经计算过了，所以指针一直向后
            if (nums[right] == nums[right - 1] && right - 1 > left && nums[right - 1] - nums[left] == k) {
                right++;
                continue;
            }
            if (nums[right] - nums[left] == k) {
                count++;
                right++;
            } else if (nums[right] - nums[left] > k && left + 1 < right) {
                // 保证left至少落后right 一位
                left++;
            } else {
                right++;
            }
        }
        return count;
    }

    /**
     * 方法二  map 记录个数
     * 时间/空间复杂度 O(n)
     * @param nums
     * @param k
     * @return
     */
    public int findPairs2(int[] nums, int k) {
        // 记录数组中每个数的个数
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) > 1) {
                    count++;
                }
            } else if (map.containsKey(key + k)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法三
     * @param nums
     * @param k
     * @return
     */
    public int findPairs3(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                if (nums[j] - nums[i] > k) {
                    break;
                }
                if (nums[j] - nums[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法四  修改方法一
     * @param nums
     * @param k
     * @return
     */
    public int findPairs4(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0, right = 1;
        while (left < nums.length && right < nums.length) {
            while (right < nums.length - 1 && nums[right + 1] == nums[right]) {
                right++;
            }

            if (nums[right] - nums[left] > k) {
                if (++left > right)
                    right++;
            } else if (nums[right] - nums[left] == k) {
                count++;
                left++;
                right++;
            } else if (nums[right] - nums[left] < k) {
                right++;
            }
        }
        return count;
    }

    /**
     * 方法五  两数之和变形
     * @param nums
     * @param k
     * @return
     */
    public int findPairs5(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> visit = new HashSet<>();
        for (int num : nums) {
            // 记录两者中的较小值
            if (visit.contains(num - k)) {
                set.add(num - k);
            }
            if (visit.contains(num + k)) {
                set.add(num);
            }
            visit.add(num);
        }
        return set.size();
    }

    public static void main(String [] args) {
        int [] nums = {3,1,4,1,5};
        System.out.println(new KdiffPairsinArray532().findPairs5(nums, 2));
    }
}
