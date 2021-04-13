package arraylist;

/**
 * Created by Administrator on 2021/4/8.
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 */
public class SearchaMatrix74 {

    /**
     * 二分解法
     * 时间复杂度：O(\log{(m * n)})O(log(m∗n))
       空间复杂度：O(1)O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n][mid % n] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }


    /**
     * 二次二分
     * 时间复杂度：O(logm+logn)=O(logmn)，其中 mm 和 nn 分别是矩阵的行数和列数。
      空间复杂度：O(1)O(1)。
     * 由于每行的第一个元素大于前一行的最后一个元素，且每行元素是升序的，所以每行的第一个元素大于前一行的
     * 第一个元素，因此矩阵第一列的元素是升序的。
       我们可以对矩阵的第一列的元素二分查找，找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找
       目标值是否存在。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        // 第一次二分查找行
        int row = binaryFindRow(matrix, target);
        if (row < 0) {
            return false;
        }

        // 第二次二分查找列
        return binaryFindTarget(matrix[row], target);
    }

    private int binaryFindRow(int [][] matrix, int target) {
        int low = 0, high = matrix.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean binaryFindTarget(int [] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }




    int m, n;
    /**
     * 抽象 BST 解法
     * 时间复杂度：O(\log{(m * n)})O(log(m∗n))
      空间复杂度：O(1)O(1)
     * @param mat
     * @param t
     * @return
     */
    public boolean searchMatrix3(int[][] mat, int t) {
        m = mat.length; n = mat[0].length;
        int x = 0, y = n - 1;
        while (check(x, y) && mat[x][y] != t) {
            if (mat[x][y] > t) {
                y--;
            } else {
                x++;
            }
        }
        return check(x, y) && mat[x][y] == t;
    }
    boolean check(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
