package arraylist;

import java.util.*;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class SubsetsII90 {

    /**
     * 方法一
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void dfs(int [] nums, List<List<Integer>> res, List<Integer> list, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[index]);
        // 选择当前的数
        dfs(nums, res, list, index + 1);
        // 回溯 - 将当前添加的数删除
        list.remove(list.size() - 1);
        // 去重，不选当前数递归时直接跳过重复数
        // 所有重复数的可能性在前面递归中均会出现
        while (index < nums.length - 1  && nums[index] == nums[index + 1]) {
            index++;
        }
        dfs(nums, res, list, index + 1);
    }


    /**
     * 方法二：迭代法实现子集枚举
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }
        }
        return ans;
    }


    /**
     * 方法三  for循环
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        dfs3(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void dfs3(int [] nums, List<List<Integer>> result, List<Integer> list, int index) {
        result.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            // i > index 若是用 i > 0 则要用状态标志位 标记是否遍历过
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            list.add(nums[i]);
            dfs3(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }



    public List<List<Integer>> subsetsWithDup4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());// 初始化空数组
        Arrays.sort(nums);
        int start = 1; //保存新解的开始位置
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            // 遍历之前的所有结果
            for (int j = 0; j < ans.size(); j++) {
                List<Integer> list = ans.get(j);
                //如果出现重复数字，就跳过所有旧解
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]); // 加入新增数字
                ans_tmp.add(tmp);
            }

            start = ans.size(); //更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
    }


    /**
     * 方法五  Set去重
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup5(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        dfs5(nums, 0, cur, ans);
        return new ArrayList<>(ans);
    }

    /**
     * @param nums 原输入数组
     * @param u 当前决策到原输入数组中的哪一位
     * @param cur 当前方案
     * @param ans 最终结果集
     */
    void dfs5(int[] nums, int u, List<Integer> cur, Set<List<Integer>> ans) {
        // 所有位置都决策完成，将当前方案放入结果集
        if (nums.length == u) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        // 选择当前位置的元素，往下决策
        cur.add(nums[u]);
        dfs5(nums, u + 1, cur, ans);

        // 不选当前位置的元素（回溯），往下决策
        cur.remove(cur.size() - 1);
        dfs5(nums, u + 1, cur, ans);
    }




    public static void main(String [] args) {
        int [] nums = {1,2,2};
        new SubsetsII90().subsetsWithDup4(nums);
    }
}
