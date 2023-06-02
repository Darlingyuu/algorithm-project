package tree;

import java.util.LinkedList;

/**
 * 297. 二叉树的序列化与反序列化[hard]
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 */
public class Codec {
    // 分隔符
    private String SEP=",";
    // null
    private String NULL="#";



    // Encodes a tree to a single string.
    // 序列化
    public String serialize(TreeNode root) {
        // 用于拼接
        StringBuilder sb = new StringBuilder();
        traverse(root,sb);
        return sb.toString();
    }

    private void traverse(TreeNode root,StringBuilder sb) {
        if (root==null){
            sb.append(NULL).append(SEP);
            return ;
        }
        // 前序位置
        sb.append(root.val).append(SEP);
        traverse(root.left,sb);
        traverse(root.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 按分隔符切分
        String[] nodes = data.split(",");
        // 转成列表
        LinkedList<String> list = new LinkedList<>();
        for (String node : nodes) {
            list.add(node);
        }
        return bulid(list);
    }

    // 根据前序遍历结果构建二叉树
    private TreeNode bulid(LinkedList<String> list) {
        // 结束条件
        if (list.isEmpty()) return null;

        // 前序遍历位置
        String first = list.removeFirst();
        // 叶子节点的左右空指针
        if (first.equals(NULL)) return null;

        // 首位就是根节点
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left=bulid(list);
        root.right=bulid(list);
        return root;
    }
}

