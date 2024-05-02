import java.util.*;

/**
 * There is a bidirectional graph with n vertices, each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges,
 * where each edges[i] = [u(i), v(i)] denotes
 * a bidirectional edge between vertex u(i) and vertex v(i).
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * Determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Source: <a href="https://leetcode.com/problems/find-if-path-exists-in-graph/description/">...</a>
 */
public class PathInGraph {
    /**
     * Determine if there is a valid path in the graph from source to destination.
     * @param n the number of vertices (node)
     * @param edges the graph (2d array)
     * @param source the source vertex
     * @param destination the destination vertex
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = createGraph(n, edges);
        return dfs(graph, visited, source, destination);
    }

    /**
     * Helper method to perform a dfs starting from the source node.
     * @param graph the adjacency list
     * @param visited nodes already visited
     * @param source the source node
     * @param dest the destination node
     */
    private boolean dfs
            (List<Integer>[] graph,
             boolean[] visited, int source, int dest) {

        // Go to the source node and perform a dfs
        visited[source] = true;

        if (source == dest)
            return true;

        List<Integer> neighbors = graph[source];
        if (neighbors == null) {
            return false;
        }

        for (Integer neighbor : neighbors) {
            if (!visited[neighbor]) {
                boolean result = dfs(graph, visited, neighbor, dest);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Builds the graph from an array of edges, in the form of an adjacency list.
     * @param edges the graph as a 2d array
     * @param n the number of vertices in this graph
     * @return the adjacency list representing this graph
     */
    private List<Integer>[] createGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int dest   = edges[i][1];

            // Graph is bidirectional, add both the dest and source node
            if (graph[source] == null) {
                graph[source] = new LinkedList<>();
            }
            if (!graph[source].contains(dest))
                graph[source].add(dest);

            if (graph[dest] == null) {
                graph[dest] = new LinkedList<>();
            }
            if (!graph[dest].contains(source))
                graph[dest].add(source);
        }
        return graph;
    }

    /**
     * Returns the string representation of a graph
     * @param graph the adjacency list
     */
    public String graphStr(List<Integer>[] graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.length; i++) {
            sb.append(i).append(": ");
            if (graph[i] == null) {
                sb.append("null");
            } else {
                for (Integer node : graph[i]) {
                    sb.append(node).append(" -> ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] edges   = {{0,1}, {1,2}, {2,0}};
        int[][] edges1  = {{0,1}, {0,2}, {3,5}, {5,4}, {4,3}};
        int[][] edges2  =
                {{31,5},{10,4},{19,3},{5,1},{31,2},{28,2},{8,26},
                {13,2},{16,3},{30,1},{16,1},{33,4},{27,3},{2,25},
                {49,3},{44,1},{22,2},{30,1},{27,1},{8,16},{42,1},{18,3},
                {21,2},{2,17},{5,48},{41,3},{39,3},{2,11},{20,2},{19,4},
                {45,7},{0,21},{44,2},{2,39},{27,3},{41,4},{17,4},{40,3},
                {2,28},{35,3},{3,9},{41,3},{5,11},{24,2},{39,5},{40,3},
                {18,3},{23,3},{20,2},{45,1}};

        int[][] edges3 = {{4,3},{1,4},{4,8},{1,7},{6,4},{4,2},{7,4},{4,0},{0,9},{5,4}};

        PathInGraph graph = new PathInGraph();

        List<Integer>[] g = graph.createGraph(50, edges2);
        String repr = graph.graphStr(g);
        System.out.println(repr);

        boolean f  = graph.validPath(3, edges, 0, 2);
        boolean f1 = graph.validPath(6, edges1, 0, 5);
        boolean f2 = graph.validPath(50, edges2, 29, 46);
        boolean f3 = graph.validPath(10, edges3, 5, 9);
        System.out.println(f);  // true
        System.out.println(f1); // false
        System.out.println(f2); // false
        System.out.println(f3); // true
    }
}
