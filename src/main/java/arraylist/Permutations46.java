package arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46.      Medium      全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Permutations46 {
    /**
     * 方法一  ba
     * 交换数组
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>() ;
        for (int num : nums) {
            list.add(num);
        }
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, list, result, 0);
        return result;
    }


    private void backtrack(int [] nums, List<Integer> list, List<List<Integer>> res, int begin) {
        if (begin == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            // 动态维护数组
            Collections.swap(list, begin, i);
            // 继续递归填下一个数
            backtrack(nums, list, res, begin + 1);
            // 撤销操作
            Collections.swap(list, begin, i);
        }
    }

    /**
     * 方法二  回溯
     * 标记数组
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int [] visited = new int[nums.length];
        backtrack2(nums, new ArrayList<>(), result, visited);
        return result;
    }

    private void backtrack2(int [] nums, List<Integer> list, List<List<Integer>> res, int [] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = 1;
            backtrack2(nums, list, res, visited);
            visited[i] = 0;
            list.remove(list.size() - 1);
        }
    }




    public static void main(String [] args) {
        new Permutations46().permute2(new int[]{1, 2, 3});
    }
}
