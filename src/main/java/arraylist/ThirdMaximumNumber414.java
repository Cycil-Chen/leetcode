package arraylist;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 414.     Easy    第三大的数
 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 */
public class ThirdMaximumNumber414 {

    /**
     * 方法一
     * 时间复杂度    O(n)
     * 空间复杂度    O(1
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == max1 || num == max2 || num == max3) {
                continue;
            }
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }
        return (int) (Long.MIN_VALUE == max3 ? max1 : max3);
    }

    /**
     * 方法二  TreeSet
     * @param nums
     * @return
     */
    public int thirdMax2(int[] nums) {
        // treeSet 是有序集合，默认正序排列
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                // 移除最小的
                set.remove(set.first());
            }
        }
        return set.size() < 3 ? set.last() : set.first();
    }

    /**
     * 方法三  排序
     * @param nums
     * @return
     */
    public int thirdMax3(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int maxCount = 3;
        int max3 = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < max3) {
                count++;
                max3 = nums[i];

                if (count == maxCount){
                    return max3;
                }
            }
        }
        // 没有第三大的数
        return nums[nums.length - 1];
    }

}
