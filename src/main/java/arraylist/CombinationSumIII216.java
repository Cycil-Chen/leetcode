package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 216.     Medium      组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，
 * 并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 */
public class CombinationSumIII216 {

    /**
     * 方法一  回溯
     * 每次递减判断
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(k, n, list, result, 1);
        return result;
    }

    private void dfs(int k, int n, List<Integer> list, List<List<Integer>> result, int start) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(list));
            return;
        } else if (k < 0 || n < start) {
            // n < start说明后面不可能有值加起来等于和了
            return;
        }

        for (int i = start; i <= 9; i++) {
            // 剪枝
            if (i > n) {
                break;
            }
            list.add(i);
            // 数量减一，和减去i
            dfs(k - 1, n - i, list, result, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法二  回溯
     * 每次递增判断
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum4(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs2(k, n, temp, res, 1);
        return res;
    }

    private void dfs2(int k, int target, List<Integer> temp, List<List<Integer>> res, int begin) {
        if (temp.size() == k && target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = begin; i < 10; i++) {
            // 剪枝
            if (target - i < 0) {
                break;
            }

            temp.add(i);
            dfs2(k, target - i, temp, res, i + 1);
            temp.remove(temp.size() - 1);
        }
    }


}
