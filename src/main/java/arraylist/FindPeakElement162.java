package arraylist;

/**
 * 162.     Medium      寻找峰值
 峰值元素是指其值大于左右相邻值的元素。
 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，
 返回 任何一个峰值 所在位置即可。

 你可以假设 nums[-1] = nums[n] = -∞ 。
 */

public class FindPeakElement162 {

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] > nums[i + 1]) {
                index = i;
                break;
            }
            if (i == nums.length - 1 && nums[i] > nums[i - 1]) {
                index = i;
                break;
            }
            if (i > 0 && i < nums.length - 1) {
                if (nums[i] > nums[i - 1] && nums [i] > nums[i + 1]) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    /**
     * 方法二
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 遍历到第i个元素本身就说明上一个元素不满足nums[i - 1] > nums[i]这一条件
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 方法三  二分查找
     * @param nums
     * @return
     */
    public int findPeakElement3(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
