package linked;

/**
 * 92. 反转链表 II[medium]
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class ReverseBetween {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        Solution solution = new Solution();
        ListNode node = solution.reverseBetween(l1, 2, 4);
        System.out.println(node);
    }

    static class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (left == 1) {
                return reverseN(head, right);
            }

            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

        //后驱节点
        ListNode successor = null;

        //反转前N个节点
        public ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                successor = head.next;
                return head;
            }

            ListNode last = reverseN(head.next, n - 1);

            head.next.next = head;
            head.next = successor;

            return last;
        }
    }
}
