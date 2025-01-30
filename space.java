import java.util.*;

class space {
    public static void main(String args[]){
        Queue<Integer> q = new LinkedList<Integer>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            q.add(sc.nextInt());
        }
        sort(q);
        while(!q.isEmpty()){
            System.out.print(q.peek()+" ");
            q.poll();
        }
    }
    private static int minidx(Queue<Integer> q, int idx){
        int i = -1, val = Integer.MAX_VALUE;
        int s = q.size();
        for(int k=0; k<s; k++){
            int cur = q.peek();
            q.poll();
            if(cur<=val && k<=idx){
                i = k;
                val = cur;
            }
            q.add(cur);
        }
        return i;
    }
    private static void insert(Queue<Integer> q, int i){
        int val = 0;
        int s = q.size();
        for(int k=0; k<s; k++){
            int cur = q.peek();
            q.poll();
            if(k!=i){
                q.add(cur);
            }
            else{
                val = cur;
            }
        }
        q.add(val);
    }
    private static void sort(Queue<Integer> q){
        for(int i=1; i<=q.size(); i++){
            int idx = minidx(q, q.size()-i);
            insert(q, idx);
        }
    }
}