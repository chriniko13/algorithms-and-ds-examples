package algorithms.graphs.nonweighted.nondirected;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjacencyMatrix {

    private static final int MAX_VERTICLES = 20;

    private int noOfVertices;

    private int indexVertex;

    private Vertex[] vertices;

    private int[][] adjacencyMatrix;

    public GraphAdjacencyMatrix(int noOfVertices) {
        if (noOfVertices > MAX_VERTICLES) {
            throw new IllegalArgumentException("noOfVertices is too big!");
        }

        indexVertex = 0;
        this.noOfVertices = noOfVertices;
        vertices = new Vertex[noOfVertices];
        adjacencyMatrix = new int[noOfVertices][noOfVertices];

        for (int i = 0; i < noOfVertices; i++) {
            for (int j = 0; j < noOfVertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void makeAllEdgesUnvisited() {
        Arrays.stream(vertices).forEach(vertex -> vertex.setVisited(Boolean.FALSE));
    }

    public void addVertex(String label) {
        if (indexVertex == noOfVertices) {
            throw new IllegalArgumentException("graph's vertices are full");
        }
        Vertex vertex = new Vertex(label, Boolean.FALSE);
        vertices[indexVertex++] = vertex;
    }

    public void addEdge(int from, int to) {
        adjacencyMatrix[from][to] = 1;
        adjacencyMatrix[to][from] = 1;
    }

    public void depthFirstSearch(int startingVertexId) {

        final Stack<Integer> backtrackStack = new Stack<>();

        vertices[startingVertexId].setVisited(Boolean.TRUE); //set it visited...
        backtrackStack.push(startingVertexId); //stored it for backtracking...
        printVertex(startingVertexId);

        while (!backtrackStack.isEmpty()) {
            int currentVertexId = backtrackStack.peek();
            int unvisitedVertex = getUnvisitedVertex(currentVertexId);

            if (unvisitedVertex != -1) {

                vertices[unvisitedVertex].setVisited(Boolean.TRUE);
                printVertex(unvisitedVertex);
                backtrackStack.push(unvisitedVertex);

            } else {
                backtrackStack.pop();
            }
        }
    }

    public List<String> minimunSpanningTreeDepthFirstSearch() {

        final List<String> mstEdges = new LinkedList<>();
        final Stack<Integer> backtrackStack = new Stack<>();

        vertices[0].setVisited(Boolean.TRUE); //set it visited...
        backtrackStack.push(0); //stored it for backtracking...
        printVertex(0);

        while (!backtrackStack.isEmpty()) {
            int currentVertexId = backtrackStack.peek();
            int unvisitedVertex = getUnvisitedVertex(currentVertexId);

            if (unvisitedVertex != -1) {

                mstEdges.add(vertices[currentVertexId].getLabel() + vertices[unvisitedVertex].getLabel());

                vertices[unvisitedVertex].setVisited(Boolean.TRUE);
                //printVertex(unvisitedVertex);
                backtrackStack.push(unvisitedVertex);

            } else {
                backtrackStack.pop();
            }
        }

        return mstEdges;
    }

    public List<String> minimumSpanningTreeBreadthFirstSearch() {
        final List<String> mstEdges = new LinkedList<>();
        final Queue<Integer> queue = new PriorityQueue<>();

        queue.add(0);
        vertices[0].setVisited(Boolean.TRUE);
        printVertex(0);

        int currentVertexId;

        while (!queue.isEmpty()) {

            currentVertexId = queue.peek();

            int unvisitedVertex = getUnvisitedVertex(currentVertexId);
            if (unvisitedVertex != -1) {
                mstEdges.add(vertices[currentVertexId].getLabel() + vertices[unvisitedVertex].getLabel());

                vertices[unvisitedVertex].setVisited(Boolean.TRUE);
                //printVertex(unvisitedVertex);
                queue.add(unvisitedVertex);

            } else {
                queue.poll();
            }
        }

        return mstEdges;
    }

    public void breadthFirstSearch(int startingVertexId) {
        final Queue<Integer> queue = new PriorityQueue<>();

        queue.add(startingVertexId);
        vertices[startingVertexId].setVisited(Boolean.TRUE);
        printVertex(startingVertexId);

        int currentVertexId;

        while (!queue.isEmpty()) {

            currentVertexId = queue.peek();

            int unvisitedVertex = getUnvisitedVertex(currentVertexId);
            if (unvisitedVertex != -1) {

                vertices[unvisitedVertex].setVisited(Boolean.TRUE);
                printVertex(unvisitedVertex);
                queue.add(unvisitedVertex);

            } else {
                queue.poll();
            }
        }
    }

    public int getUnvisitedVertex(int currentVertexId) {
        for (int j = 0; j < noOfVertices; j++) {

            boolean isConnected = adjacencyMatrix[currentVertexId][j] == 1;
            boolean isVisited = vertices[j].getVisited();

            if (isConnected && !isVisited) {
                return j;
            }
        }
        return -1;
    }

    public void printVertex(int vertexId) {
        System.out.println("vertex: " + vertices[vertexId]);
    }

    void printAdjacencyMatrix() {
        System.out.println("Adjacency - Matrix");
        for (int i = 0; i < noOfVertices; i++) {
            for (int j = 0; j < noOfVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
