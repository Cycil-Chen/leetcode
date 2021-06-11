package arraylist;

/**
 * 457.     Medium      环形数组是否存在循环
 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的
 如果 nums[i] 是正数，向前 移动 nums[i] 步
 如果 nums[i] 是负数，向后 移动 nums[i] 步
 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 数组中的 循环 由长度为 k 的下标序列 seq ：
 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 所有 nums[seq[j]] 应当不是 全正 就是 全负
 k > 1
 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 */
public class CircularArrayLoop457 {

    /**
     * 方法一  快慢指针
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 遍历过的环置0，防止重复（可以理解为剪枝）
            if (nums[i] == 0) {
                continue;
            }
            int slow = i;
            int fast = next(nums, i);
            // 全正或全负
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, slow)] > 0 && nums[fast] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    // 环中只有一个节点
                    if (slow == next(nums, slow)) {
                        break;
                    } else {
                        return true;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, fast);
            }
            // 遍历过的环节点置0
            int index = i;
            // 因为环节点是全正或者全负的，所以如果开始是正数，一直判断到出现负数为止，之前遍历的都置0
            while (nums[index] > 0) {
                int temp = next(nums, index);
                nums[index] = 0;
                index = temp;
            }
            index = i;
            // 负数亦然
            while (nums[index] < 0) {
                int temp = next(nums, index);
                nums[index] = 0;
                index = temp;
            }
        }
        return false;
    }

    // 求环的下一个下标
    private int next(int [] nums, int i) {
        int res = (i + nums[i]) % nums.length;
        if (res >= 0) {
            return res;
        } else {
            return res + nums.length;
        }
    }

    //总体访问过节点
    boolean[] sign;
    /**
     * 方法二  递归
     * @param nums
     * @return
     */
    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length;
        //n = 1 时 ，直接违背（k > 1），返回false
        if(n == 1) return false;
        //全局访问数组，记录全局访问过的节点
        sign = new boolean[n];
        for(int i = 0 ; i < n ; i++){
            //若该节点已经在全局数组中记录，则包含该节点的路径都不可能成环
            if(sign[i]) continue;
            //单线访问数组，记录每次以新的节点为起点时访问的节点
            boolean[] bl = new boolean[n];
            if(nums[i] < 0){
                if(dfs2(i , nums , n , i , bl)) return true;
            }else{
                if(dfs1(i , nums , n , i , bl)) return true;
            }
        }
        return false;
    }

    //大于0时走的路径
    private boolean dfs1(int index , int[] nums , int n , int pre , boolean[] bl){
        //计算出正确下标
        if(index >= n) index %= n;
        //若走到点的值小于0，则违背题意（所有 nums[seq[j]] 应当不是 全正 就是 全负），返回false
        if(nums[index] < 0) return false;
        //若在单线中访问到之前访问过的节点，进入判断
        if(bl[index]){
            //是否为自己访问自己，若是，返回false（k > 1）
            if(index == pre) return false;
            //若不是，返回true
            return true;
        }
        if(sign[index]) return false;
        //全局访问过点保存
        sign[index] = true;
        //单线访问点保存
        bl[index] = true;
        //递归访问下一节点
        return dfs1(index + nums[index] , nums , n , index , bl);
    }
    //小于0时走的路径
    private boolean dfs2(int index , int[] nums , int n , int pre , boolean[] bl){
        //计算出正确下标
        if(index < 0) index = n - ((-index) % n);
        //若走到点的值大于0，则违背题意（所有 nums[seq[j]] 应当不是 全正 就是 全负），返回false
        if(nums[index] > 0) return false;
        //若在单线中访问到之前访问过的节点，进入判断
        if(bl[index]){
            //是否为自己访问自己，若是，返回false（k > 1）
            if(index == pre) return false;
            //若不是，返回true
            return true;
        }
        if(sign[index]) return false;
        //全局访问过点保存
        sign[index] = true;
        //单线访问点保存
        bl[index] = true;
        //递归访问下一节点
        return dfs2(index + nums[index] , nums , n , index , bl);
    }
}
