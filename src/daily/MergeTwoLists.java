package daily;

/**
 * 21. 合并两个有序链表[easy]
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class MergeTwoLists {
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1);
            ListNode node=dummy;
            ListNode p = list1;
            ListNode q =list2;
            while (p!=null && q!=null){
                if (p.val<=q.val){
                    node.next=p;
                    p=p.next;
                }else {
                    node.next=q;
                    q=q.next;
                }
                node=node.next;
            }

            if (p!=null){
                node.next=p;
            }
            if (q!=null){
                node.next=q;
            }
            return dummy.next;
        }
    }
}
