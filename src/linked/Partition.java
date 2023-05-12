package linked;

/**
 * 86. 分隔链表[medium]
 * https://leetcode.cn/problems/partition-list/
 */
public class Partition {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        Solution solution = new Solution();
        ListNode node = solution.partition(l1, 3);
        System.out.println(node);
    }

    static class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head==null) return null;
            ListNode pre = new ListNode(-1);
            ListNode nxt = new ListNode(-1);

            ListNode p = pre,q=nxt,node=head;
            while (node!=null){
                if (node.val<x){
                    p.next=node;
                    p=node;
                    node=node.next;
                    p.next=null;
                }else {
                    q.next=node;
                    q=node;
                    node=node.next;
                    q.next=null;
                }
            }
            p.next=nxt.next;
            return pre.next;
        }
    }
}
