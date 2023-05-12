package linked;

/**
 * 160. 相交链表[easy]
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(8);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode ll1 = new ListNode(5);
        ListNode ll2 = new ListNode(6);
        ListNode ll3 = new ListNode(1);
        ll1.next=ll2;
        ll2.next=ll3;
        ll3.next=l3;

//        ListNode l1 = new ListNode(3);
//        ListNode ll1 = new ListNode(2);
//        ll1.next=l1;

//        ListNode l1 = new ListNode(3);
//        ListNode l2 = new ListNode(4);
//        l1.next=l2;
//        ListNode ll1 = new ListNode(2);
//        ListNode ll2 = new ListNode(5);
//        ListNode ll3 = new ListNode(6);
//        ll1.next = ll2;
//        ll2.next = ll3;


        Solution solution = new Solution();
        ListNode node = solution.getIntersectionNodeII(l1, ll1);
        System.out.println(node);

    }

    public static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == headB) return headA;
            ListNode p = headA;
            ListNode q = headB;

            boolean flagA=true;
            boolean flagB=true;
            while (p != q) {
                p = p.next;
                q = q.next;
                if (p == null && flagA) {
                    p = headB;
                    flagA=false;
                }
                if (q == null && flagB) {
                    q = headA;
                    flagB=false;
                }
                if (p == q) {
                    return p;
                }
            }
            return null;
        }

        public ListNode getIntersectionNodeII(ListNode headA, ListNode headB) {
            ListNode p = headA;
            ListNode q = headB;

            while (p != q) {
                if (p==null){
                    p=headB;
                }else {
                    p=p.next;
                }

                if (q==null){
                    q=headA;
                }else {
                    q=q.next;
                }
            }
            return p;
        }
    }
}
