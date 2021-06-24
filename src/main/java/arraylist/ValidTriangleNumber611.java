package arraylist;

import java.util.Arrays;

/**
 * 611.     Medium      有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 */
public class ValidTriangleNumber611 {

    /**
     * 方法一  回溯递归
     * @param nums
     * @return
     */
    int res = 0;
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        // 记录三角形三条边
        int [] path = new int[3];
        dfs(nums, path, 0, 0);
        return res;
    }

    private void dfs(int [] nums, int [] path, int level, int pre) {
        for (int i = pre; i < nums.length; i++) {
            path[level] = nums[i];
            // 只有三条边都有，且数组没有越界才会比较
            if (level == 2) {
                if (path[0] + path[1] > path[2]) {
                    res++;
                } else {
                    // 数组已经排序，所以后面不用比较，直接退出
                    break;
                }
            } else {
                dfs(nums, path, level + 1, i + 1);
            }
        }
    }

    /**
     * 方法二  排序比较
     * @param nums
     * @return
     */
    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                // 只要left right符合条件，left到right之间的都符合条件
                if (nums[left] + nums[right] > nums[i]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

    /**
     * 方法三  排序
     * @param nums
     * @return
     */
    public int triangleNumber3(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            // 不放入第二个循环中的原因是，前一个K已经走到后面的位置，没必要在j循环下一次
            // 的时候重置k，再来一次；这就相当于k再来了一次循环，没必要
            int k =  i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                // 一直找到不满足的那个k
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }


    public static void main(String [] args) {
        int [] nums = {2,2,3,4};
        System.out.println(new ValidTriangleNumber611().triangleNumber(nums));
    }
}
