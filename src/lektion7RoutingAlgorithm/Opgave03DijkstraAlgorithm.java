package lektion7RoutingAlgorithm;

import java.lang.annotation.Target;
import java.util.*;

public class Opgave03DijkstraAlgorithm {

    /*
        Target objects (Edges - links)
        Contains a target: Notation of the node of which it links to
        Weight means cost of the link
     */
    static class Edge {
        String target;
        int weight;

        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }

    }

    /*
        Dijkstra's algorithm:
        input:
            Map og String and List containing edges (the graph)
            Value of node to which the algorithms starting point is (destination)
     */
    public static Map<String, Integer> dijkstra(Map<String, List<Edge>> graph, String start) {
        // Korteste afstande (default = ∞)
        // Result is a Map of all nodes and cost - starting point is source, therefore all other is values are infinity
        Map<String, Integer> dist = new HashMap<>();
        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE); // integer.MAX_VALUE represents infinity
        }
        dist.put(start, 0);

        // PriorityQueue til at vælge node med korteste afstand
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );
        priorityQueue.add(new AbstractMap.SimpleEntry<>(start, 0));

        // While the priority queue isn't empty
        while (!priorityQueue.isEmpty()) {
            String currentEdge = priorityQueue.poll().getKey();

            for (Edge edge : graph.get(currentEdge)) {
                int newDist = dist.get(currentEdge) + edge.weight;
                if (newDist < dist.get(edge.target)) {
                    dist.put(edge.target, newDist);
                    priorityQueue.add(
                            new AbstractMap.SimpleEntry<>(
                                    edge.target,
                                    newDist
                            )
                    );
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        // Definer grafen
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(
                new Edge("B", 6), new Edge("D", 1))
        );
        graph.put("B", Arrays.asList(
                new Edge("A", 6),
                new Edge("D", 2),
                new Edge("C", 5),
                new Edge("E", 2))
        );
        graph.put("C", Arrays.asList(
                new Edge("B", 5),
                new Edge("E", 5))
        );
        graph.put("D", Arrays.asList(
                new Edge("A", 1),
                new Edge("B", 2),
                new Edge("E", 1))
        );
        graph.put("E", Arrays.asList(
                new Edge("C", 5),
                new Edge("D", 1),
                new Edge("B", 2))
        );

        // Point of which the algorithm should run - giving the provided result
        Map<String, Integer> result = dijkstra(graph, "A");

        // Print resultaterne
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println("Korteste afstand fra A til " + entry.getKey() + " = " +
                    entry.getValue());
        }
    }

}