package daily;

import java.util.HashMap;

/**
 * 1171. 从链表中删去总和值为零的连续节点[medium]
 * https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
 */
public class RemoveZeroSumSublists {

    class Solution {
        public ListNode removeZeroSumSublists(ListNode head) {
            // 虚拟节点
            ListNode dummy = new ListNode(0);
            dummy.next=head;

            // key=前缀和  value=节点  从头节点到达value该节点时前缀和为key
            HashMap<Integer, ListNode> map = new HashMap<>();
            // 记录前缀和
            int prefix=0;
            // 如果相同前缀和已经存在，就可以直接覆盖掉原有节点
            for (ListNode node=dummy;node!=null;node=node.next){
                prefix+=node.val;
                map.put(prefix,node);
            }

            prefix=0;
            // 第二遍重新遍历链表，同时记录前缀和prefix，哈希表中对应prefix的节点是最后一次出现相同前缀和的节点，
            // 我们将这个节点的下一个节点，赋值给当前节点的下一个节点，中间跳过的部分总和即为 0。
            for (ListNode node = dummy; node != null; node = node.next) {
                prefix += node.val;
                node.next = map.get(prefix).next;
            }
            return dummy.next;
        }

    }
}
