import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CloneGraphToo {

    public Node cloneGraph(Node node) {
        // keep track of which nodes have been visited
        List<Integer> visited = new ArrayList<>();
        return dfs(node, null, visited);
    }

    /**
     * Traverse the graph in dfs fashion and recursively create a new node
     */
    public Node dfs(Node node, Node source, List<Integer> visited) {
        if (node == null) {
            return null;
        }

        Node newNode = new Node(node.val);
        visited.add(newNode.val);
        for (Node neighbor : node.neighbors) {
            if (visited.contains(neighbor.val) && source != null)
                newNode.neighbors.add(source);
            else
                newNode.neighbors.add(dfs(neighbor, newNode, visited));
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

    public static void testCase1() {
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

        CloneGraphToo graph = new CloneGraphToo();
        Node node = graph.cloneGraph(node1);
        String str = graph.graphStr(node);
        System.out.println(str);
    }

    public static void testCase2() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
    }
}
