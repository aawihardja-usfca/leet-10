/**
 * There is an undirected star graph consisting of n nodes.
 * Given a 2D integer array edges where each edges[i] = [u(i), v(i)]
 * indicates that there is an edge between the nodes u(i) and v(i).
 * Return the center of this star graph.
 *
 * Source: <a href="https://leetcode.com/problems/find-center-of-star-graph/description/">...</a>
 */
public class StarGraph {
    /**
     * Finds the center of a star graph.
     * @param edges the star graph
     */
    public int findCenter(int[][] edges) {
        /* In a star graph, any two edges will have a shared node.
         * This shared node is the center.
         * It is enough to compare two edges.
         */

        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }

    public static void main(String[] args) {
        int[][] edges  = {{1,2}, {2,3}, {4,2}};
        int[][] edges2 = {{1,2}, {5,1}, {1,3}, {1,4}};
        StarGraph sg = new StarGraph();
        int center1 = sg.findCenter(edges);
        int center2 = sg.findCenter(edges2);
        System.out.println(center1); // 2
        System.out.println(center2); // 1
    }
}
