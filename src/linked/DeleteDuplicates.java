package linked;

/**
 * 83. 删除排序链表中的重复元素[easy]
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(1);
        ListNode l3=new ListNode(2);
        ListNode l4=new ListNode(3);
        ListNode l5=new ListNode(3);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;

        Solution solution = new Solution();
        ListNode node = solution.deleteDuplicates(l1);
        System.out.println(node);
    }

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head==null) return null;
            ListNode p=head,q=head.next;
            while (q!=null){
                if (q.val!=p.val){
                    p.next=q;
                    p=p.next;
                }
                q=q.next;
            }
            p.next=null;
            return head;
        }
    }
}
