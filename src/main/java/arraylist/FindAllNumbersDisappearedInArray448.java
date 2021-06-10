package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 448.     Medium      找到所有数组中消失的数字
 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n]
 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 */

public class FindAllNumbersDisappearedInArray448 {

    /**
     * 方法一  相加判断
     * 鸽笼问题，相加之后判断哪个位置没被占用过
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            // num - 1的位置被占用过
            nums[(num - 1) % n] += n;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 方法二  绝对值判断
     * 和方法一的原理一致，都是判断哪些位置没有被占用过
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] > 0) {
                nums[Math.abs(num) - 1] = - nums[Math.abs(num) -1];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            // i 处没被占用过
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

}
