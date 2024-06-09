package hot100;

import java.util.HashMap;

/**
 * 437. 路径总和 III[medium]
 * https://leetcode.cn/problems/path-sum-iii/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class PathSum {
    class Solution {
        // 统计路径数量
        private int ans;

        public int pathSum(TreeNode root, int targetSum) {
            // 统计前缀和的出现次数
            HashMap<Long, Integer> cnt = new HashMap<>();
            cnt.put(0L, 1);
            dfs(root, 0, targetSum, cnt);
            return ans;
        }

        private void dfs(TreeNode node, long s, int targetSum, HashMap<Long, Integer> cnt) {
            if (node == null) {
                return;
            }

            // s为从root到达该节点的路径长度
            s += node.val;
            // 目标路径长度为targetSum，若到达该节点之前有cnt[s-targetSum]条路径的长度均为s-targetSum，则这些路径的终点到达该节点的路径为targetSum
            // 累计符合长度的路径数量
            ans += cnt.getOrDefault(s - targetSum, 0);
            // 如果 key 对应的 value 不存在，则返回该 value 值，如果存在，则返回通过 remappingFunction 重新计算后的值。
            cnt.merge(s, 1, Integer::sum);
            dfs(node.left, s, targetSum, cnt);
            dfs(node.right, s, targetSum, cnt);
            // 恢复现场，确保回到当前node的根节点时向右子树递归cnt中不包含左子树数据
            cnt.merge(s, -1, Integer::sum);
        }
    }
}
