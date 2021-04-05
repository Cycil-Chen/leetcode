package arraylist;

/**
 * 48. 旋转图像
 * 给定一个 n x n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class RotateImage48 {

    /**
     * 方法一  使用辅助数组
     * 复杂度达不到要求
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }


    /**
     * 时间复杂度：O(N^2)
     *  其中 N 是 matrix 的边长。我们需要枚举的子矩阵大小为 O(\lfloor n/2 \rfloor \times \lfloor (n+1)/2 \rfloor) = O(N^2)⌊n/2⌋×⌊(n+1)/2⌋)=O(N
     * 空间复杂度：O(1)O(1)。为原地旋转。
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i -1][n - j - 1];
                matrix[n - i - 1][n - j -1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }


    /**
     * 方法三：用翻转代替旋转
     * 时间复杂度：O(N^2)O(N
     * 其中 N 是 matrix 的边长。对于每一次翻转操作，我们都需要枚举矩阵中一半的元素。
     * 空间复杂度：O(1)O(1)。为原地翻转得到的原地旋转。
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
