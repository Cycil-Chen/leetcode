package arraylist;

import java.util.Map;
import java.util.Stack;

/**
 * 106.     Medium      从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 */

public class InorderandPostorderTraversalToBinaryTree106 {

    private Map<Integer, Integer> map;

    /**
     * 方法一  递归
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return myBuildTree(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode myBuildTree(int [] inOrder, int [] postOrder, int in_left, int in_right, int post_left, int post_right) {
        // 边界条件
        if (post_left > post_right) {
            return null;
        }

        // 找到根在中序遍历对应坐标
        int in_root = map.get(postOrder[post_right]);
        // 构建根节点对应数
        TreeNode node = new TreeNode(postOrder[post_right]);
        // 获取中序遍历根左边对应数量
        int in_left_num = in_root - in_left;

        // 构建左子树
        node.left = myBuildTree(inOrder, postOrder, in_left, in_root - 1, post_left, post_left + in_left_num - 1);
        // 构建右子树
        node.right = myBuildTree(inOrder, postOrder, in_root + 1, in_right, post_left + in_left_num, post_right - 1);
        return node;
    }

    /**
     * 方法二  迭代
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int index = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            TreeNode node = stack.peek();
            // 当前节点值和中序遍历数组值不相等，构造右子树
            if (node.val != inorder[index]) {
                node.right = new TreeNode(postorder[i]);
                stack.push(node.right);
            } else {
                while (! stack.isEmpty() && stack.peek().val == inorder[index]) {
                    node = stack.pop();
                    index--;
                }

                node.left = new TreeNode(postorder[i]);
                stack.push(node.left);
            }
        }
        return root;
    }

}


