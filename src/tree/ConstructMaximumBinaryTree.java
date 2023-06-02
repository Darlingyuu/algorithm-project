package tree;

/**
 * 654. 最大二叉树[medium]
 * https://leetcode.cn/problems/maximum-binary-tree/
 */
public class ConstructMaximumBinaryTree {
    public static void main(String[] args) {
        int[] nums={3,2,1,6,0,5};
        Solution solution = new Solution();
        System.out.println(solution.constructMaximumBinaryTree(nums));
    }

    static class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums,0,nums.length-1);

        }

        private TreeNode build(int[] nums,int start, int end) {
            // 结束条件
            if (start>end) return null;

            // 获取最大值及下标
            int[] num=getMax(nums,start,end);
            // 构造根节点
            TreeNode root = new TreeNode(num[0]);
            // 左节点是 [start,num[1]-1]范围内的最大值
            root.left=build(nums,start,num[1]-1);
            // 右节点是 [num[1]+1,end]范围内的最大值
            root.right=build(nums, num[1]+1, end);
            return root;
        }

        private int[] getMax(int[] nums, int start, int end) {
            int max=Integer.MIN_VALUE;
            int index=-1;
            for (int i = start; i <= end; i++) {
                if (nums[i]>max){
                    max=nums[i];
                    index=i;
                }
            }
            return new int[]{max,index};

        }
    }
}
