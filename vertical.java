import java.util.*;

class vertical {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Helper class for BFS traversal (stores node and its horizontal distance)
    static class Pair {
        Node node;
        int hd; // Horizontal Distance

        public Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    static void verticalOrderTraversal(Node root) {
        if (root == null) return;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            Node node = curr.node;
            int hd = curr.hd;

            // Store in map
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);

            // Add children to queue with updated horizontal distance
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Print Vertical Order Traversal
        for (List<Integer> values : map.values()) {
            for (int val : values) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(3);
        root.left.left.left = new Node(5);
        root.left.left.right = new Node(3);
        root.right.left.left = new Node(6);
        root.right.left.right = new Node(7);
        root.right.left.left.right = new Node(3);

        System.out.println("Vertical Order Traversal:");
        verticalOrderTraversal(root);
    }
}
