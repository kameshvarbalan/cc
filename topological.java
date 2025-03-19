import java.util.*;

class TopologicalSortBFS {
    static void topologicalSort(int V, List<List<Integer>> adj) {
        int[] inDegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> topoOrder = new ArrayList<>();

        // Calculate in-degree (number of incoming edges)
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        // Add all vertices with in-degree 0 to the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // BFS processing
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check for cycle (if all nodes aren't processed)
        if (topoOrder.size() != V) {
            System.out.println("Graph contains a cycle! No valid topological order.");
            return;
        }

        // Print the topological order
        System.out.println("Topological Order (BFS): " + topoOrder);
    }

    public static void main(String[] args) {
        int V = 6;  // Number of vertices
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Sample DAG
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        topologicalSort(V, adj);
    }
}
