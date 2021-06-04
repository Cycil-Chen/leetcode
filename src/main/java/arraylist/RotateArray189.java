package arraylist;

/**
 * 189.     Medium      旋转数组
 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 进阶：
 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */

public class RotateArray189 {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }
        int n = nums.length;
        if (k % n == 0) {
            return;
        }
        k = k % n;
        int [] temp = new int[k];
        int index = n - k;
        for (int i = 0; i < k; i++) {
            temp[i] = nums[index++];
        }

        for (int i = n - 1; i >= k; i--){
            nums[i] = nums[i - k];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }


    /**
     * 方法二  空间换时间
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 方法三  环状替换
     * 时间复杂度    O(n)    每个元素只会被遍历一次
     * 空间复杂度    O(1)
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        // k 步跳跃后，每次加1，往后循环count次
        for (int i = 0; i < count; i++) {
            int start = i;
            // 指向交换后的数
            int prev = nums[start];
            do {
                int next = (start + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                // k 步跳跃
                start = next;
            } while (start != i);
        }
    }

    /**
     * 方法四  翻转数组
     * @param nums
     * @param k
     */
    public void rotate4(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 求最大公约数
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    private void reverse(int [] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


}
