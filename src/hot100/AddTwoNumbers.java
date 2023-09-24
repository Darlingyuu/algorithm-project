package hot100;

/**
 * 2.两数相加[medium]
 * https://leetcode.cn/problems/add-two-numbers/?envType=study-plan-v2&envId=top-100-liked
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.println(solution.addTwoNumbers(l1, l2));
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode node = dummy;
            ListNode p = l1;
            ListNode q = l2;
            int add = 0;
            while (p != null || q != null) {
                int x = p == null ? 0 : p.val;
                int y = q == null ? 0 : q.val;
                node.next = new ListNode((x + y + add) % 10);
                add = (x + y + add) / 10;
                node = node.next;
                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }
            if (add == 1) {
                node.next = new ListNode(1);
            }
            return dummy.next;
        }
    }
}
