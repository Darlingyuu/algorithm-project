package tree;

/**
 * 116. 填充每个节点的下一个右侧节点指针[medium]
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
 */
public class Connect {
    public static void main(String[] args) {

    }


    class Solution {
        public Node connectII(Node root) {
            if (root==null) return root;
            // 连接两个节点
            connectTow(root.left,root.right);
            return root;
        }

        // 利用递归，定义该方法为连接node1 node2
        private void connectTow(Node node1, Node node2) {
            if (node1==null || node2==null){
                return;
            }
            // 前序位置
            node1.next=node2;
            // 要连接 node1的左子节点连node1的右子节点
            connectTow(node1.left,node1.right);
            // 要连接 node1的右子节点连node2的左子节点
            connectTow(node1.right,node2.left);
            // 要连接 node2的左子节点连node1的右子节点
            connectTow(node2.left,node2.right);
        }




        public Node connect(Node root) {
            // 到达叶子节点
            if (root==null || (root.left==null && root.right==null)) return root;

            Node left = connect(root.left);
            Node right = connect(root.right);
            left.next=right;
            // 找到左子树的右节点连接右子树的左结点
            Node p=left.right;
            Node q=right.left;
            while (p!=null && q!=null){
                p.next=q;
                p=p.right;
                q=q.left;

            }

            return root;
        }
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


}
