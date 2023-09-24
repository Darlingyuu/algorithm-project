package hot100;

/**
 * 234. 回文链表[easy]
 * https://leetcode.cn/problems/palindrome-linked-list/?envType=study-plan-v2&envId=top-100-liked
 */
public class IsPalindrome {
    class Solution {
        public boolean isPalindrome(ListNode head) {
            // 快慢指针，找到中点
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // 偶数个数 fast=null,slow指向后半的第一个
            // 奇数个数 fast指向链尾，slow指向中间节点
            //当链表长度为奇数，令slow指向后半部分的第一个节点，slow前进一步
            if (fast!=null){
                slow=slow.next;
            }
            // 反转slow到链尾的节点
            ListNode newHead = reverse(slow);

            ListNode p = head;
            ListNode q = newHead;
            //两边链表均从头部开始比较
            while (newHead != null) {
                if (head.val != newHead.val) {
                    return false;
                }
                head = head.next;
                newHead = newHead.next;
            }
            return true;
        }

        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode newHead = reverse(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
}
