package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Subsets78 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 方法一：迭代法实现子集枚举
     * 思路与算法
     * 记原序列中元素的总数为 nn。原序列中的每个数字 a_ia
     * i的状态可能有两种，即「在子集中」和「不在子集中」。我们用 11 表示「在子集中」，00 表示不在子集中，那么每一个子集可以对应一个长度为 nn 的 0/10/1 序列，第 ii 位表示 a_ia
     * i是否在子集中。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            list.clear();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    list.add(nums[j]);
                }
            }
            result.add(new ArrayList<>(list));
        }

        return result;
    }


    /**
     * 时间复杂度：O(n×2 n)。一共 2^n2
     * n个状态，每种状态需要 O(n)O(n) 的时间来构造子集。
     * 空间复杂度：O(n)O(n)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        dfs(0, nums);
        return result;
    }

    private void dfs(int cur, int [] nums) {
        if (cur == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[cur]);
        dfs(cur + 1, nums);
        list.remove(list.size() - 1);
        dfs(cur + 1, nums);
    }


    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

}
