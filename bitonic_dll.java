import java.util.*;

class Node {
    int data;
    Node next;
    Node prev;
    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class bitonic_dll {
    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    static Node sort(Node head) {
        if (head == null || head.next == null) return head;
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        Node front = head;
        Node result = null;
        Node tail = null;
        while (front != null && last != null && front != last && last.next != front) {
            Node newNode;
            if (front.data < last.data) {
                newNode = new Node(front.data);
                front = front.next;
            } else {
                newNode = new Node(last.data);
                last = last.prev;
            }
            if (result == null) {
                result = newNode;
                tail = result;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = tail.next;
            }
        }
        while (front != null && front != last.next) {
            Node newNode = new Node(front.data);
            front = front.next;
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
        while (last != null && last.next != front) {
            Node newNode = new Node(last.data);
            last = last.prev;
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<1){
            return;
        }
        Node head = new Node(sc.nextInt());
        Node cur = head;
        for(int i=1; i<n; i++){
            Node newNode = new Node(sc.nextInt());
            cur.next = newNode;
            newNode.prev = cur;
            cur = cur.next;
        }
        head = sort(head);
        printList(head);
    }
}