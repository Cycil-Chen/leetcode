package arraylist;

/**
 * 130.     Medium      被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class SurroundedRegions130 {
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            // 递归左右两边界
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        // 因为左右两边界已经遍历，所以从1 到 n -2
        for (int i = 1; i < n - 1; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 没被包围
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }

    /**
     * 递归数组四周的边界
     * @param board
     * @param x
     * @param y
     */
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 'X') {
            return;
        }

        board[x][y] = '1';
        // 上
        dfs(board, x - 1, y);
        // 下
        dfs(board, x + 1, y);
        // 左
        dfs(board, x, y - 1);
        // 右
        dfs(board, x, y + 1);
    }
}
