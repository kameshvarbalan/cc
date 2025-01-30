import java.util.*;

class stock {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int[] ans = new int[n];
        stock(arr, n, ans);
        System.out.println(Arrays.toString(ans));
    }
    private static void stock(int[] arr, int n , int[] ans){
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        ans[0] = 1;
        for(int i=1; i<n; i++){
            while(!stk.isEmpty() && arr[stk.peek()]<=arr[i]){
                stk.pop();
            }
            ans[i] = (stk.isEmpty())? (i+1):(i-stk.peek());
            stk.push(i); 
        }
    }
}