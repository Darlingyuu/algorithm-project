package hot100;

/**
 * 108. 将有序数组转换为二叉搜索树[easy]
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class SortedArrayToBST {
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs(nums, 0, nums.length - 1);
        }

        // 返回[lo,hi]范围构建的平衡二叉树的根节点
        private TreeNode dfs(int[] nums, int lo, int hi) {
            if (lo > hi) {
                return null;
            }
            // 以升序数组的中间元素作为根节点
            int mid = lo + (hi - lo) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            // 递归构建左右子树
            root.left = dfs(nums, lo, mid - 1);
            root.right = dfs(nums, mid + 1, hi);
            return root;
        }
    }
}
