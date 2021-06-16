package arraylist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 565.     Medium      数组嵌套
 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，
 其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，
 之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 */
public class ArrayNesting565 {

    /**
     * 方法一
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int maxCount = 0;
        Set<Integer> set = new HashSet<>();
        boolean [] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            int index = i;
            while (index < nums.length && ! set.contains(nums[index])) {
                set.add(nums[index]);
                index = nums[index];
                visited[index] = true;
            }
            maxCount = Math.max(maxCount, set.size());
            set.clear();
        }
        return maxCount;
    }

    /**
     * 方法二  方法一优化
     * 不使用set
     * @param nums
     * @return
     */
    public int arrayNesting2(int[] nums) {
        int maxCount = 1;
        boolean [] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        for (int i = 0; i < nums.length; i++) {
            // 如果一个环的长度超过了数组的一半，直接返回，不会有比它更长的环了
            if (maxCount > nums.length / 2) {
                return maxCount;
            }
            if (visited[nums[i]]) {
                continue;
            }
            int count = 1;
            visited[nums[i]] = true;
            int cur = nums[nums[i]];
            while (nums[i] != cur) {
                count++;
                visited[cur] = true;
                cur = nums[cur];
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

}
