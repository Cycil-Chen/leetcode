package arraylist;

/**
 * 238.     Medium      除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output
 */
public class ProductofArrayExceptSelf238 {

    /**
     * 方法一  左右乘积列表
     * 时间复杂度    O(n)
     * 空间复杂度    O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int [] result = new int[nums.length];

        int [] left = new int[nums.length];
        int [] right = new int[nums.length];
        left[0] = 1;
        // 索引i 左侧所有元素的乘积
        for (int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i -1];
        }

        right[right.length - 1] = 1;
        // 索引i 右侧所有元素的乘积
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    /**
     * 方法二
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int [] result = new int[nums.length];

        // 计算索引i 左侧的元素乘积
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }

        // 记录索引i 右侧的元素
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            // 更新right
            right = right * nums[i];
        }
        return result;
    }

}
