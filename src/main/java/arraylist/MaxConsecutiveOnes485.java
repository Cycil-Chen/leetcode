package arraylist;

/**
 * 485.     Easy    最大连续 1 的个数
 给定一个二进制数组， 计算其中最大连续 1 的个数。
 */
public class MaxConsecutiveOnes485 {

    /**
     * 方法一
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }

}
