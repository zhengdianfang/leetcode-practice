package com.zhengdianfang.node;

public class LeetCodeNode {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + (next == null ? "" : next.toString()) +
                    '}';
        }
    }
    public static void main(String[] args) {
        ListNode head = initData();

        new LeetCodeNode().removeNthFromEnd(head, 2);
        System.out.println(head);
    }

    private static ListNode initData() {
        int[] numbers = new int[]{4, 5, 1, 9};
        ListNode head = new ListNode(numbers[0]);
        ListNode next = head;
        for (int i = 1; i < numbers.length; i++) {
            ListNode listNode = new ListNode(numbers[i]);
            next.next = listNode;
            next = listNode;
        }
        return head;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head.next;
        ListNode slow = head.next;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

}
