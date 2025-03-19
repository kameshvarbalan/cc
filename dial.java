import java.util.*;

class dial {
    static int V, maxWeight;
    static int[][] graph;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        System.out.print("Enter maximum edge weight: ");
        maxWeight = sc.nextInt();
        graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }
        System.out.println("Enter edges (format: u v weight), enter -1 -1 -1 to stop:");
        while (true) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            if (u == -1 && v == -1 && weight == -1) break;
            graph[u][v] = weight;
            graph[v][u] = weight;
        }
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        int[] distances = dialsAlgorithm(src);
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To " + i + " -> " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
        sc.close();
    }

    public static int[] dialsAlgorithm(int src) {
        int[] dist = new int[V]; 
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        List<Deque<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= V * maxWeight; i++) {
            buckets.add(new LinkedList<>());
        }
        buckets.get(0).add(src); 
        int index = 0;
        while (index < buckets.size()) {
            while (!buckets.get(index).isEmpty()) {
                int u = buckets.get(index).poll();
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE) { 
                        int newDist = dist[u] + graph[u][v];
                        if (newDist < dist[v]) {
                            dist[v] = newDist;
                            buckets.get(newDist).add(v);
                        }
                    }
                }
            }
            while (index < buckets.size() && buckets.get(index).isEmpty()) {
                index++;
            }
        }
        return dist;
    }
}