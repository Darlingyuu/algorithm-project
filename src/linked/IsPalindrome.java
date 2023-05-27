package linked;

/**
 * 234. 回文链表[easy]
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class IsPalindrome {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(2);
//        ListNode l4 = new ListNode(1);
//        ListNode l5 = new ListNode(5);
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next=l5;
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(l1));
    }

    static class Solution {
        public boolean isPalindrome(ListNode head) {
            if (head==null) return false;
            // 使用快慢指针找到中点
            ListNode fast, slow;
            fast = slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            // 偶数个数 fast=null,slow指向后半的第一个
            // 奇数个数 fast指向链尾，slow指向中间节点

            //当链表长度为奇数，令slow指向后半部分的第一个节点，slow前进一步
            if (fast!=null){
                slow=slow.next;
            }
            // 反转slow到链尾的节点
            ListNode newHead = reverse(slow);
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

        // 反转链表，返回反转后的头节点
        private ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode cur, nxt;
            cur = nxt = head;
            while (cur != null) {
                // 找到下一个节点
                nxt = cur.next;
                // 将当前节点指向前一个节点
                cur.next = pre;
                // 移动pre cur
                pre = cur;
                cur = nxt;
            }
            // 反转结束时，cur，nxt指向null，pre反转后的头节点
            return pre;
        }
    }
}
