import java.util.Arrays;

class HeapSort {
    // Function to perform Heap Sort
    public void heapSort(int arr[]) {
        int n = arr.length;

        // Step 1: Build Max Heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (max) to end
            swap(arr, 0, i);

            // Heapify root element to maintain heap property
            heapify(arr, i, 0);
        }
    }

    // Heapify a subtree rooted at node i
    void heapify(int arr[], int n, int i) {
        int largest = i; // Assume root is largest
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than current largest
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Swap function
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Driver code to test Heap Sort
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(arr));

        HeapSort hs = new HeapSort();
        hs.heapSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
