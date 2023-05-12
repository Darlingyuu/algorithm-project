package linked;

/**
 * 876. 链表的中间结点[easy]
 * https://leetcode.cn/problems/middle-of-the-linked-list/
 */
public class MiddleNode {
    public static void main(String[] args) {

    }

    static class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode p=head,q=head;
            while (q!=null && q.next!=null){
                p=p.next;
                q=q.next.next;
            }
            return p;
        }
    }
}
