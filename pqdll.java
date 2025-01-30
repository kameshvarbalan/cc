import java.util.*;

class pqdll {
    static class Node{
        int val, p;
        Node next, prev;
        Node (int data, int p){
            this.val = data;
            this.p = p;
            this.next = null;
            this.prev = null;
        }
    }
    private static Node head =null;
    private static void push(int val, int p){
        if(head==null){
            Node node = new Node(val, p);
            head = node;
            return;
        }
        Node node = new Node(val, p);
        Node temp = head, parent = null;
        while(temp!=null && temp.p>=p){
            parent = temp;
            temp = temp.next;
        }
        if(parent==null){
            node.next = head;
            head.prev = node;
            head = node;
        }
        else if(temp==null){
            parent.next = node;
            node.prev = parent;
        }
        else{
            parent.next = node;
            node.prev = parent;
            node.next = temp;
            temp.prev = node;
        }
    }
    private static int peek(){
        return (head!=null)? head.val: -1;
    }
    private static int pop(){
        if (head!=null){
            int data = head.val;
            head = head.next;
            if(head!=null){
                head.prev = null;
            }
            return data;
        }
        return -1;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            int data = sc.nextInt();
            int p = sc.nextInt();
            push(data, p);
        }
        System.out.println(peek());
        System.out.println(pop());
    }
}