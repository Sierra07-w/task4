package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class DAGShortestLongestTest {

    @Test
    public void testShortestAndLongest() {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        adj.put(0, List.of(new int[]{1, 2}, new int[]{2, 4}));
        adj.put(1, List.of(new int[]{2, 1}));
        adj.put(2, new ArrayList<>());

        List<Integer> topo = List.of(0, 1, 2);
        DAGShortestLongest dag = new DAGShortestLongest();

        Map<Integer, Integer> shortest = dag.shortest(adj, 0, topo);
        Map<Integer, Integer> longest = dag.longest(adj, 0, topo);

        assertEquals(0, (int) shortest.get(0));
        assertEquals(2, (int) shortest.get(1));
        assertEquals(3, (int) shortest.get(2)); // 0->1->2

        assertEquals(0, (int) longest.get(0));
        assertEquals(2, (int) longest.get(1));
        assertEquals(4, (int) longest.get(2)); // 0->2
    }
}
