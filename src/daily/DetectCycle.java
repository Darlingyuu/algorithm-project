package daily;


/**
 * 142.环形链表II[medium]
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class DetectCycle {

    public static class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;

            ListNode p = head, q = head;
            // 使用后快慢指针找到相遇处
            while (q != null && q.next != null) {
                p = p.next;
                q = q.next.next;

                //相遇
                if (p == q) {
                /*
                    假设入环点与相遇点的距离为k
                    head到入环点的距离为m
                    q的路程则为m+k,p的路程为head到入环点+环的其余长度（m+k+k+ 环的其余长度）
                    则环的其余长度为m
                    所以，相遇后，令p指向dummy，p q相同速度前进，相遇时就是入环点（走了m）
                    */
                    p = head;
                    break;
                }
            }

            //p q未相遇 不存在环
            if (q == null || q.next == null) {
                return null;
            }


            while (p != q) {
                p = p.next;
                q = q.next;
            }
            return p;
        }
    }
}
