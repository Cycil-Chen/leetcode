package arraylist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128.     Medium      最长连续序列
 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 */

public class LongestConsecutiveSequence128 {

    /**
     * 方法一
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestLength = 0;
        for (int num : set) {
            // 不包含num - 1, 否则就从num - 1 进行判断了
            if (! set.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentLength += 1;
                }

                longestLength = Math.max(longestLength, currentLength);
            }
        }
        return longestLength;
    }

    /**
     * 方法二
     * 用哈希表存储每个端点值对应连续区间的长度
     若数已在哈希表中：跳过不做处理
     若是新数加入：
        取出其左右相邻数已有的连续区间长度 left 和 right
        计算当前数的区间长度为：cur_length = left + right + 1
        根据 cur_length 更新最大长度 max_length 的值
        更新区间两端点的长度值
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int num : nums) {
            if (! map.containsKey(num)) {
                int left = map.get(num - 1) == null ? 0 : map.get(num - 1);
                int right = map.get(num + 1) == null ? 0 : map.get(num + 1);

                int curLength = left + right + 1;
                maxLength = Math.max(maxLength, curLength);

                // 放什么数不重要， 重要的是标记该数已有
                map.put(num, 1);
                map.put(num - left, curLength);
                map.put(num + right, curLength);
            }
        }
        return maxLength;
    }

}
