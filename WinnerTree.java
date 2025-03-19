import java.util.Arrays;

class WinnerTree {
    private int[] tree;  // Winner tree internal nodes
    private int[] leaves; // Leaf nodes (input elements)
    private int k; // Number of elements (K-way merge)

    // Constructor
    public WinnerTree(int[] arr) {
        k = arr.length;
        leaves = arr.clone(); // Copy input array
        int size = 2 * k - 1; // Total nodes in winner tree
        tree = new int[size]; // Allocate space for tree
        buildTree();
    }

    // Build the winner tree
    private void buildTree() {
        int offset = k - 1; // Offset where leaf nodes start
        for (int i = 0; i < k; i++) {
            tree[offset + i] = i; // Store indices of leaves
        }
        for (int i = offset - 1; i >= 0; i--) {
            tree[i] = getWinner(tree[2 * i + 1], tree[2 * i + 2]);
        }
    }

    // Get the index of the winner (smallest value)
    private int getWinner(int left, int right) {
        if (leaves[left] <= leaves[right]) return left;
        return right;
    }

    // Get the current minimum (winner)
    public int getMin() {
        return leaves[tree[0]];
    }

    // Replace the winner with a new value (for K-way merging)
    public void replaceMin(int newValue) {
        int minIndex = tree[0];
        leaves[minIndex] = newValue;
        updateTree((k - 1 + minIndex) / 2); // Update the tree correctly
    }

    // Update tree after replacing a value
    private void updateTree(int pos) {
        while (pos >= 0) {
            int leftChild = 2 * pos + 1;
            int rightChild = 2 * pos + 2;

            // Ensure we don't go out of bounds
            if (leftChild >= tree.length || rightChild >= tree.length) {
                break;
            }

            tree[pos] = getWinner(tree[leftChild], tree[rightChild]);
            if (pos == 0) break; // Root reached
            pos = (pos - 1) / 2; // Move up to parent
        }
    }

    // Print the tree (for debugging)
    public void printTree() {
        System.out.println("Winner Tree: " + Arrays.toString(tree));
    }

    // Main function
    public static void main(String[] args) {
        int[] input = {10, 5, 20, 7, 30, 3, 1, 2, 6, 4, 8};
        WinnerTree wt = new WinnerTree(input);

        wt.printTree();
        System.out.println("Winner (Min Element): " + wt.getMin());

        wt.replaceMin(25); // Replace the smallest element
        System.out.println("Winner after replacement: " + wt.getMin());
    }
}
