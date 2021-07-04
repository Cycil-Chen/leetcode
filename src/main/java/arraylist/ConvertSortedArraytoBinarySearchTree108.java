package arraylist;

/**
 * 108.     Medium   将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 */
public class ConvertSortedArraytoBinarySearchTree108 {
    /**
     * 方法一  中序遍历
     * @param nums
     * @return
     */
    public TreeNode2 sortedArrayToBST(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode2 construct(int [] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择中间位置
        int mid = left + (right - left) / 2;
        // 选择中间位置右边的数字
        // int mid = left + (right - left + 1) / 2;
        // 选择中间任意一个位置
        // int mid = left + (right - left + new Random().nextInt(2)) / 2;
        TreeNode2 root = new TreeNode2(nums[mid]);
        root.left = construct(nums, left, mid - 1);
        root.right = construct(nums, mid + 1, right);
        return root;
    }
}

class TreeNode2 {
      int val;
      TreeNode2 left;
      TreeNode2 right;
      TreeNode2() {}
      TreeNode2(int val) { this.val = val; }
      TreeNode2(int val, TreeNode2 left, TreeNode2 right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}


