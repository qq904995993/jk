//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    //mine
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List list = new LinkedList();
        while(l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }

        if(list.size() < 1) {
            return null;
        }

        Collections.sort(list);
        ListNode node = new ListNode((int) list.get(0));
        ListNode temp = node;
        for(int i = 1; i < list.size(); i ++) {
            ListNode next = new ListNode((int) list.get(i));
            temp.next = next;
            temp = next;
        }

        return node;
    }

    //their
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l2 == null) return l1;
//        if (l1 == null) return l2;
//        ListNode p1 = l1;
//        ListNode p2 = l2;
//        ListNode start = l1.val < l2.val ? l1 : l2;
//        do {
//            if (p2.val <= p1.val) {
//                while (p2.next != null && p2.next.val <= p1.val) {
//                    p2 = p2.next;
//                }
//                l2 = p2.next;
//                p2.next = p1;
//            } else {
//                while (p1.next != null && p1.next.val < p2.val) {
//                    p1 = p1.next;
//                }
//                l2 = p2.next;
//                p2.next = p1.next;
//                p1.next = p2;
//            }
//            p2 = l2;
//        } while (p2 != null);
//        return start;
//    }

//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        // 类似归并排序中的合并过程
//        ListNode dummyHead = new ListNode(0);
//        ListNode cur = dummyHead;
//        while (l1 != null && l2 != null) {
//            if (l1.val < l2.val) {
//                cur.next = l1;
//                cur = cur.next;
//                l1 = l1.next;
//            } else {
//                cur.next = l2;
//                cur = cur.next;
//                l2 = l2.next;
//            }
//        }
//        // 任一为空，直接连接另一条链表
//        if (l1 == null) {
//            cur.next = l2;
//        } else {
//            cur.next = l1;
//        }
//        return dummyHead.next;
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
