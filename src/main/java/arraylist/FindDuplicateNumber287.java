package arraylist;

/**
 * 287. 寻找重复数
 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），
 可知至少存在一个重复的整数。
 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。。
 */
public class FindDuplicateNumber287 {

    /**
     * 方法一  快慢指针
     * 找到闭环的入口
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int findDuplicate(int [] nums) {
        int slow = 0, fast = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            // 找到环的入口
            if (slow == fast) {
                slow = 0;
                while (nums[slow] != nums[fast]) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return nums[slow];
            }
        }

    }

    /**
     * 方法二  二分查找
     * 时间复杂度    O(nlogn)
     * 空间复杂度    O(1)
     * @param nums
     * @return
     */
    public int findDuplicate2(int [] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


}
