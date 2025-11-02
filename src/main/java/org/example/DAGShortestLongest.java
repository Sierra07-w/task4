package org.example;
import java.util.*;

public class DAGShortestLongest {
    public Map<Integer, Integer> shortest(Map<Integer, List<int[]>> adj, int src, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int u : adj.keySet()) dist.put(u, Integer.MAX_VALUE);
        dist.put(src, 0);

        for (int u : topo) {
            if (dist.get(u) == Integer.MAX_VALUE) continue;
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w < dist.get(v)) dist.put(v, dist.get(u) + w);
            }
        }
        return dist;
    }

    public Map<Integer, Integer> longest(Map<Integer, List<int[]>> adj, int src, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int u : adj.keySet()) dist.put(u, Integer.MIN_VALUE);
        dist.put(src, 0);

        for (int u : topo) {
            if (dist.get(u) == Integer.MIN_VALUE) continue;
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w > dist.get(v)) dist.put(v, dist.get(u) + w);
            }
        }
        return dist;
    }

    public List<Integer> reconstructLongestPath(Map<Integer, List<int[]>> adj, int src, int dest, List<Integer> topo) {
        Map<Integer, Integer> dist = longest(adj, src, topo);
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(src, -1);

        for (int u : topo) {
            if (dist.get(u) == Integer.MIN_VALUE) continue;
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w > dist.get(v)) {
                    dist.put(v, dist.get(u) + w);
                    parent.put(v, u);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = parent.getOrDefault(at, -1)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
