package arraylist;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标
 * 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，
 * 那么下一步可以移动到下一行的下标 i 或 i + 1 。
 */
public class Triangle120 {

    /**
     * 方法一  动态规划
     * 时间复杂度：O(n^2)，其中 n 是三角形的行数。
     * 空间复杂度：O(n^2)
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 状态转移方程   f[i][j] = Math.min(f[i-1][j-1], f[i-1][j]) + c[i][j]
        // 特殊情况       f[i][0] = f[i-1][0] + c[i][0],    f[i][i] = f[i-1][i-1] + c[i][i]
        int n = triangle.size();
        int [][] f = new int[n][n];

        f[0][0] = triangle.get(0).get(0);
        // 计算路径和
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i -1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int total = f[n - 1][0];
        for (int i = 1; i < n; i++) {
            total = Math.min(total, f[n - 1][i]);
        }
        return total;
    }


    /**
     * 方法二  动态规划 + 空间优化
     * 空间复杂度为 O(n)
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int [][] f = new int[2][n];

        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            for (int j = 1; j < i; j++) {
                f[curr][i] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[prev][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }

        int total =  f[(n - 1)%2][0];
        for (int i = 1; i < n; i++) {
            total = Math.min(total, f[(n - 1)%2][i]);
        }
        return total;
    }

    /**
     * 方法三  空间优化
     * 不够好
     * @param triangle
     * @return
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }


    /**
     * 方法四  动态规划
     * 另一种方法    从后往前递推
     * 时间复杂度：O(N^2)，N 为三角形的行数。
     * 空间复杂度：O(N^2)，N 为三角形的行数。
     * @param triangle
     * @return
     */
    public int minimumTotal4(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 防止数组越界
        int [][] f = new int [n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 从下往上，所以每一行是下一行的最小值和该值之和
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        return f[0][0];
    }


    /**
     * 方法五  动态规划 + 空间优化
     * 时间复杂度：O(N^2),N 为三角形的行数。
     * 空间复杂度：O(N)
     * @param triangle
     * @return
     */
    public int minimumTotal5(List<List<Integer>> triangle) {
        int n = triangle.size();
        int [] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 在实际递推中我们发现，计算 dp[i][j] 时，只用到了下一行的 dp[i + 1][j]
                // 和 dp[i + 1][j + 1]。
                // 因此 dp 数组不需要定义 N 行，只要定义 1 行就阔以啦
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }

}
