import java.util.*;

class kary{
    private int[] heap;
    private int size;
    private int k;  // Number of children per node

    // Constructor
    public kary(int capacity, int k) {
        this.heap = new int[capacity];
        this.size = 0;
        this.k = k;
    }

    // Returns the parent index
    private int parent(int i) {
        return (i - 1) / k;
    }

    // Returns the ith child index
    private int child(int i, int pos) {
        return k * i + pos;
    }

    // Insert a new key
    public void insert(int key) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = key;
        heapifyUp(size);
        size++;
    }

    // Heapify-up (percolate up)
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Extract the minimum element
    public int extractMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // Heapify-down (percolate down)
    private void heapifyDown(int i) {
        int minIndex = i;
        for (int j = 1; j <= k; j++) {
            int childIndex = child(i, j);
            if (childIndex < size && heap[childIndex] < heap[minIndex]) {
                minIndex = childIndex;
            }
        }
        if (minIndex != i) {
            swap(i, minIndex);
            heapifyDown(minIndex);
        }
    }

    // Swap function
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Print the heap
    public void printHeap() {
        System.out.println("K-ary Heap:");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Main function
    public static void main(String[] args) {
        kary heap = new kary(15, 3); // 3-ary heap

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(7);
        heap.insert(30);
        heap.insert(3);

        heap.printHeap();
        System.out.println("Extracted Min: " + heap.extractMin());
        heap.printHeap();
    }
}
