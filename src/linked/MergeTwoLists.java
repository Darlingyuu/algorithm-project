package linked;

/**
 * 21. 合并两个有序链表[easy]
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;

        ListNode ll1 = new ListNode(1);
        ListNode ll2 = new ListNode(3);
        ListNode ll3 = new ListNode(4);
        ll1.next = ll2;
        ll2.next = ll3;

        Solution solution = new Solution();
        ListNode node = solution.mergeTwoLists(l1, ll1);
        System.out.println(node);

    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1);
            ListNode node = dummy;
            ListNode p = list1, q = list2;
            while (p != null && q != null) {
                if (p.val <= q.val) {
                    node.next = p;
                    p = p.next;
                } else {
                    node.next = q;
                    q = q.next;
                }

                //node不断后移
                node=node.next;
            }

            if (p != null) node.next = p;
            if (q != null) node.next = q;
            return dummy.next;
        }
    }
}
