import java.util.*;

class BinomialHeap {
    static class Node {
        int key, degree;
        Node parent, child, sibling;

        Node(int key) {
            this.key = key;
            degree = 0;
            parent = child = sibling = null;
        }
    }

    private Node head;

    public BinomialHeap() {
        head = null;
    }

    // Merge two binomial heaps
    private Node mergeHeaps(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        Node newHead;
        if (h1.degree <= h2.degree) {
            newHead = h1;
            h1 = h1.sibling;
        } else {
            newHead = h2;
            h2 = h2.sibling;
        }

        Node tail = newHead;
        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) {
                tail.sibling = h1;
                h1 = h1.sibling;
            } else {
                tail.sibling = h2;
                h2 = h2.sibling;
            }
            tail = tail.sibling;
        }

        if (h1 != null) tail.sibling = h1;
        if (h2 != null) tail.sibling = h2;

        return newHead;
    }

    // Link two binomial trees
    private void linkTrees(Node smaller, Node larger) {
        smaller.parent = larger;
        smaller.sibling = larger.child;
        larger.child = smaller;
        larger.degree++;
    }

    // Union of two binomial heaps
    private void union(Node h2) {
        head = mergeHeaps(head, h2);
        if (head == null) return;

        Node prev = null, curr = head, next = head.sibling;

        while (next != null) {
            if ((curr.degree != next.degree) || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    linkTrees(next, curr);
                } else {
                    if (prev == null) head = next;
                    else prev.sibling = next;
                    linkTrees(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
    }

    // Insert a key into the binomial heap
    public void insert(int key) {
        Node newNode = new Node(key);
        if (head == null) head = newNode;
        else union(newNode);
    }

    // Get minimum key from the heap
    public int getMin() {
        if (head == null) throw new NoSuchElementException("Heap is empty");

        Node minNode = head, temp = head;
        int minValue = head.key;

        while (temp != null) {
            if (temp.key < minValue) {
                minValue = temp.key;
                minNode = temp;
            }
            temp = temp.sibling;
        }
        return minValue;
    }

    // Extract the minimum key (delete min)
    public int extractMin() {
        if (head == null) throw new NoSuchElementException("Heap is empty");

        Node minPrev = null, minNode = head, temp = head;
        int minValue = head.key;

        while (temp.sibling != null) {
            if (temp.sibling.key < minValue) {
                minPrev = temp;
                minNode = temp.sibling;
                minValue = temp.sibling.key;
            }
            temp = temp.sibling;
        }

        if (minPrev != null) minPrev.sibling = minNode.sibling;
        else head = minNode.sibling;

        Node child = minNode.child;
        Node reversedChild = null;

        while (child != null) {
            Node next = child.sibling;
            child.sibling = reversedChild;
            reversedChild = child;
            child = next;
        }

        union(reversedChild);
        return minValue;
    }

    // Print the binomial heap (debugging)
    public void printHeap() {
        System.out.println("Binomial Heap:");
        printTree(head);
        System.out.println();
    }

    private void printTree(Node root) {
        while (root != null) {
            System.out.print(root.key + " ");
            printTree(root.child);
            root = root.sibling;
        }
    }

    public static void main(String[] args) {
        BinomialHeap bh = new BinomialHeap();
        bh.insert(10);
        bh.insert(20);
        bh.insert(5);
        bh.insert(7);
        bh.insert(30);

        System.out.println("Min Element: " + bh.getMin());
        bh.printHeap();

        System.out.println("Extracted Min: " + bh.extractMin());
        bh.printHeap();
    }
}
