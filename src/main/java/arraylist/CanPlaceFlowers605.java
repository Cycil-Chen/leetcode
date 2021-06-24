package arraylist;

/**
 * 605.     Easy    种花问题
 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，
 它们会争夺水源，两者都会死去。
 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，
 1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，
 不能则返回 false。
 */
public class CanPlaceFlowers605 {

    /**
     * 方法一  贪心
     * 时间复杂度    O(n)
     * 空间复杂度    O(1)
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }

    /**
     * 方法二  判断
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed.length == 1) {
            return n == 1 ? flowerbed[0] == 0 : false;
        }

        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (count >= n) {
                return true;
            }
            if (i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                count++;
                continue;
            }
            if (i - 1 >= 0 && i + 1 < flowerbed.length && flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                count++;
            }
            if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                count++;
            }
        }
        return count >= n;
    }

    /**
     * 方法三
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0;) {
            // 判断方式很重要
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                n--;
                i += 2;
            } else {
                //flowerbed[i]==0 && i != flowerbed.length-1 && flowerbed[i+1]==1
                i += 3;
            }
        }
        return n <= 0;
    }

    /**
     * 方法四
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers4(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)
                    && (i == 0 || flowerbed[i - 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    /**
     * 方法五
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers5(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (n == 0) break;
            if (flowerbed[i] == 1) continue;
            // 因为上面有if判断，走到left说明i=0时，flowerbed[0]就是0,否则进入下一次循环了
            int left = i > 0 ? flowerbed[i - 1] : 0;
            // 对于i=flowerbed.length-1 同理
            int right = i < flowerbed.length - 1 ? flowerbed[i + 1] : 0;
            if (left == 0 && right == 0) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n == 0;
    }


    public static void main(String [] args) {
        int [] flowerbed = {1,0,0,0,1};
        new CanPlaceFlowers605().canPlaceFlowers2(flowerbed, 1);
    }
}
