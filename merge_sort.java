import java.util.Scanner;

class Node {
    int data;
    Node next;
    Node prev;
    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class merge_sort {
    static Node split(Node head) {
        if (head == null || head.next == null) return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null;
        if (secondHalf != null) secondHalf.prev = null;
        return secondHalf;
    }

    static Node merge(Node first, Node second) {
        if (first == null) return second;
        if (second == null) return first;

        if (first.data <= second.data) {
            first.next = merge(first.next, second);
            if (first.next != null) first.next.prev = first;
            return first;
        } else {
            second.next = merge(first, second.next);
            if (second.next != null) second.next.prev = second;
            return second;
        }
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;
        Node secondHalf = split(head);
        head = mergeSort(head);
        secondHalf = mergeSort(secondHalf);
        return merge(head, secondHalf);
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 1) return;
        Node head = new Node(sc.nextInt());
        Node cur = head;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(sc.nextInt());
            cur.next = newNode;
            newNode.prev = cur;
            cur = newNode;
        }
        head = mergeSort(head);
        printList(head);
    }
}
