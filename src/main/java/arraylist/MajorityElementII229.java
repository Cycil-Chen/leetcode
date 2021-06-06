package arraylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229.     Medium      求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 */
public class MajorityElementII229 {

    /**
     * 方法一  Map
     * 时间复杂度    O(n)
     * 空间复杂度    O(n)
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (! map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) > nums.length / 3) {
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 方法二
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * 摩尔投票法升级
     * 对于大于n/3的数组来说，最多有两个数；因此，对于大叔n/k的数组来说，最多有k-1个数
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int candidate1 = nums[0], candidate2 = nums[0];
        int count1 = 0, count2 = 0;
        // 先投票
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
                count2++;
                continue;
            }
            // 更换候选人
            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }

            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }
            // 都不相等，数量也没减到0，继续减
            count1--;
            count2--;
        }

        count1 = count2 = 0;
        // 计数
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) list.add(candidate1);
        if (count2 > nums.length / 3) list.add(candidate2);
        return list;
    }

}
