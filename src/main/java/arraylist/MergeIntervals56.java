package arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 */
public class MergeIntervals56 {

    /**
     * 方法一  排序
     * 时间复杂度：O(nlogn)，其中 n 为区间的数量。除去排序的开销，我们只需要一次线性扫描
     * 空间复杂度：O(logn)，其中 n 为区间的数量。这里计算的是存储答案之外，使用的额外空间。
     *
     * 我们用数组 merged 存储最终的答案。
     * 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，
     * 并按顺序依次考虑之后的每个区间：
     * 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，
     * 我们可以直接将这个区间加入数组 merged 的末尾；
     * 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，
     * 将其置为二者的较大值。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int []> merge = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (merge.size() == 0 || merge.get(merge.size() - 1)[1] < left) {
                merge.add(new int[]{left, right});
            } else {
                merge.get(merge.size() - 1)[1] = Math.max(merge.get(merge.size() - 1)[1], right);
            }
        }

        return merge.toArray(new int[merge.size()][]);
    }


    /**
     * 方法二  排序+双指针
     * 需要按照先比较区间开始，如果相同再比较区间结束，使用默认的排序规则即可
     * 使用双指针，左边指针指向当前区间的开始
     * 使用一个变量来记录连续的范围 t
     * 右指针开始往后寻找，如果后续的区间的开始值比 t 还小，说明重复了，可以归并到一起
     * 此时更新更大的结束值到 t
     * 直到区间断开，将 t 作为区间结束，存储到答案里
     * 然后移动左指针，跳过中间已经合并的区间
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int []> list = new ArrayList<>();
        for (int i = 0; i < intervals.length;) {
            int j = i + 1;
            int t = intervals[i][1];
            while (j < intervals.length && intervals[j][0] <= t){
                t = Math.max(intervals[j][1], t);
                j++;
            }

            list.add(new int[]{intervals[i][0], t});
            i = j;
        }

        return list.toArray(new int[list.size()][]);
    }


    public int[][] merge3(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);

    }
}
