package daily;


/**
 * 141. 环形链表[easy]
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class HasCycle {

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
