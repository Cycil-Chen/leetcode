package arraylist;

/**
 * 665.     Medium      非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，
 * 该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，
 * 总满足 nums[i] <= nums[i + 1]。
 */
public class NondecreasingArray665 {

    /**
     * 方法一
     * 修改 nums[i] 为 nums[i+1] 后，还需要保证 nums[i−1]≤nums[i] 仍然成立，
     * 即 nums[i−1]≤nums[i+1]，
     * 若该不等式不成立则整个数组必然不是非递减的，则需要修改 nums[i+1] 为 nums[i]。
     * 修改完后，接着判断后续数字的大小关系。
     * 在遍历中统计 nums[i]>nums[i+1] 的次数，若超过一次可以直接返回 false。
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }

    /**
     * 方法二  贪心
     * @param nums
     * @return
     */
    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length && count < 2; i++) {
            if (nums[i - 1] <= nums[i]) {
                continue;
            }
            count++;
            // nums[i]<nums[i-2]<nums[i-1]
            if (i - 1 > 0 && nums[i] < nums[i - 2]) {
                nums[i] = nums[i - 1];
            } else {
                // (i=1&&nums[0]>nums[1]) || nums[i-2]<=nums[i]<nums[i-1]
                nums[i - 1] = nums[i];
            }
        }
        return count <= 1;
    }
}
