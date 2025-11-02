package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TopoSortTest {

    @Test
    public void testTopoOrder() {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        adj.put(0, List.of(new int[]{1, 1}));
        adj.put(1, List.of(new int[]{2, 1}));
        adj.put(2, new ArrayList<>());

        KahnTopo topo = new KahnTopo();
        List<Integer> order = topo.topoSort(adj);

        assertEquals(3, order.size());
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(1) < order.indexOf(2));
    }
}
