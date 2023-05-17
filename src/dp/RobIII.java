package dp;

import java.util.HashMap;

/**
 * 337. 打家劫舍 III[medium]
 * https://leetcode.cn/problems/house-robber-iii/
 */
public class RobIII {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(4);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(1);
        TreeNode l4 = new TreeNode(3);
        l1.left = l2;
        l2.left = l3;
        l2.right = l4;
        Solution solution = new Solution();
        System.out.println(solution.rob(l1));
    }

    static class Solution {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        public int rob(TreeNode root) {
            return dp(root);
        }

        private int dp(TreeNode root) {
            if (map.containsKey(root)) return map.get(root);
            if (root == null) {
                map.put(root,0);
                return 0;
            }

            //抢该节点,就只能从下下节点继续
            int rob = root.val +
                    (root.left == null ? 0 : dp(root.left.left)+dp(root.left.right)) +
                    (root.right == null ? 0 : dp(root.right.left)+dp(root.right.right));
            //不抢该节点，从下一节点继续
            int noRob = dp(root.left) + dp(root.right);
            int res = Math.max(rob, noRob);
            map.put(root,res);
            return res;
        }

    }

}
