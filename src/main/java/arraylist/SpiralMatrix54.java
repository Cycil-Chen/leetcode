package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 54.  Medium  螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class SpiralMatrix54 {

    /**
     * 方法一：模拟
     * 时间复杂度：O(mn)O(mn)，其中 mm 和 nn 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
     * 空间复杂度：O(mn)O(mn)。需要创建一个大小为 m \times nm×n 的矩阵 \textit{visited}visited 记录每个位置是否被访问过。
     *
     * 可以模拟螺旋矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，
     * 当路径超出界限或者进入之前访问过的位置时，顺时针旋转，进入下一个方向。
     * 判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵 visited，
     * 其中的每个元素表示该位置是否被访问过。当一个元素被访问时，将 visited 中的对应位置的元素设为已访问。
     * 如何判断路径是否结束？由于矩阵中的每个元素都被访问一次，因此路径的长度即为矩阵中的元素数量，
     * 当路径的长度达到矩阵中的元素数量时即为完整路径，将该路径返回。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }

        int rows = matrix.length, columns = matrix[0].length;
        boolean [][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            list.add(matrix[row][column]);
            visited[row][column] = true;
            int newRow = row + directions[directionIndex][0];
            int newColumn = column + directions[directionIndex][1];
            if (newRow < 0 || newRow >= rows || newColumn < 0 || newColumn >= columns
                || visited[newRow][newColumn] == true) {
                directionIndex = (directionIndex + 1) % 4;
            }

            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }

        return list;

    }


    /**
     * 方法二：按层模拟
     * 时间复杂度：O(mn)O(mn)，其中 mm 和 nn 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
     * 空间复杂度：O(1)O(1)。除了输出数组以外，空间复杂度是常数。
     * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
     * 定义矩阵的第 kk 层是到最近边界距离为 kk 的所有顶点。例如，下图矩阵最外层元素都是第 11 层，
     * 次外层元素都是第 22 层，剩下的元素都是第 33 层。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    public List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int num = matrix[0].length * matrix.length;
        while (num >= 1) {
            for (int i = left; i <= right && num >= 1; i++) {
                list.add(matrix[top][i]);
                num--;
            }
            top++;
            for (int i = top; i <= bottom && num >= 1; i++) {
                list.add(matrix[i][right]);
                num--;
            }
            right--;
            for (int i = right; i >= left && num >= 1; i--) {
                list.add(matrix[bottom][i]);
                num--;
            }
            bottom--;
            for (int i = bottom; i >= top && num >= 1; i--) {
                list.add(matrix[i][left]);
                num--;
            }
            left++;
        }
        return list;
    }


    public static void main(String [] args) {
        int [][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> list = new SpiralMatrix54().spiralOrder3(matrix);

    }
}
