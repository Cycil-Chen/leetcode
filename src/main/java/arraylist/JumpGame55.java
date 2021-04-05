package arraylist;

/**
 * 55.  Medium  跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class JumpGame55 {

    /**
     * 方法一  贪心
     * 时间复杂度：O(n)O(n)，其中 nn 为数组的大小。只需要访问 nums 数组一遍，共 nn 个位置。
     * 空间复杂度：O(1)O(1)，不需要额外的空间开销。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max_length = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max_length) {
                max_length = Math.max(max_length, i + nums[i]);
                if (max_length >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二
     * 和方法一的逻辑一致
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int max_length = 0;
        for (int i = 0; i < nums.length; i++) {
            // 此时i已经超出了能够跳到的最大索引
            if (i > max_length) {
                return false;
            }
            max_length = Math.max(max_length, i + nums[i]);
        }

        return true;
    }
}
