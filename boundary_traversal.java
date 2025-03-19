import java.util.*;

class Node {
    int data;
    Node left, right;
    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class boundary_traversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        sc.close();
        Node root = build(s);
        printBoundary(root);
    }

    static Node build(String s[]) {
        if (s.length == 0 || s[0].equals("N"))
            return null;

        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            Node curr = q.poll();
            String cval = s[i];

            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.left = new Node(h);
                q.add(curr.left);
            }
            i++;

            if (i >= s.length)
                break;

            cval = s[i];
            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.right = new Node(h);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    static void printLeaves(Node node) {
        if (node == null)
            return;
        printLeaves(node.left);
        if (node.left == null && node.right == null)
            System.out.print(node.data + " ");
        printLeaves(node.right);
    }

    static void printBoundaryLeft(Node node) {
        if (node == null)
            return;
        if (node.left != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.right);
        }
    }

    static void printBoundaryRight(Node node) {
        if (node == null)
            return;
        if (node.right != null) {
            printBoundaryRight(node.right);
            System.out.print(node.data + " ");
        } else if (node.left != null) {
            printBoundaryRight(node.left);
            System.out.print(node.data + " ");
        }
    }

    static void printBoundary(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printBoundaryLeft(node.left);
        printLeaves(node.left);
        printLeaves(node.right);
        printBoundaryRight(node.right);
    }
}
