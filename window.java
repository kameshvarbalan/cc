import java.util.*;

class window {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int[] ans = new int[n-k+1];
        slide(arr, ans, k);
        System.out.println(Arrays.toString(ans));
    }
    private static void slide(int[] arr, int[] ans, int k){
        Deque<Integer> q = new LinkedList<>();
        int i;
        for(i=0; i<k; i++){
            while(!q.isEmpty() && arr[i]>=arr[q.peekLast()]){
                q.removeLast();
            }
            q.addLast(i);
        }
        int idx = 0;
        for(; i<arr.length; i++){
            ans[idx++] = arr[q.peek()];
            while(!q.isEmpty() && q.peek()<=i-k){
                q.removeFirst();
            }
            while(!q.isEmpty() && arr[i]>=arr[q.peekLast()]){
                q.removeLast();
            }
            q.addLast(i);
        }
        ans[idx] = q.peek();
    }
}