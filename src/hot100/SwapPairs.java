package hot100;

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/?envType=study-plan-v2&envId=top-100-liked
 * 24. 两两交换链表中的节点[medium]
 */
public class SwapPairs {

    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            ListNode q = dummy;
            dummy.next = head;
            while (p.next != null && p.next.next != null) {
                ListNode temp = p;
                p = p.next;
                q = p.next;
                p.next = q.next;
                q.next = p;
                temp.next = q;
            }
            return dummy.next;
        }
    }
}
