package algorithms.graphs.nonweighted.nondirected;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("DEPTH FIRST SEARCH - BREADTH FIRST SEARCH DEMONSTRATION\n");
        dfs_bfs_demonstration();

        System.out.println("==========================================================\n");

        System.out.println("MINIMUM SPANNING TREE DEMONSTRATION\n");
        mst_demonstration();

    }

    private static void dfs_bfs_demonstration() {
        /*
        
        B --- F --- H
        /
        A  --- C
        |  \
        |    D --- G --- I
        \
        E
         */
        GraphAdjacencyMatrix graphAdjacencyMatrix = new GraphAdjacencyMatrix(9);

        //add vertices...
        graphAdjacencyMatrix.addVertex("A"); // 0
        graphAdjacencyMatrix.addVertex("B"); // 1
        graphAdjacencyMatrix.addVertex("C"); // 2
        graphAdjacencyMatrix.addVertex("D"); // 3
        graphAdjacencyMatrix.addVertex("E"); // 4
        graphAdjacencyMatrix.addVertex("F"); // 5
        graphAdjacencyMatrix.addVertex("G"); // 6
        graphAdjacencyMatrix.addVertex("H"); // 7
        graphAdjacencyMatrix.addVertex("I"); // 8

        //add edges...
        graphAdjacencyMatrix.addEdge(0, 1); // AB
        graphAdjacencyMatrix.addEdge(0, 2); // AC
        graphAdjacencyMatrix.addEdge(0, 3); // AD
        graphAdjacencyMatrix.addEdge(0, 4); // AE

        graphAdjacencyMatrix.addEdge(1, 5); // BF
        graphAdjacencyMatrix.addEdge(5, 7); // FH

        graphAdjacencyMatrix.addEdge(3, 6); // DG
        graphAdjacencyMatrix.addEdge(6, 8); // GI

        //print adjacency matrix...
        graphAdjacencyMatrix.printAdjacencyMatrix();

        //perform depth first search...
        System.out.println("DFS");
        graphAdjacencyMatrix.depthFirstSearch(0);
        graphAdjacencyMatrix.makeAllEdgesUnvisited();
        System.out.println("\n");

        //perform breadth first search...
        System.out.println("BFS");
        graphAdjacencyMatrix.breadthFirstSearch(0);
        graphAdjacencyMatrix.makeAllEdgesUnvisited();
        System.out.println("\n");
    }

    private static void mst_demonstration() {
        GraphAdjacencyMatrix graphAdjacencyMatrix = new GraphAdjacencyMatrix(5);

        //add vertices...
        graphAdjacencyMatrix.addVertex("A"); // 0
        graphAdjacencyMatrix.addVertex("B"); // 1
        graphAdjacencyMatrix.addVertex("C"); // 2
        graphAdjacencyMatrix.addVertex("D"); // 3
        graphAdjacencyMatrix.addVertex("E"); // 4

        //add edges...
        graphAdjacencyMatrix.addEdge(0, 1); // AB
        graphAdjacencyMatrix.addEdge(0, 2); // AC
        graphAdjacencyMatrix.addEdge(0, 3); // AD
        graphAdjacencyMatrix.addEdge(0, 4); // AE
        graphAdjacencyMatrix.addEdge(1, 2); // BC
        graphAdjacencyMatrix.addEdge(1, 3); // BD
        graphAdjacencyMatrix.addEdge(1, 4); // BE
        graphAdjacencyMatrix.addEdge(2, 3); // CD
        graphAdjacencyMatrix.addEdge(2, 4); // CE
        graphAdjacencyMatrix.addEdge(3, 4); // DE

        //print adjacency matrix...
        graphAdjacencyMatrix.printAdjacencyMatrix();

        //perform mst with dfs
        List<String> minimunSpanningTreeDepthFirstSearch = graphAdjacencyMatrix.minimunSpanningTreeDepthFirstSearch();
        String mstDfsResult = minimunSpanningTreeDepthFirstSearch.stream().collect(Collectors.joining(" ---> ", "{", "}"));
        System.out.println("mst with dfs result = " + mstDfsResult + "\n");

        graphAdjacencyMatrix.makeAllEdgesUnvisited();

        //perform mst with bfs
        List<String> minimunSpanningTreeBreadthFirstSearch = graphAdjacencyMatrix.minimumSpanningTreeBreadthFirstSearch();
        String mstBfsResult = minimunSpanningTreeBreadthFirstSearch.stream().collect(Collectors.joining(" ---> ", "{", "}"));
        System.out.println("mst with bfs result = " + mstBfsResult + "\n");
    }

}
