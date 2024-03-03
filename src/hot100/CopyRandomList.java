package hot100;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-100-liked
 * 138. 随机链表的复制[medium]
 */
public class CopyRandomList {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            // 记录每个节点的值和对应的新节点
            HashMap<Integer,Node> nodes = new HashMap<>();
            // 记录原Node及下标
            HashMap<Node, Integer> map = new HashMap<>();
            Node p = head;
            int i = 0;
            while (p != null) {
                map.put(p, i);
                nodes.put(i , new Node(p.val));
                i++;
                p = p.next;
            }
            Node q = nodes.get(0);
            p = head;
            while (p != null) {
                if (p.next!=null){
                    q.next = nodes.get(map.get(p.next));
                }else {
                    q.next = null;
                }
                if (p.random!=null){
                    q.random = nodes.get(map.get(p.random));
                }else {
                    q.random = null;
                }
                p=p.next;
                q=q.next;
            }
            return nodes.get(0);
        }
    }
}
