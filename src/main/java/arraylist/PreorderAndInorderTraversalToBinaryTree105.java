package arraylist;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 105.     Medium      从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 */
public class PreorderAndInorderTraversalToBinaryTree105 {

    // 构造中序遍历的hash映射
    private Map<Integer, Integer> map;

    /**
     * 方法一  递归
     * 时间复杂度：O(n)，其中 n 是树中的节点个数。
       空间复杂度：O(n)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int [] preOrder, int [] inOrder, int pre_left, int pre_right, int in_left, int in_right) {
        // 边界条件
        if (pre_left > pre_right) {
            return null;
        }

        // 前序遍历第一个节点就是根节点
//        int pre_root = pre_left;
        // 根在中序遍历中的坐标位置
        int in_root = map.get(preOrder[pre_left]);
        // 建立根节点的树
        TreeNode treeNode = new TreeNode(preOrder[pre_left]);
        // 中序遍历根节点左边的数量
        int in_left_num = in_root - in_left;

        // 递归构造左子树
        treeNode.left = myBuildTree(preOrder, inOrder, pre_left + 1, pre_left + in_left_num, in_left, in_root - 1);
        // 递归构造右子树
        treeNode.right = myBuildTree(preOrder, inOrder, pre_left + in_left_num + 1, pre_right, in_root + 1, in_right);
        return treeNode;
    }


    /**
     * 方法二  不用hashMap
     * 不再从中序遍历中寻找根节点的位置，而是直接把值传过去，表明当前子树的结束点
     * Consider the example again. Instead of finding the 1 in inorder, splitting the arrays into
     * parts and recursing on them, just recurse on the full remaining arrays and stop when you
     * come across the 1 in inorder. That's what my above solution does. Each recursive call gets
     * told where to stop, and it tells its subcalls where to stop. It gives its own root value as
     * stopper to its left subcall and its parent`s stopper as stopper to its right subcall.
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder,  inorder, (long)Integer.MAX_VALUE + 1);
    }
    int pre = 0;
    int in = 0;
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
        //到达末尾返回 null
        if(pre == preorder.length){
            return null;
        }
        //到达停止点返回 null
        //当前停止点已经用了，in 后移
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        int root_val = preorder[pre++];
        TreeNode root = new TreeNode(root_val);
        //左子树的停止点是当前的根节点
        root.left = buildTreeHelper(preorder,  inorder, root_val);
        //右子树的停止点是当前树的停止点
        root.right = buildTreeHelper(preorder, inorder, stop);
        return root;
    }

    /**
     * 方法三  迭代
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int index = 0;
        for (int i = 1; i < preorder.length; i++) {
            // 重要，每次循环更新为当前栈顶元素
            TreeNode node = stack.peek();
            // 当前节点的值和中序遍历数组的值不相等，一直构造左子树
            if (node.val != inorder[index]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            } else {
                // 相等，需要寻找当前值是谁的右子树
                // 每次出栈，实现倒序遍历
                while (! stack.isEmpty() && stack.peek().val == inorder[index]) {
                    node = stack.pop();
                    index++;
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }
        return root;
    }

}


 /** Definition for a binary tree node. */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

