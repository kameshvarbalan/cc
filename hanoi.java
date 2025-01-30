import java.util.*;

class hanoi {
    static void move(Stack<Integer> from, Stack<Integer> to, char fromRod, char toRod) {
        if (from.isEmpty()) {
            from.push(to.pop());
            System.out.println("Move disk " + from.peek() + " from " + toRod + " to " + fromRod);
        } else if (to.isEmpty() || from.peek() < to.peek()) {
            to.push(from.pop());
            System.out.println("Move disk " + to.peek() + " from " + fromRod + " to " + toRod);
        } else {
            from.push(to.pop());
            System.out.println("Move disk " + from.peek() + " from " + toRod + " to " + fromRod);
        }
    }

    static void towerOfHanoi(int n) {
        Stack<Integer> A = new Stack<>(), B = new Stack<>(), C = new Stack<>();
        for (int i = n; i >= 1; i--) A.push(i);

        char src = 'A', aux = 'B', dest = 'C';
        if (n % 2 == 0) { char temp = dest; dest = aux; aux = temp; }

        for (int i = 1, moves = (1 << n) - 1; i <= moves; i++) {
            if (i % 3 == 1) move(A, C, src, dest);
            else if (i % 3 == 2) move(A, B, src, aux);
            else move(B, C, aux, dest);
        }
    }

    public static void main(String[] args) {
        towerOfHanoi(4);
    }
}
