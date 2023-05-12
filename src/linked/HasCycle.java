package linked;

/**
 * 141. 环形链表[easy]
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class HasCycle {
    public static void main(String[] args) {
        ListNode l1=new ListNode(3);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(0);
//        ListNode l4=new ListNode(-4);
        l1.next=l2;
        l2.next=l3;
//        l3.next=l4;

        Solution solution = new Solution();
        System.out.println(solution.hasCycle(l1));
    }

    public static class Solution {
        public boolean hasCycle(ListNode head) {
            // 使用快慢指针
            ListNode p = head,q=head;
            while (q!=null && q.next!=null){
                p=p.next;
                q=q.next.next;

                //如果相遇则说明有环
                if (p==q) return true;
            }

            return false;
        }
    }
}
