package arraylist;

import java.util.Arrays;
import java.util.Stack;

/**
 * 581.     Medium      最短无序连续子数组
 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，
 那么整个数组都会变为升序排序。
 请你找出符合题意的 最短 子数组，并输出它的长度。
 */
public class ShortestUnsortedContinuousSubarray581 {

    /**
     * 方法一  暴力
     * 时间复杂度    O(n^2)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int left = nums.length;
        int right = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    left = Math.min(left, i);
                    right = Math.max(right, j);
                }
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }

    /**
     * 方法二  排序
     * 时间复杂度    O(nlogn)
     * 空间复杂度    O(n)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray2(int[] nums) {
        int [] nums2 = nums.clone();
        Arrays.sort(nums);
        int left = nums.length, right = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] != nums[i]) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }
        return right - left >= 0 ? right - left + 1 : 0;
    }

    /**
     * 方法三  单调栈
     * 时间复杂度    O(n)
     * 空间复杂度    O(n)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray3(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length, right = 0;
        for (int i = 0; i < nums.length; i++) {
            while (! stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1;i >= 0; i--) {
            while (! stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }
        return right - left > 0 ? right - left + 1 : 0;
    }

    /**
     * 方法四  找最小值和最大值
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int findUnsortedSubarray4(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            // 从左往后持续比较前后两位相对位置大小
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            // flag最后不在置false，因为只要有一个不在正确的位置上，后面的都需要判断
            // 直到找到最小值
            if (flag) {
                // 选出不在正确顺序上的最小值
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        // 记录选出的最小值和最大值在数组中正确的位置
        int left, right;
        for (left = 0; left < nums.length; left++) {
            if (min < nums[left]) {
                break;
            }
        }
        for (right = nums.length - 1; right >=0; right--) {
            if (max > nums[right]) {
                break;
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }
}
