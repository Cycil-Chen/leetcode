package arraylist;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 */
public class SortColors75 {

    /**
     * 方法一  单指针
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(1)
     * @param nums
     */
    public void sortColors(int[] nums) {
        int ptr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, ptr);
                ptr++;
            }
        }

        for (int i = ptr; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, i, ptr);
                ptr++;
            }
        }
    }


    /**
     * 方法二  双指针
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                if (left < right) {
                    swap(nums, i, right);
                }
                left++;
                right++;
            } else if (nums[i] == 1) {
                swap(nums, i, right);
                right++;
            }
        }
    }


    /**
     * 方法三  双指针
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
            }

            while (i <= right && nums[i] == 2) {
                swap(nums, i, right);
                right--;
            }
        }
    }



    private void swap(int [] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
