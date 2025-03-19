import java.util.*;

class bellmanford {
    static int V;
    static int[][] graph;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
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
        }
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        int[] distances = bellmanFord(src);
        if (distances == null) {
            System.out.println("Negative weight cycle detected!");
        } else {
            System.out.println("Shortest distances from source " + src + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("To " + i + " -> " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
            }
        }
        sc.close();
    }

    public static int[] bellmanFord(int src) {
        int[] dist = new int[V]; 
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int k = 0; k < V - 1; k++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE) {
                        int newDist = dist[u] + graph[u][v];
                        if (newDist < dist[v]) {
                            dist[v] = newDist;
                        }
                    }
                }
            }
        }
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE) {
                    if (dist[u] + graph[u][v] < dist[v]) {
                        return null;
                    }
                }
            }
        }
        return dist;
    }
}