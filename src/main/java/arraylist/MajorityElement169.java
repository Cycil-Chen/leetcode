package arraylist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  169.    Easy    多数元素
 *  给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class MajorityElement169 {
    /**
     * 方法一  哈希表
     * 时间复杂度O(n) 空间复杂度O(n)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = countNums(nums);

        Map.Entry<Integer, Integer> result = null;
        for (Map.Entry<Integer, Integer> entryMap : map.entrySet()) {
            // 先给result初始化，然后逐步比较result中的值，找出次数最多的
            if (result == null || entryMap.getValue() > result.getValue()) {
                result  = entryMap;
            }
        }

        return result.getKey();
    }

    private Map<Integer, Integer> countNums(int [] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (! map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        return map;
    }

    /**
     * 方法二 排序
     * 时间复杂度：O(n\log n)O(nlogn)。将数组排序的时间复杂度为 O(n\log n)O(nlogn)。
     * 空间复杂度：O(\log n)O(logn)
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    /**
     * 方法三  随机化
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        Random rand = new Random();

        while (true) {
            int num = nums[randRange(rand, 0, nums.length)];
            if (countRange(nums, num) > nums.length/2) {
                return num;
            }
        }
    }

    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countRange(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }

        return count;
    }

    /**
     * 方法四  分治
     * 思路
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * 算法
     * 我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
     * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。如果回溯后某区间的长度大于 1，
     * 我们必须将左右子区间的值合并。如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
     * 否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
     * @param nums
     * @return
     */
    public int majorityElement4(int[] nums) {
        return majorityElementResult(nums, 0, nums.length - 1);
    }

    private int majorityElementResult(int [] nums, int left, int right) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (left == right) {
            return nums[left];
        }

        // recurse on left and right halves of this slice.
        int mid = (left + right) / 2;
        int leftNum = majorityElementResult(nums, left, mid);
        int rightNum = majorityElementResult(nums, mid + 1, right);

        // if the two halves agree on the majority element, return it.
        if (leftNum == rightNum) {
            return leftNum;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, right, leftNum);
        int rightCount = countInRange(nums, left, right, rightNum);

        return leftCount > rightCount ? leftNum : rightNum;
    }

    private int countInRange(int [] nums, int left, int right, int num) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) {
                count++;
            }
        }

        return count;
    }

    /**
     * 方法五  Boyer-Moore 投票算法
     * 时间复杂度：O(n)O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     *
     * 思路
     * 如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，
     * 从结果本身我们可以看出众数比其他数多。
     * 算法
     * Boyer-Moore 算法的本质和方法四中的分治十分类似。我们首先给出 Boyer-Moore 算法的详细步骤：
     *
     * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，
     * 我们先将 x 的值赋予 candidate，随后我们判断 x：
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     * 在遍历完成后，candidate 即为整个数组的众数。
     *
     * @param nums
     * @return
     */
    public int majorityElement5(int[] nums) {
        int count = 0;
        int value = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                value = nums[i];
            }

            count += (nums[i] == value) ? 1 : -1;
        }

        return value;
    }


    public int majorityElement8(int[] nums) {
        int cand_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (cand_num == nums[i])
                ++count;
            else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }


    public int majorityElement6(int[] nums) {
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long limit = nums.length >> 1;
        for (Map.Entry<Integer, Long> entry : map.entrySet())
            if (entry.getValue() > limit)
                return entry.getKey();
        return -1;
    }

    public int majorityElement7(int[] nums) {
        int limit = nums.length >> 1;
        HashMap<Integer, Integer> map = new HashMap<>(limit);
        for (int num : nums)
            map.merge(num, 1, (o_val, n_val) -> o_val + n_val);
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (entry.getValue() > limit)
                return entry.getKey();
        return -1;
    }



    public static void main(String [] args) {
        int [] nums = {3,2,3};
        System.out.println(new MajorityElement169().majorityElement4(nums));
    }
}
