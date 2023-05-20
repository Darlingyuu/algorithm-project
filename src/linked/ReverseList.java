package linked;

/**
 * 206. 反转链表[easy]
 * https://leetcode.cn/problems/reverse-linked-list/description/
 */
public class ReverseList {
    public static void main(String[] args) {

    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head==null || head.next==null){
                return head;
            }

            ListNode last = reverseList(head.next);
            head.next.next=head;
            head.next=null;
            return last;
        }
    }
}
