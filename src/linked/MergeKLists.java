package linked;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并 K 个升序链表[hard]
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 */
public class MergeKLists {
    public static void main(String[] args) {
//        ListNode l1=new ListNode(1);
//        ListNode l2=new ListNode(4);
//        ListNode l3=new ListNode(5);
//        l1.next=l2;
//        l2.next=l3;
//        ListNode ll1=new ListNode(1);
//        ListNode ll2=new ListNode(3);
//        ListNode ll3=new ListNode(4);
//        ll1.next=ll2;
//        ll2.next=ll3;
//        ListNode lll1=new ListNode(2);
//        ListNode lll2=new ListNode(6);
//        lll1.next=lll2;
//        ListNode[] lists ={l1,ll1,lll1};
        ListNode l1=null;
        ListNode[] lists={l1};

        Solution solution = new Solution();
        ListNode node = solution.mergeKLists(lists);
        System.out.println(node);
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists==null && lists.length == 0) return null;
            ListNode dummy = new ListNode(-1);
            ListNode node=dummy;

            // 力扣不支持lambda表达式，不支持PriorityQueue(int initialCapacity,Comparator<? super E> comparator)
            PriorityQueue<ListNode> queue = new PriorityQueue<>( new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val-o2.val;
                }
            });

            for (ListNode listNode : lists) {
                if (listNode!=null) {
                    queue.add(listNode);
                }
            }

            while (!queue.isEmpty()){
                ListNode listNode = queue.poll();
                node.next=listNode;
                if (listNode.next!=null) {
                    queue.add(listNode.next);
                }
                node=node.next;
            }
            return dummy.next;
        }
    }
}
