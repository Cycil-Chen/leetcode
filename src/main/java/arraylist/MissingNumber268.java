package arraylist;

/**
 * 268.     Medium      丢失的数字
 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 进阶：
 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 */

public class MissingNumber268 {

    /**
     * 方法一  高斯求和
     * 有溢出风险
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int missingNumber(int [] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int expectedSum = 0;
        for (int i = 0; i < n; i++) {
            expectedSum += nums[i];
        }
        return sum - expectedSum;
    }

    /**
     * 方法二  求和
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int missingNumber2(int [] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            sum -= nums[i - 1];
        }

        // 另一种写法
        /*int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i;
            sum -= nums[i];
        }
        */
        return sum;
    }

    /**
     * 方法三  异或(相同为0，不同为1)
     * 0^a = a
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int missingNumber3(int [] nums) {
        int miss = nums.length;
        for (int i = 0; i < nums.length; i++) {
            miss ^= i ^ nums[i];
        }
        return miss;
    }
}
