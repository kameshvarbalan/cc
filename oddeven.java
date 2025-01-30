import java.util.*;

class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class oddeven {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<1){
            return;
        }
        Node head = new Node(sc.nextInt());
        Node cur = head;
        for(int i=1; i<n; i++){
            cur.next = new Node(sc.nextInt());
            cur = cur.next;
        }
        Node ans = segregate(head);
        while(ans!=null){
            System.out.print(ans.data + " ");
            ans = ans.next;
        }
    }
    private static Node segregate(Node head){
        Node even = new Node(0), odd = new Node(-1);
        Node ans = even, odds = odd;
        while(head!=null){
            if(head.data%2==0){
                even.next = head;
                even = even.next;
            }
            else{
                odd.next = head;
                odd = odd.next;
            }
            head = head.next;
        }
        even.next = odds.next;
        return ans.next;
    }
}
