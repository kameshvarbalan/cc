import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class bst_recover {
    static Node first, middle, last, prev;

    static Node buildTree(String[] values) {
        if (values.length == 0 || values[0].equals("N"))
            return null;
        Node root = new Node(Integer.parseInt(values[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            Node curr = queue.poll();
            if (!values[i].equals("N")) {
                curr.left = new Node(Integer.parseInt(values[i]));
                queue.add(curr.left);
            }
            i++;
            if (i >= values.length) break;
            if (!values[i].equals("N")) {
                curr.right = new Node(Integer.parseInt(values[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    static void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && root.data < prev.data) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;

        inorder(root.right);
    }

    static void recoverBST(Node root) {
        first = middle = last = prev = null;

        inorder(root);
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
        else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter level-order input (use 'N' for null):");
        String input = sc.nextLine();
        String[] values = input.split(" ");

        Node root = buildTree(values);

        System.out.println("Inorder Traversal before recovery:");
        printInorder(root);
        System.out.println();

        recoverBST(root);

        System.out.println("Inorder Traversal after recovery:");
        printInorder(root);
        sc.close();
    }
}
