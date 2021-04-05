package arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57.  Medium   插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class InsertInterval57 {

    /**
     * 方法一  模拟
     * 时间复杂度：O(n)，其中 nn 是数组 intervals 的长度，即给定的区间个数。
     * 空间复杂度：O(1)。除了存储返回答案的空间以外，我们只需要额外的常数空间即可。
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int []> list = new ArrayList<>();
        int left = newInterval[0];
        int right = newInterval[1];
        // 标记newInterval是否已经合并
        boolean result = false;
        for (int [] interval : intervals) {
            if (interval[0] > right)  {
                // 没有合并，在插入区间的右侧且无交集
                if (! result) {
                    list.add(new int[]{left, right});
                    result = true;
                }
                list.add(interval);
            } else if (interval[1] < left){
                // 在插入区间的左侧且无交集
                list.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (! result) {
            list.add(new int[]{left, right});
        }

        return list.toArray(new int[list.size()][]);
    }


    /**
     * 首先将新区间左边且相离的区间加入结果集（遍历时，如果当前区间的结束位置小于新区间的开始位置，
     * 说明当前区间在新区间的左边且相离）；
     * 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
     * 将最终合并后的新区间加入结果集；
     * 最后将新区间右边且相离的区间加入结果集。
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;
        // 遍历区间列表：
        // 首先将新区间左边且相离的区间加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res[idx++] = newInterval;
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length) {
            res[idx++] = intervals[i++];
        }

        return Arrays.copyOf(res, idx);
    }


    public static void main(String [] args) {
        int [][] intervals = new int[][]{{1,3},{6,9}};
        int [] newInterval = new int[]{2,5};
        new InsertInterval57().insert(intervals, newInterval);
    }
}
