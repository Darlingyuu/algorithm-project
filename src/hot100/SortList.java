package hot100;

/**
 * https://leetcode.cn/problems/sort-list/?envType=study-plan-v2&envId=top-100-liked
 * 148. 排序链表[medium]
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = ListNode.buildListNodes(new int[]{ });
        Solution solution = new Solution();
        ListNode list = solution.sortList(head);
        System.out.println(list);
    }


    static class Solution {
        // 二分排序
        // 返回排序后的头节点
        public static ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode fast = head.next, slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // 右半部分的头节点
            ListNode tmp = slow.next;
            // 断开左右部分
            slow.next = null;
            // 左半部分排序完毕
            ListNode left = sortList(head);
            // 右半部分排序完毕
            ListNode right = sortList(tmp);
            // 虚拟头节点
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            // 将长的一侧的剩余部分接上
            h.next = left != null ? left : right;
            return res.next;
        }

        // 升序排序，返回排序后的头节点(超时)
//        public static ListNode sortList(ListNode head) {
//            // 结束迭代
//            if (head == null || head.next == null) {
//                return head;
//            }
//
//            ListNode nxtHead = sortList(head.next);
//            if (head.val < nxtHead.val) {
//                head.next = nxtHead;
//                return head;
//            } else {
//                ListNode p = nxtHead;
//                while (p.next != null) {
//                    // 找到head排序后的位置
//                    if (p.val <= head.val && p.next.val > head.val) {
//                        head.next = p.next;
//                        p.next = head;
//                        return nxtHead;
//                    }
//                    p = p.next;
//                }
//                if (p.next == null) {
//                    p.next = head;
//                    head.next = null;
//                }
//                return nxtHead;
//            }
//        }
    }
}
