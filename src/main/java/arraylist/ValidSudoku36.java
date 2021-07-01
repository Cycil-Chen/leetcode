package arraylist;

import java.util.HashMap;
import java.util.Map;

/**
 * 36.      Medium      有效的数独
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 */
public class ValidSudoku36 {
    /**
     * 方法一  一次迭代
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer>[] rowMap = new HashMap[9];
        Map<Integer, Integer>[] colMap = new HashMap[9];
        Map<Integer, Integer>[] boxMap = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<>();
            colMap[i] = new HashMap<>();
            boxMap[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int n = (int) board[i][j];
                    // 每个小数独的序号
                    int box_index = (i / 3) * 3 + j / 3;
                    // 保证每列的数不相同
                    rowMap[i].put(n, rowMap[i].getOrDefault(n, 0) + 1);
                    // 保证每行的数不相同
                    colMap[j].put(n, colMap[j].getOrDefault(n, 0) + 1);
                    // 保证每个小数独中的数不相同
                    boxMap[box_index].put(n, boxMap[box_index].getOrDefault(n, 0) + 1);

                    if (rowMap[i].get(n) > 1 || colMap[j].get(n) > 1 || boxMap[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 方法二  采用数组
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        // 哈希表存储每一行的每个数是否出现过，默认初始情况下，每一行每一个数都没有出现过
        int [][] row = new int[9][10];
        // 整个board有9行，第二维的维数10是为了让下标有9，和数独中的数字9对应。
        // 存储每一列的每个数是否出现过，默认初始情况下，每一列的每一个数都没有出现过
        int [][] col = new int[9][10];
        // 存储每一个box的每个数是否出现过，默认初始情况下，在每个box中，每个数都没有出现过。整个board有9个box。
        int [][] box = new int[9][10];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                // 遍历到第i行第j列的那个数,我们要判断这个数在其所在的行有没有出现过，
                // 同时判断这个数在其所在的列有没有出现过
                // 同时判断这个数在其所在的box中有没有出现过
                if(board[i][j] == '.') continue;
                int curNumber = board[i][j]-'0';
                if(row[i][curNumber] == 1) return false;
                if(col[j][curNumber] == 1) return false;
                if(box[j/3 + (i/3)*3][curNumber] == 1) return false;

                // 之前都没出现过，现在出现了，就给它置为1，下次再遇见就能够直接返回false了。
                row[i][curNumber] = 1;
                col[j][curNumber] = 1;
                box[j/3 + (i/3)*3][curNumber] = 1;
            }
        }
        return true;
    }

    /**
     * 方法三  方法二的变种
     * @param board
     * @return
     */
    public boolean isValidSudoku3(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] col = new int[9][9];
        int[][] sbox = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]!='.'){
                    int num = board[i][j] - '1';
                    int index_box = (i/3)*3+j/3;
                    if (rows[i][num]==1) {
                        return false;
                    } else {
                        rows[i][num]=1;
                    }
                    if (col[j][num]==1) {
                        return false;
                    } else {
                        col[j][num]=1;
                    }
                    if (sbox[index_box][num]==1) {
                        return false;
                    } else {
                        sbox[index_box][num]=1;
                    }
                }
            }
        }
        return true;
    }

}
