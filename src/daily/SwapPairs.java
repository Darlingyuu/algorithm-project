package daily;

/**
 * 24. 两两交换链表中的节点[medium]
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        Solution solution = new Solution();
        System.out.println(solution.swapPairs(l1));
    }

    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                ListNode nxt = cur.next;
                cur.next = nxt.next;
                nxt.next = cur;
                pre.next = nxt;
                // 更新
                pre = cur;
                cur = cur.next;
            }
            return dummy.next;
        }
    }
}
