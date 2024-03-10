package hot100;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并 K 个升序链表[hard]
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class MergeKLists {
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode dummy = new ListNode(-1);
            ListNode node = dummy;
            PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            });

            for (ListNode list : lists) {
                if (list != null) {
                    // 将所有头节点放入队列中国
                    queue.add(list);
                }
            }
            // 小顶堆，依次取出
            while (!queue.isEmpty()) {
                ListNode listNode = queue.poll();
                node.next = listNode;
                if (listNode.next != null) {
                    // 将该节点的下一个节点放入队列
                    queue.add(listNode.next);
                }
                node = node.next;
            }
            return dummy.next;
        }
    }

}
