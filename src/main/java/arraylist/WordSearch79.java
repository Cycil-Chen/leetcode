package arraylist;

import java.util.Arrays;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；
 * 否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 */
public class WordSearch79 {

    public boolean exist(char[][] board, String word) {
        int row = board.length, column = board[0].length;
        boolean [][] visited = new boolean[row][column];
        System.out.println(visited);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (dfs(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char [][] board, String word, boolean [][] visited, int i, int j, int depth) {
        // 顺序不能变
        if (board[i][j] != word.charAt(depth)) {
            return false;
        } else if (depth == word.length() - 1) {
            return true;
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        visited[i][j] = true;
        // 记录结果
        boolean result = false;
        for (int [] dir : directions) {
            int newRow = i + dir[0], newColumn = j + dir[1];
            if (inArea(newRow, newColumn, board.length, board[0].length)) {
                // 没判断过
                if (!visited[newRow][newColumn]) {
                    boolean flag = dfs(board, word, visited, newRow, newColumn, depth + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    private boolean inArea(int i, int j, int row, int column) {
        return i >= 0 && i < row && j >= 0 && j < column;
    }



    private boolean[][] used;
    private int row, col;
    private char[][] boards;
    private char[] ws;
    private int[][] direction = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};


    public boolean exist2(char[][] board, String word) {
        this.row = board.length;
        this.col = board[0].length;
        this.used = new boolean[row][col];
        for (boolean[] u : used) {
            Arrays.fill(u, false);
        }

        this.boards = board;
        this.ws = word.toCharArray();

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == ws[0]) {
                    used[i][j] = true;
                    if (dfs(i, j, 1)) {
                        return true;
                    } else {
                        used[i][j] = false;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int depth) {
        if (depth == ws.length) {
            return true;
        }

        for (int[] d : direction) {
            int newX = i + d[0];
            int newY = j + d[1];
            // 剪枝
            if (! inArea(newX, newY)) {
                continue;
            }
            // 剪枝
            if (used[newX][newY]) {
                continue;
            }
            // 剪枝
            if (boards[newX][newY] != ws[depth]) {
                continue;
            }

            used[newX][newY] = true;
            if (dfs(newX, newY, depth + 1)) {
                return true;
            } else {
                used[newX][newY] = false;
            }
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < this.row && y >= 0 && y < this.col;
    }

}
