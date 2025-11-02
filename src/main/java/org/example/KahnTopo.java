package org.example;
import java.util.*;

public class KahnTopo {
    public static List<Integer> topoSort(Map<Integer, List<int[]>> adj) {
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int u : adj.keySet()) indeg.put(u, 0);
        for (List<int[]> list : adj.values()) {
            for (int[] e : list) indeg.put(e[0], indeg.get(e[0]) + 1);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (var e : indeg.entrySet()) if (e.getValue() == 0) q.add(e.getKey());

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int[] e : adj.get(u)) {
                int v = e[0];
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0) q.add(v);
            }
        }
        return order;
    }
}
