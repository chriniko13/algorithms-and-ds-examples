package algorithms.graphs.nonweighted.directed;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjacencyList {

    private final Map<String, Vertex> vertices;
    private final Map<String, List<String>> adjacencyList;

    public GraphAdjacencyList() {
        vertices = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }

    public void addVertex(String label) {
        Vertex vertex = new Vertex(label);

        vertices.put(label, vertex);
        adjacencyList.put(label, new LinkedList<>());
    }

    public void addEdge(String fromLabel, String toLabel) {
        adjacencyList.get(fromLabel).add(toLabel);
    }

    public boolean isAcyclicGraph() {

        int edges = adjacencyList
                .entrySet()
                .stream()
                .map((entry) -> entry.getValue().size())
                .reduce(0, Integer::sum);

        return edges == vertices.size();
    }

    public List<Vertex> topologicalSorting() {

        final List<Vertex> topologicalSorting = new LinkedList<>();
        int noOfVertices = vertices.size();

        for (int i = 0; i < noOfVertices; i++) {

            String vertexLabelWithNoSuccessors = getVertexLabelWithNoSuccessors();

            if (vertexLabelWithNoSuccessors != null) {

                //add it to list...
                topologicalSorting.add(0, vertices.get(vertexLabelWithNoSuccessors));

                //remove it...
                vertices.remove(vertexLabelWithNoSuccessors);
                adjacencyList.remove(vertexLabelWithNoSuccessors);
                for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
                    entry.getValue().remove(vertexLabelWithNoSuccessors);
                }
            } else {
                throw new IllegalStateException("the graph is not acyclic!");
            }

        }

        return topologicalSorting;
    }

    public Map<String, List<String>> connectivityTable(boolean useBfs) {
        final Map<String, List<String>> connectivityTable = new HashMap<>();

        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {

            vertices.forEach((k, v) -> v.setVisited(false)); //reinit every time...

            String vertexLabel = entry.getKey();
            Vertex vertexValue = entry.getValue();

            final List<String> connections = adjacencyList.get(vertexLabel);

            if (connections.isEmpty()) {
                connectivityTable.put(vertexLabel, Collections.emptyList());
            } else {
                if (useBfs) {
                    bfsProcessing(vertexValue, connectivityTable, vertexLabel);
                } else {
                    dfsProcessing(vertexValue, connectivityTable, vertexLabel);
                }
            }
        }
        return connectivityTable;
    }

    private void dfsProcessing(Vertex vertexValue, final Map<String, List<String>> connectivityTable, String vertexLabel) {
        final Stack<String> backtrack = new Stack<>();
        final List<String> connectsTo = new LinkedList<>();
        String currentVertexLabel;

        backtrack.add(vertexValue.getLabel());
        vertexValue.setVisited(true);
        backtrack.push(vertexValue.getLabel());

        while (!backtrack.isEmpty()) {
            currentVertexLabel = backtrack.peek();
            String unvisitedConnection = getUnvisitedConnection(adjacencyList.get(currentVertexLabel));

            if (unvisitedConnection != null) {

                currentVertexLabel = unvisitedConnection;

                connectsTo.add(currentVertexLabel);
                vertices.get(currentVertexLabel).setVisited(true);
                backtrack.push(currentVertexLabel);

            } else {
                backtrack.pop();
            }

        }
        connectivityTable.put(vertexLabel, connectsTo);
    }

    private void bfsProcessing(Vertex vertexValue, final Map<String, List<String>> connectivityTable, String vertexLabel) {
        final Queue<String> backtrack = new PriorityQueue<>();
        final List<String> connectsTo = new LinkedList<>();
        String currentVertexLabel;

        backtrack.add(vertexValue.getLabel());
        vertexValue.setVisited(true);
        backtrack.add(vertexValue.getLabel());

        while (!backtrack.isEmpty()) {

            currentVertexLabel = backtrack.peek();
            String unvisitedConnection = getUnvisitedConnection(adjacencyList.get(currentVertexLabel));

            if (unvisitedConnection != null) {

                currentVertexLabel = unvisitedConnection;

                connectsTo.add(currentVertexLabel);
                vertices.get(currentVertexLabel).setVisited(true);
                backtrack.add(currentVertexLabel);

            } else {
                backtrack.poll();
            }
        }

        connectivityTable.put(vertexLabel, connectsTo);
    }

    private String getUnvisitedConnection(List<String> connections) {

        for (String connection : connections) {

            Vertex vertex = vertices.get(connection);
            if (!vertex.isVisited()) {
                return vertex.getLabel();
            }
        }

        return null;

    }

    private String getVertexLabelWithNoSuccessors() {
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {

            String nodeLabel = entry.getKey();
            List<String> connections = entry.getValue();

            if (connections.isEmpty()) {
                return nodeLabel;
            }
        }

        return null;
    }

}
