package arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 77.  medium  组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Combinations77 {
    /**
     * 方法一  递归 + 回溯
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, new ArrayList<>(), res, 1);
        return res;
    }

    private void backtrack(int n, int k, List<Integer> list, List<List<Integer>> res, int begin) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i <= n; i++) {
            list.add(i);
            // 用i 不用 begin， 因为再一次循环begin还是原来的，比如1，但是i 会随着循环增加
            backtrack(n, k, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法二  纯回溯
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, new ArrayList<>(), res, 1);
        return res;
    }

    private void dfs(int n, int k, List<Integer> list, List<List<Integer>> res, int begin) {
        // 结束条件 list的长度加上目前begin到n的长度，小于k，说明不可能构造出长度k的list了
        if (list.size() + (n - begin + 1) < k) {
            return;
        }

        // 记录答案
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 考虑当前位置
        list.add(begin);
        dfs(n, k, list, res, begin + 1);
        list.remove(list.size() - 1);
        // 不考虑当前位置
        dfs(n, k, list, res, begin + 1);
    }


    public static void main(String [] args) {
        List<List<Integer>> list = new Combinations77().combine2(4, 2);
        System.out.println(list);
    }
}
