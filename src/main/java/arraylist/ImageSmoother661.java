package arraylist;

/**
 * 661.     Easy    图片平滑器
 * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
 * 平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 */
public class ImageSmoother661 {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int [][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int row = i - 1; row <= i + 1; row++) {
                    for (int col = j - 1; col <= j + 1; col++) {
                        if (row >= 0 && row < m && col >= 0 && col < n) {
                            res[i][j] += img[row][col];
                            count ++;
                        }
                    }
                }
                res[i][j] /= count;
            }
        }
        return res;
    }
}
