package arraylist;

/**
 * 566.     Easy    重塑矩阵
 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 */
public class ReshapetheMatrix566 {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int [][] res = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            // 映射   或者可以通过一个一维数组中转
            res[i / c][i % c] = mat[i / n][i % n];
        }
        return res;
    }
}
