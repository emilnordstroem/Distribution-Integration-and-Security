package lektion8BellmanFord;

import java.util.Arrays;

public class MyBellmanFord {

    private static Edge[] edge;
    private static MyBellmanFord graph;

    private static int[] dist;
    private int V, E;

    public static void main(String[] args) {
        //TODO
        //angiv antal knuder og kanter
        //lave en ny instans af MyBellmanFord graph
        graph = new MyBellmanFord(5,14);
        //fyld array’et edge med kanterne
        edge[0] = new Edge(0, 1, 1);
        edge[1] = new Edge(0,2,2);
        edge[2] = new Edge(1,0,1);
        edge[3] = new Edge(1,2,4);
        edge[4] = new Edge(1,3,2);
        edge[5] = new Edge(1,4,3);
        edge[6] = new Edge(2,0,2);
        edge[7] = new Edge(2,1,4);
        edge[8] = new Edge(2,3,5);
        edge[9] = new Edge(3,1,2);
        edge[10] = new Edge(3,2,5);
        edge[11] = new Edge(3,4,10);
        edge[12] = new Edge(4,1,3);
        edge[13] = new Edge(4,3,10);

        BellmanFordAlgo(graph, 4);
    }

    public MyBellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
    }

    public static void BellmanFordAlgo(MyBellmanFord graph, int source) {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        // Step 1: Initialize distances from source to all other vertices as INFINITE
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax all edges |V| - 1 times.
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].source;
                int v = graph.edge[j].destination;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Print distances from source to all vertices
        printDistances(dist, V);
    }

    // Print distances from source to all vertices
    public static void printDistances(int dist[], int V) {
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }
}

class Edge {
    int source;
    int destination;
    int weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}