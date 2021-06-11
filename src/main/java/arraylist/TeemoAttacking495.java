package arraylist;

/**
 * 495.     Easy    提莫攻击
 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。
 你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
 */
public class TeemoAttacking495 {

    /**
     * 方法一
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int total = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i] + duration <= timeSeries[i + 1]) {
                total += duration;
            } else {
                total += (timeSeries[i + 1] - timeSeries[i]);
            }
        }
        total += duration;
        return total;
    }

    /**
     * 方法二
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration2(int[] timeSeries, int duration) {
        int total = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            total += Math.min(timeSeries[i+1] - timeSeries[i], duration);
        }
        total += duration;
        return total;
    }
}
