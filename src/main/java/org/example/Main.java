package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/data/small3.json";
        if (args.length > 0) file = args[0];
        try {
            GraphReader.Data d = GraphReader.read(file);
            Graph g = d.graph;
            int source = d.source;

            TarjanSCC tarjan = new TarjanSCC(g);
            List<List<Integer>> comps = tarjan.run();
            System.out.println("SCCs: " + comps);
            System.out.println("SCC sizes: " + comps.stream().map(List::size).toList());
            System.out.println("DFS visits: " + tarjan.getDfsVisits() + " edges seen: " + tarjan.getDfsEdges());

            Condensation.Result cr = Condensation.build(comps, g);
            System.out.println("Condensation nodes: " + cr.compCount);
            System.out.println("Component id per node: " + Arrays.toString(cr.compId));

            List<Integer> topo = KahnTopo.topoSort(cr.condAdj);
            System.out.println("Topo order of components: " + topo);

            DAGShortestLongest dsp = new DAGShortestLongest();
            int compSource = cr.compId[source];
            Map<Integer, Integer> shortest = dsp.shortest(cr.condAdj, compSource, topo);
            Map<Integer, Integer> longest = dsp.longest(cr.condAdj, compSource, topo);
            System.out.println("Shortest distances from comp " + compSource + ": " + shortest);
            System.out.println("Longest distances from comp " + compSource + ": " + longest);

            int maxComp = -1;
            int maxVal = Integer.MIN_VALUE;
            for (var e : longest.entrySet()) {
                if (e.getValue() != Integer.MIN_VALUE && e.getValue() > maxVal) {
                    maxVal = e.getValue();
                    maxComp = e.getKey();
                }
            }
            if (maxComp != -1) {
                List<Integer> path = dsp.reconstructLongestPath(cr.condAdj, compSource, maxComp, topo);
                System.out.println("Critical path (components): " + path + " length: " + maxVal);
            } else {
                System.out.println("No reachable nodes from source in condensation DAG.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
