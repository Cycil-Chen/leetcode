package arraylist;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleinHistogram84 {

    /**
     * 方法一  单调栈
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        return res;
    }

    /**
     * 枚举宽，固定高度
     * 时间复杂度O(N^2)，不符合
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        int result = 0;
        // 枚举左边界
        for (int left = 0; left < heights.length; left++) {
            int min = Integer.MIN_VALUE;
            //枚举定右边界
            for (int right = left; right < heights.length; right++) {
                // 确定高度
                min = Math.min(min, heights[right]);
                // 计算面积
                result = Math.max(result, (right - left + 1) * min);
            }
        }
        return result;
    }

    /**
     * 枚举高，固定宽
     * 时间复杂度O(N^2)，不符合
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            // 枚举高
            int height = heights[i];
            int left = i, right = i;
            // 确定左右边界
            while (left - 1 >= 0 && heights[left - 1] >= height) {
                left--;
            }

            while (right + 1 < heights.length && heights[right + 1] >= height) {
                right++;
            }

            result = Math.max(result, (right - left + 1) * height);
        }
        return result;
    }
}
