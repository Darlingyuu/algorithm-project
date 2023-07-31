package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表[medium]
 * https://leetcode.cn/problems/reorder-list/
 */
public class ReorderList {

    class Solution {

        public void reorderList(ListNode head) {
            if (head==null || head.next==null || head.next.next==null) return;

            ListNode p=head;
            // 找到尾结点的前一个节点
            while (p.next.next!=null){
                p=p.next;
            }
            // 新的尾结点
            ListNode newTail=p;
            // q指向原来的尾结点
            p=p.next;
            // 新尾结点指向null
            newTail.next=null;
            // 继续反转的新头节点
            ListNode newHead=head.next;
            // 将原来的尾结点接到头节点后面
            head.next=p;
            // 指向新头节点
            p.next=newHead;
            // 继续反转
            reorderList(newHead);
        }

        // https://leetcode.cn/problems/reorder-list/solutions/452867/zhong-pai-lian-biao-by-leetcode-solution/

        // 线性表
        public void reorderListII(ListNode head) {
            if (head == null) {
                return;
            }
            List<ListNode> list = new ArrayList<ListNode>();
            ListNode node = head;
            while (node != null) {
                list.add(node);
                node = node.next;
            }
            int i = 0, j = list.size() - 1;
            while (i < j) {
                list.get(i).next = list.get(j);
                i++;
                if (i == j) {
                    break;
                }
                list.get(j).next = list.get(i);
                j--;
            }
            list.get(i).next = null;
        }


        // 寻找链表中点 + 链表逆序 + 合并链表
        public void reorderListIII(ListNode head) {
            if (head == null) {
                return;
            }
            ListNode mid = middleNode(head);
            ListNode l1 = head;
            ListNode l2 = mid.next;
            mid.next = null;
            l2 = reverseList(l2);
            mergeList(l1, l2);
        }

        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

        public void mergeList(ListNode l1, ListNode l2) {
            ListNode l1_tmp;
            ListNode l2_tmp;
            while (l1 != null && l2 != null) {
                l1_tmp = l1.next;
                l2_tmp = l2.next;

                l1.next = l2;
                l1 = l1_tmp;

                l2.next = l1;
                l2 = l2_tmp;
            }
        }
    }
}
