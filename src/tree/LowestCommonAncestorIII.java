package tree;

/**
 * 1650.二叉树的最近公共祖先 III[medium]
 * 给你输入一棵存在于二叉树中的两个节点p和q，请你返回它们的最近公共祖先
 * (由于节点中包含父节点的指针，所以二叉树的根节点就没必要输入了)
 */
public class LowestCommonAncestorIII {

    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    };

    class Solution {
        public Node lowestCommonAncestor(Node p, Node q) {
            // 施展链表双指针技巧
            Node a = p, b = q;
            while (a != b) {
                // a 走一步，如果走到根节点，转到 q 节点
                if (a == null) a = q;
                else           a = a.parent;
                // b 走一步，如果走到根节点，转到 p 节点
                if (b == null) b = p;
                else           b = b.parent;
            }
            return a;
        }
    }


}
