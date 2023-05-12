package linked;

/**
 * 19. 删除链表的倒数第 N 个结点[medium]
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
//        ListNode l2=new ListNode(2);
//        ListNode l3=new ListNode(3);
//        ListNode l4=new ListNode(4);
//        ListNode l5=new ListNode(5);
//        l1.next=l2;
//        l2.next=l3;
//        l3.next=l4;
//        l4.next=l5;
        Solution solution = new Solution();
        ListNode node = solution.removeNthFromEnd(l1, 1);
        System.out.println(node);
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next=head;
            ListNode p=dummy,q=dummy;
            //p 先走n步
            for (int i = 1; i <=n ; i++) {
                p=p.next;
            }

            //p q同时走直到p到达链表尾部
            while (p.next!=null){
                p=p.next;
                q=q.next;
            }

            //此时q指向倒数n+1个节点,即要删除节点的前一个节点
            q.next=q.next.next;
            return dummy.next;
        }
    }
}
