package arraylist;

/**
 * 80.      Medium      删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，
 * 返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicatesSortedArrayII80 {

    /**
     * 法一：双指针
     * 时间复杂度：O(n)，其中 nn 是数组的长度。我们最多遍历该数组一次。
     * 空间复杂度：O(1)。我们只需要常数的空间存储若干变量。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int len = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[len - 2] != nums[i]) {
                nums[len] = nums[i];
                len++;
            }
        }
        return len;
    }



    public int removeDuplicates2(int[] nums) {
        return process(nums, 2);
    }
    int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x) nums[u++] = x;
        }
        return u;
    }
}
