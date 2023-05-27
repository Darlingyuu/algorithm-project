package linked;

/**
 * 25. K 个一组翻转链表[hard]
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        Solution solution = new Solution();
        System.out.println(solution.reverseKGroup(l1, 1));

    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head==null) return head;
            ListNode a, b;
            a = b = head;
            // 反转[a,b),找到b节点
            for (int i = 0; i < k; i++) {
                // 后续节点数不够k个,保持该组原有顺序
                if (b ==null) return head;
                b = b.next;
            }
            // 反转[a.b)部分的链表，返回头节点
            ListNode newHead = reverse(a, b);
            // a是反转后[a,b)部分的尾结点，指向后面反转完成的头节点
            // b是下一次反转组的头节点
            a.next = reverseKGroup(b, k);

            return newHead;
        }

        // 反转以[a,b)部分的链表，返回反转后的头节点
        private ListNode reverse(ListNode a, ListNode b) {
            ListNode pre = null;
            ListNode cur, nxt;
            cur = nxt = a;
            while (cur != b) {
                // 找到下一个节点
                nxt = cur.next;
                // 将当前节点指向前一个节点
                cur.next = pre;
                // 移动pre cur
                pre = cur;
                cur = nxt;
            }
            // 反转结束时，cur，nxt指向b，pre反转后的头节点
            return pre;
        }
    }

}
