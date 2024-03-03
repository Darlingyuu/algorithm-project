package hot100;

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static ListNode buildListNodes(int[] arr){
        ListNode dummy = new ListNode(-1);
        ListNode p=dummy;
        for (int i : arr) {
            p.next=new ListNode(i);
            p=p.next;
        }
        return dummy.next;
    }
}
