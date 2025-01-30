import java.util.*;

class minStack {
    Stack<Integer> stack, min;
    public minStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }
    public void push(int val) {
        stack.push(val);
        if(min.isEmpty() || val<=min.peek()){
            min.push(val);
        }
    }
    public void pop() {
        if (stack.isEmpty()) return;
        if(!min.isEmpty() && min.peek()==stack.peek()) min.pop();
        stack.pop();
    }
    public int top() {
        return (!stack.isEmpty())? stack.peek():-1;
    }
    public int getMin() {
        return (!min.isEmpty())? min.peek():-1;
    }
}

class min_stack {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String[] ops = sc.nextLine().split(" ");
        String[] vals = sc.nextLine().split(" ");
        minStack s = new minStack();
        for(int i=0; i<ops.length; i++){
            String op = ops[i];
            switch(op){
                case "push":
                {
                    s.push(Integer.parseInt(vals[i]));
                    break;
                }
                case "pop":
                {
                    s.pop();
                    break;
                }
                case "top":
                {
                    System.out.println(s.top());
                    break;
                }
                case "getMin":
                {
                    System.out.println(s.getMin());
                    break;
                }
                default: System.out.println("Error");
            }
        }
    }
}