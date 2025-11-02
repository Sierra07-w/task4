package graph.dagsp;

import java.util.*;

public class SimpleDAGPaths {
    public Map<Integer, Integer> shortestPath(Map<Integer, List<int[]>> graph, int source, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int v : graph.keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(source, 0);
        for (int u : topo) {
            if (dist.get(u) == Integer.MAX_VALUE) continue;
            for (int[] e : graph.getOrDefault(u, new ArrayList<>())) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w < dist.get(v)) dist.put(v, dist.get(u) + w);
            }
        }
        return dist;
    }

    public Map<Integer, Integer> longestPath(Map<Integer, List<int[]>> graph, int source, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int v : graph.keySet()) dist.put(v, Integer.MIN_VALUE);
        dist.put(source, 0);
        for (int u : topo) {
            if (dist.get(u) == Integer.MIN_VALUE) continue;
            for (int[] e : graph.getOrDefault(u, new ArrayList<>())) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w > dist.get(v)) dist.put(v, dist.get(u) + w);
            }
        }
        return dist;
    }
}
