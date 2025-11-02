package org.example;
import java.util.*;

public class Condensation {
    public static class Result {
        public Map<Integer, List<int[]>> condAdj;
        public int[] compId;
        public int compCount;
    }

    public static Result build(List<List<Integer>> comps, Graph g) {
        int n = g.n;
        int[] compId = new int[n];
        for (int i = 0; i < comps.size(); i++) {
            for (int v : comps.get(i)) compId[v] = i;
        }

        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 0; i < comps.size(); i++) adj.put(i, new ArrayList<>());

        for (int u = 0; u < n; u++) {
            for (int[] e : g.adj.get(u)) {
                int v = e[0], w = e[1];
                if (compId[u] != compId[v]) {
                    adj.get(compId[u]).add(new int[]{compId[v], w});
                }
            }
        }

        Result r = new Result();
        r.compId = compId;
        r.compCount = comps.size();
        r.condAdj = adj;
        return r;
    }
}
