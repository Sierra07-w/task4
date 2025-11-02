package graph.topo;

import java.util.*;

public class SimpleTopoSort {
    public List<Integer> topologicalSort(Map<Integer, List<int[]>> graph) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int u : graph.keySet()) {
            indegree.putIfAbsent(u, 0);
            for (int[] e : graph.get(u))
                indegree.put(e[0], indegree.getOrDefault(e[0], 0) + 1);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int node : indegree.keySet())
            if (indegree.get(node) == 0) q.add(node);
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            res.add(u);
            for (int[] e : graph.getOrDefault(u, new ArrayList<>())) {
                indegree.put(e[0], indegree.get(e[0]) - 1);
                if (indegree.get(e[0]) == 0) q.add(e[0]);
            }
        }
        return res;
    }
}
