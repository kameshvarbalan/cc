import java.util.*;

public class views {
    static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

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


    static void leftView(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (i == 0) System.out.print(curr.data + " ");
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
    }

    static void rightView(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (i == size - 1) System.out.print(curr.data + " ");
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
    }


    static void topView(Node root) {
        if (root == null) return;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node.data);
            }
            if (curr.node.left != null) queue.add(new Pair(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.add(new Pair(curr.node.right, curr.hd + 1));
        }

        for (int value : map.values()) {
            System.out.print(value + " ");
        }
    }

    static void bottomView(Node root) {
        if (root == null) return;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            map.put(curr.hd, curr.node.data);
            if (curr.node.left != null) queue.add(new Pair(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.add(new Pair(curr.node.right, curr.hd + 1));
        }
        for (int value : map.values()) {
            System.out.print(value + " ");
        }
    }

    static class Pair {
        Node node;
        int hd;
        Pair(Node n, int h) {
            node = n;
            hd = h;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter level-order input (use 'N' for null):");
        String input = sc.nextLine();
        String[] values = input.split(" ");
        Node root = buildTree(values);
        System.out.println("Left View:");
        leftView(root);
        System.out.println("\nRight View:");
        rightView(root);
        System.out.println("\nTop View:");
        topView(root);
        System.out.println("\nBottom View:");
        bottomView(root);
        sc.close();
    }
}
