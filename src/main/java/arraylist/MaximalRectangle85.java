package arraylist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 85.      Hard    最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaximalRectangle85 {

    /**
     * 时间复杂度：O(m^2n),其中 m 和 n 分别是矩阵的行数和列数。
     * 空间复杂度：O(mn)，其中 m 和 n 分别是矩阵的行数和列数。我们分配了一个与给定矩阵等大的数组，
     * 用于存储每个元素的左边连续 11 的数量。
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }

        int n = matrix[0].length;
        // 记录矩阵的每个元素的左边连续 1 的数量
        int [][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }

                int width = left[i][j];
                int area = left[i][j];
                // 计算在i j 下标处存在的最大矩形面积
                for (int k = i - 1; k >= 0; k--) {
                    // 不用再计算
                    if (left[k][j] == 0) {
                        break;
                    }
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }

                // 比较目前为止所有计算出来的矩形面积
                res = Math.max(res, area);
            }
        }

        return res;
    }

    /**
     * 方法二 利用单调栈
     * 与84题类似
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }

        int n = matrix[0].length;
        int [][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int res = 0;
        // 对于每一列，使用基于柱状图的方法 枚举高 固定宽度
        for (int j = 0; j < n; j++) {
            int [] up = new int[m];
            int [] down = new int[m];

            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                while (! stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }

                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (! stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }

                down[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int area = (down[i] - up[i] - 1)  * left[i][j];
                res = Math.max(res, area);
            }
        }
        return res;
    }

    /**
     * 方法三  暴力求解
     * 类似方法一
     * @param matrix
     * @return
     */
    public int maximalRectangle3(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }

        int n = matrix[0].length;
        //保存以当前数字结尾的连续 1 的个数
        int [][] left = new int[m][n];
        int res = 0;
        // 开始遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                } else {
                    left[i][j] = 0;
                }

                //记录所有行中最小的数
                int width = left[i][j];
                // 向上扩展行
                for (int k = i; k >= 0; k--) {
                    // 不用再计算
                    if (left[k][j] == 0) {
                        break;
                    }
                    int height = i - k + 1;
                    // 宽取最小值
                    width = Math.min(width, left[k][j]);
                    res = Math.max(res, width * height);
                }
            }
        }
        return res;
    }
}
