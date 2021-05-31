package arraylist;

/**
 * 123.     Hard    买卖股票的最佳时机 III
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class BestTimetoBuyandSellStockIII123 {

    /**
     * 方法一  动态规划
     * 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
      空间复杂度：O(1)。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 5个状态：
        // 未进行过任何操作； 不用考虑
        // 只进行过一次买操作；
        // 进行了一次买操作和一次卖操作，即完成了一笔交易；
        // 在完成了一笔交易的前提下，进行了第二次买操作；
        // 完成了全部两笔交易。
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 0; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
