package graph.scc;

import java.util.*;

public class SimpleSCC {
    private Map<Integer, List<int[]>> graph;
    private Map<Integer, Boolean> visited = new HashMap<>();
    private Deque<Integer> stack = new ArrayDeque<>();
    private Map<Integer, List<Integer>> reversed = new HashMap<>();

    public SimpleSCC(Map<Integer, List<int[]>> graph) {
        this.graph = graph;
    }

    public List<List<Integer>> findSCCs() {
        for (Integer node : graph.keySet())
            if (!visited.getOrDefault(node, false)) dfs1(node);
        buildReversed();
        visited.clear();
        List<List<Integer>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.getOrDefault(node, false)) {
                List<Integer> comp = new ArrayList<>();
                dfs2(node, comp);
                result.add(comp);
            }
        }
        return result;
    }

    private void dfs1(int node) {
        visited.put(node, true);
        for (int[] e : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited.getOrDefault(e[0], false)) dfs1(e[0]);
        stack.push(node);
    }

    private void dfs2(int node, List<Integer> comp) {
        visited.put(node, true);
        comp.add(node);
        for (int nei : reversed.getOrDefault(node, new ArrayList<>()))
            if (!visited.getOrDefault(nei, false)) dfs2(nei, comp);
    }

    private void buildReversed() {
        for (int u : graph.keySet())
            for (int[] e : graph.get(u))
                reversed.computeIfAbsent(e[0], k -> new ArrayList<>()).add(u);
    }
}
