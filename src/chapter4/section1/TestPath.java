package chapter4.section1;

import edu.princeton.cs.algs4.*;
public class TestPath {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BFSPaths paths = new BFSPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.print("Path from " + s + " to " + v + " : ");

                for (int w : paths.pathTo(v)) {
                    if (w == s) StdOut.print(w);
                    else StdOut.print("-" + w);
                }
                StdOut.print('\n');
            }
        }
    }
}
