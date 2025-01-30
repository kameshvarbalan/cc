import java.util.*;

class Node {
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class loop {
   public static void main(String[] var0) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      if (n < 1) {
         System.out.println("No Cycle");
      } 
      else {
         Node head = new Node(sc.nextInt());
         Node cur = head;
         for(int i = 1; i<n ; i++) {
            cur.next = new Node(sc.nextInt());
            cur = cur.next;
         }
         int pos = sc.nextInt();
         if (pos > 0 && pos < n) {
            Node loop = head;
            for(int i = 1; i < pos; i++) {
               loop = loop.next;
            }
            cur.next = loop;
         }
         detect_loop(head);
         sc.close();
      }
   }

   private static void detect_loop(Node head) {
      Node slow = head, fast = head;
      while(fast != null && fast.next != null) {
         slow = slow.next;
         fast = fast.next.next;
         if (slow==fast) {
            System.out.println("Loop Detected");
            return;
         }
      }
      System.out.println("No Loop Detected");
   }
}
