import java.util.*;

public class Graph {
    private int V;
    private int[][] adjMatrix;

    public Graph(int vertices) {
        this.V = vertices;
        adjMatrix = new int[V][V]; 
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1;
    }
    public void bfs(int startVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);
        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int i = 0; i < V; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    public void dfsRecursive(int startVertex) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Traversal (Recursive): ");
        dfsHelper(startVertex, visited);
        System.out.println();
    }
    private void dfsHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int i = 0; i < V; i++) {
            if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                dfsHelper(i, visited);
            }
        }
    }

    public void dfsIterative(int startVertex) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        System.out.print("DFS Traversal (Iterative): ");
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");
                for (int i = V - 1; i >= 0; i--) {
                    if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);
        System.out.println("Enter edges (format: src dest), enter -1 -1 to stop:");
        while (true) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            if (src == -1 && dest == -1) break;
            graph.addEdge(src, dest);
        }
        System.out.print("Enter starting vertex for traversal: ");
        int startVertex = scanner.nextInt();
        graph.bfs(startVertex);
        graph.dfsRecursive(startVertex);
        graph.dfsIterative(startVertex);
        scanner.close();
    }
}
