package arraylist;

import java.util.Arrays;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleinHistogram84 {

    /**
     * 方法一  单调栈
     * 枚举高
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int [] left = new int[n];
        int [] right = new int[n];

        Stack<Integer> stack = new Stack<>();
        // 遍历左边界
        for (int i = 0; i < n; i++) {
            while (! stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        // 遍历右边界
        for (int i = n - 1; i >= 0; i--) {
            while (! stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    /**
     * 方法二  单调栈 + 常数优化
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int [] left = new int[n];
        int [] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (! stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // 确定左边界的同时也确定了左边界对应的右边界
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    /**
     * 同方法一相似
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
         int len = heights.length;
         if (len == 0) {
             return 0;
         }

         int res = 0;
         for (int i = 0; i < len; i++) {
             int left = i;
             // 找左边最后 1 个大于等于 heights[i] 的下标
             while (left > 0 && heights[left - 1] >= heights[i]) {
                 left--;
             }

             int right = i;
             // 找右边最后 1 个大于等于 heights[i] 的索引
             while (right < len - 1 && heights[right + 1] >= heights[i]) {
                 right++;
             }

             res = Math.max(res, (right - left - 1) * heights[i]);
         }
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
