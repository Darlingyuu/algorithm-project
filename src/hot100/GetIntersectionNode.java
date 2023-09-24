package hot100;

/**
 * 160. 相交链表[easy]
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&envId=top-100-liked
 */
public class GetIntersectionNode {
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p = headA;
            ListNode q = headB;
            while (p != q) {
                if (p == null) {
                    p = headB;
                } else {
                    p = p.next;
                }

                if (q == null) {
                    q = headA;
                } else {
                    q = q.next;
                }
            }

            return p;
        }
    }
}
