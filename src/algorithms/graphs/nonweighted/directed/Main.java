package algorithms.graphs.nonweighted.directed;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("CYCLIC GRAPH DEMONSTRATION");

        cyclicGraphDemonstration();

        System.out.println("==========================================================\n");

        System.out.println("TOPOLOGICAL SORTING DEMONSTRATION");

        topologicalSortingDemonstration();

        System.out.println("==========================================================\n");

        System.out.println("CONNECTIVITY TABLE DEMONSTRATION");

        GraphAdjacencyList graphAdjacencyList = new GraphAdjacencyList();

        //add vertices...
        graphAdjacencyList.addVertex("A");
        graphAdjacencyList.addVertex("B");
        graphAdjacencyList.addVertex("C");
        graphAdjacencyList.addVertex("D");
        graphAdjacencyList.addVertex("E");

        //add edges...
        graphAdjacencyList.addEdge("A", "C");
        graphAdjacencyList.addEdge("B", "A");
        graphAdjacencyList.addEdge("B", "E");
        graphAdjacencyList.addEdge("D", "E");
        graphAdjacencyList.addEdge("E", "C");

        //connectivity table demonstration...
        System.out.println("connectivity table using dfs...");
        Map<String, List<String>> connectivityTableUsingDfs = graphAdjacencyList.connectivityTable(false);
        connectivityTableUsingDfs.forEach((k, v) -> {
            System.out.println(k + " ---> " + v);
        });

        System.out.println();

        System.out.println("connectivity table using bfs...");
        Map<String, List<String>> connectivityTableUsingBfs = graphAdjacencyList.connectivityTable(true);
        connectivityTableUsingBfs.forEach((k, v) -> {
            System.out.println(k + " ---> " + v);
        });

    }

    private static void cyclicGraphDemonstration() {
        GraphAdjacencyList graph = new GraphAdjacencyList();

        //add vertices...
        graph.addVertex("A"); // 0
        graph.addVertex("B"); // 1
        graph.addVertex("C"); // 2
        graph.addVertex("D"); // 3

        //add edges...
        graph.addEdge("A", "B"); // AB
        graph.addEdge("A", "C"); // AC
        graph.addEdge("B", "C"); // BC
        graph.addEdge("C", "D"); // CD
//        graph.addEdge("D", "B"); // DB , Note: comment-uncomment to see what happens.

        System.out.println("is acyclic graph: " + graph.isAcyclicGraph());
    }

    private static void topologicalSortingDemonstration() {
        GraphAdjacencyList graphAdjacencyList = new GraphAdjacencyList();

        //add vertices...
        graphAdjacencyList.addVertex("A"); // 0
        graphAdjacencyList.addVertex("B"); // 1
        graphAdjacencyList.addVertex("C"); // 2
        graphAdjacencyList.addVertex("D"); // 3
        graphAdjacencyList.addVertex("E"); // 4
        graphAdjacencyList.addVertex("F"); // 5
        graphAdjacencyList.addVertex("G"); // 6
        graphAdjacencyList.addVertex("H"); // 7

        //add edges...
        graphAdjacencyList.addEdge("A", "D"); // AD
        graphAdjacencyList.addEdge("A", "E"); // AE
        graphAdjacencyList.addEdge("B", "E"); // BE
        graphAdjacencyList.addEdge("C", "F"); // CF
        graphAdjacencyList.addEdge("D", "G"); // DG
        graphAdjacencyList.addEdge("E", "G"); // EG
        graphAdjacencyList.addEdge("F", "H"); // FH
        graphAdjacencyList.addEdge("G", "H"); // GH
        //graphAdjacencyList.addEdge("H", "A"); // HA , Note: comment-uncomment to see what happens.

        //do topological sorting...
        List<Vertex> topologicalSorting = graphAdjacencyList.topologicalSorting();
        System.out.println("topologicalSorting == " + topologicalSorting);
    }

}
