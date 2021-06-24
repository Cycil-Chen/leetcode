package arraylist;

import java.util.HashMap;
import java.util.Map;

/**
 * 621.     Medium      任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 */
public class TaskScheduler621 {
    /**
     * 方法一
     * 解释一下这个公式怎么来的 (count[25] - 1) * (n + 1) + maxCount
     * 假设数组 ["A","A","A","B","B","C"]，n = 2，A的频率最高，记为count = 3，
     * 所以两个A之间必须间隔2个任务，才能满足题意并且是最短时间（两个A的间隔大于2的总时间
     * 必然不是最短），因此执行顺序为： A->X->X->A->X->X->A，这里的X表示除了A以外其他字母，
     * 或者是待命，不用关心具体是什么，反正用来填充两个A的间隔的。上面执行顺序的规律是：
     * 有count - 1个A，其中每个A需要搭配n个X，再加上最后一个A，
     * 所以总时间为 (count - 1) * (n + 1) + 1
     * 要注意可能会出现多个频率相同且都是最高的任务，
     * 比如 ["A","A","A","B","B","B","C","C"]，所以最后会剩下一个A和一个B，
     * 因此最后要加上频率最高的不同任务的个数 maxCount
     * 公式算出的值可能会比数组的长度小，如["A","A","B","B"]，n = 0，此时要取数组的长度
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        // 执行次数最多的任务
        int maxCount = 0;
        for (char ch : tasks) {
            int count = map.getOrDefault(ch, 0) + 1;
            map.put(ch, count);
            maxCount = Math.max(maxCount, count);
        }

        // 具有最多执行次数的任务数量
        int taskCount = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value == maxCount) {
                taskCount++;
            }
        }

        return Math.max((maxCount - 1) * (n + 1) + taskCount, tasks.length);
    }
}
