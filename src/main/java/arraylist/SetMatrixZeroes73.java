package arraylist;

/**
 * Created by Administrator on 2021/4/8.
 * 73. 矩阵置零
 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 进阶：
 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 你能想出一个仅使用常量空间的解决方案吗？
 */
public class SetMatrixZeroes73 {

    /**
     * 方法二：使用两个标记变量
     * 思路和算法
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1)O(1) 的额外空间。
     * 但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 00。因此我们需要额外
     * 使用两个标记变量分别记录第一行和第一列是否原本包含 00。
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，
     * 然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        boolean rowFlag = false, colFlag = false;
        // 标记第一行是否有0
        for (int i = 0; i < m; i++){
            if (matrix[0][i] == 0) {
                rowFlag = true;
            }
        }

        // 标记第一列是否有0
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowFlag) {
            for (int i = 0; i < m; i++) {
                matrix[0][i] = 0;
            }
        }

        if (colFlag) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    /**
     * 方法一：使用标记数组
     * 思路和算法
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     * 具体地，我们首先遍历该数组一次，如果某个元素为 00，那么就将该元素所在的行和列所对应标记数组的
     * 位置置为 \text{true}true。最后我们再次遍历该数组，用标记数组更新原数组即可。
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    /**
     * 方法三：使用一个标记变量
     思路和算法
     我们可以对方法二进一步优化，只使用一个标记变量记录第一列是否原本存在 00。这样，第一列的第一个元素
     即可以标记第一行是否出现 00。
     但为了防止每一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素。
     * @param matrix
     */
    public void setZeroes3(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int m = matrix.length, n = matrix[0].length;
        boolean colFlag = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }

            for (int j = 1; j < n; j++) {
                // 设置首行首列数字为0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (colFlag) {
                matrix[i][0] = 0;
            }
        }
    }


    public static void main(String [] args) {
        int [][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};

        new SetMatrixZeroes73().setZeroes3(matrix);
    }
}
