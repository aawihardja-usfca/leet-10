import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The graph is an adjacency list with a Node that has a pointer
 * to its neighboring nodes. This is a bidirectional graph.
 *
 * Ex:
 *  1 --- 2
 *  |     |
 *  3 --- 4
 *  Node 1 has neighbors 2 and 3
 *  Node 2 has neighbors 1 and 4 etc.
 *
 * Given a node that represents the starting point in a graph,
 * return a deep copy of this graph.
 *
 * Source: <a href="https://leetcode.com/problems/clone-graph/description/">...</a>
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {
        // maps the original node to its clone
        Map<Node, Node> clonedMap = new HashMap<>();
        return dfs(node, clonedMap);
    }

    /**
     * Traverse the graph in dfs fashion and recursively create a new node.
     * @param node the original source node
     * @param clonedMap a mapping of original node to its clone
     */
    public Node dfs(Node node, Map<Node, Node> clonedMap) {
        if (node == null) {
            return null;
        }

        // We have already cloned this node, return the clone
        if (clonedMap.containsKey(node)) {
            return clonedMap.get(node);
        }

        // Otherwise, make a new clone and add it to the map
        Node newNode = new Node(node.val);
        clonedMap.put(node, newNode);

        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(dfs(neighbor, clonedMap));
        }
        return newNode;
    }

    private String graphStr(Node node) {
        StringBuilder sb = new StringBuilder();
        List<Integer> visited = new LinkedList<>();
        strHelper(sb, node, visited);
        return sb.toString();
    }

    private void strHelper(StringBuilder sb, Node node, List<Integer> visited) {
        if (visited.contains(node.val)) {
            return;
        }
        sb.append(node.val);
        visited.add(node.val);
        for (Node neighbor : node.neighbors) {
            strHelper(sb, neighbor, visited);
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph graph = new CloneGraph();
        Node node = graph.cloneGraph(node1);
        String str = graph.graphStr(node);
        System.out.println(str);
    }
}
