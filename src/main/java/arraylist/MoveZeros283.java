package arraylist;

/**
 * 283.     Easy    移动零
 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class MoveZeros283 {

    /**
     * 方法一  双指针
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     */
    public void moveZeros(int [] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }
}
