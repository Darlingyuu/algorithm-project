package hot100;

/**
 * 142. 环形链表 II[medium]
 * https://leetcode.cn/problems/linked-list-cycle-ii/?envType=study-plan-v2&envId=top-100-liked
 */
public class DetectCycle {
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;

            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    slow = head;
                    break;
                }
            }

            // 无环
            if (fast == null || fast.next == null) {
                return null;
            }

            while (slow != head) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
}
