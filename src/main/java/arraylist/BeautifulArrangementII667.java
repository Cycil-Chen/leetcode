package arraylist;

/**
 * 667.     Medium      优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个
 * 不同正整数，并同时满足下述条件：
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|,
 * |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 */
public class BeautifulArrangementII667 {

    public int[] constructArray(int n, int k) {
        int [] res = new int[n];
        // 构造等差数列
        for (int i = 0; i < n - k - 1; i++) {
            res[i] = i + 1;
        }

        // 控制交错的变量
        int j = 0;
        // 构造交错数列，下标从n-k-1开始，数值从n-k开始
        int left = n - k;
        int right = n;
        for (int i = n - k - 1; i < n; i++) {
            if (j % 2 == 0) {
                res[i] = left++;
            } else {
                res[i] = right--;
            }
            j++;
        }
        return res;
    }
}
