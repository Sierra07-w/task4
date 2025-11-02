package org.example;
import java.util.*;

public class TarjanSCC {
    private Graph g;
    private int time = 0;
    private int dfsVisits = 0, dfsEdges = 0;
    private int[] disc, low;
    private boolean[] onStack;
    private Deque<Integer> stack = new ArrayDeque<>();
    private List<List<Integer>> sccList = new ArrayList<>();

    public TarjanSCC(Graph g) {
        this.g = g;
        int n = g.n;
        disc = new int[n];
        low = new int[n];
        onStack = new boolean[n];
    }

    public List<List<Integer>> run() {
        for (int i = 0; i < g.n; i++) {
            if (disc[i] == 0) dfs(i);
        }
        return sccList;
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        onStack[u] = true;
        dfsVisits++;

        for (int[] e : g.adj.get(u)) {
            int v = e[0];
            dfsEdges++;
            if (disc[v] == 0) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                onStack[v] = false;
                comp.add(v);
            } while (v != u);
            sccList.add(comp);
        }
    }

    public int getDfsVisits() { return dfsVisits; }
    public int getDfsEdges() { return dfsEdges; }

}
