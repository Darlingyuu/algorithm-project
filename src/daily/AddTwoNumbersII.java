package daily;

import java.util.LinkedList;

/**
 * 445. 两数相加 II[medium]
 * https://leetcode.cn/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(4);
//        l1.next.next.next = new ListNode(3);
        ListNode l2=new ListNode(5);
//        l2.next=new ListNode(6);
//        l2.next.next=new ListNode(4);
        Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(l1, l2));
    }
    static class Solution {
        // 栈
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            LinkedList<Integer> p = new LinkedList();
            LinkedList<Integer> q = new LinkedList();
            while (l1!=null){
                p.add(l1.val);
                l1=l1.next;
            }
            while (l2!=null){
                q.add(l2.val);
                l2=l2.next;
            }

            // 记录进阶
            int add=0;
            // 从后向前拼接链表，head指向头节点
            ListNode head=null;
            while (!p.isEmpty() || !q.isEmpty()){
                // 取出最后的元素
                Integer m = p.isEmpty()?0:p.removeLast();
                Integer n = q.isEmpty()?0:q.removeLast();
                // 当前节点数字,加上之前的进阶，取个位
                int val = (m + n + add) % 10;
                // 下一位的进阶
                add=(m + n + add) / 10;
                ListNode listNode = new ListNode(val);
                listNode.next=head;
                head=listNode;
            }
            // 处理遍历结束还存在进阶的情况
            if (add!=0){
                ListNode listNode = new ListNode(add);
                listNode.next=head;
                head=listNode;
            }

            return head;
        }


        // 反转链表
        public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
            l1 = reverseList(l1);
            l2 = reverseList(l2); // l1 和 l2 反转后，就变成【2. 两数相加】了

            AddTwoNumbers.Solution solution = new AddTwoNumbers.Solution();
            ListNode l3 = solution.addTwoNumbers(l1, l2);
            return reverseList(l3);
        }
        private ListNode reverseList(ListNode head) {
            ListNode pre = null, cur = head;
            while (cur != null) {
                ListNode nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
            }
            return pre;
        }
    }
}
