package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SCCFinderTest {

    @Test
    public void testSimpleSCC() {
        Graph g = new Graph(4, true);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);
        g.addEdge(2, 3, 1);

        TarjanSCC sccFinder = new TarjanSCC(g);
        List<List<Integer>> sccs = sccFinder.run();

        boolean hasBigSCC = false;
        for (List<Integer> scc : sccs) {
            if (scc.size() == 3 && scc.containsAll(Arrays.asList(0, 1, 2))) {
                hasBigSCC = true;
                break;
            }
        }
        assertTrue("Expected SCC with vertices 0,1,2", hasBigSCC);
    }
}
