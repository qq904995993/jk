package jk.mqtt;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    public static void main(String[] as) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        mergeTwoLists(l1, l2);
    }

}
