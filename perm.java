import java.util.*;

class perm {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ip = new int[n];
        int[] op = new int[n];
        for(int i=0; i<n; i++){
            ip[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            op[i] = sc.nextInt();
        }
        if(check(ip, op, n)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
    private static boolean check(int[] input, int[] output, int n){
        Stack<Integer> s = new Stack<>();
        int k = 0;
        for(int i=0; i<n; i++){
            s.push(input[i]);
            while(!s.isEmpty() && s.peek()==output[k]){
                s.pop();
                k++;
            }
        }
        return (s.isEmpty());
    }
}