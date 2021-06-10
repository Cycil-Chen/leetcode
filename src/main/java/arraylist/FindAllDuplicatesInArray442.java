package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 找到所有出现两次的元素。

 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 */
public class FindAllDuplicatesInArray442 {

    /**
     * 方法一  绝对值
     * 遍历过的取相反数
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            // 已经遍历过
            if (nums[num - 1] < 0) {
                list.add(num);
            } else {
                nums[num - 1] = - nums[num - 1];
            }
        }
        return list;
    }

    /**
     * 方法二  相加求和
     * 判断和的范围，重复两次在2n~3n+1之间
     * 重复k次在kn~(k+1)n+1之间
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1)%n] = nums[(num - 1)%n] + n;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n && nums[i] < 3 * n + 1) {
                list.add(i + 1);
            }
        }
        return list;
    }
}
