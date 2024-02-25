package hot100;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-100-liked
 * 19. 删除链表的倒数第 N 个结点[medium]
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

    }

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy=new ListNode(-1);
            ListNode l=dummy;
            ListNode r=dummy;
            dummy.next=head;

            for (int i = 0; i < n; i++) {
                r=r.next;
            }

            while (r.next!=null){
                l=l.next;
                r=r.next;
            }
            // 此时l指向要删除的节点的前一个节点
            l.next=l.next.next;
            return dummy.next;
        }
    }
}
