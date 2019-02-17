package chapter4.section1;

import edu.princeton.cs.algs4.*;
public class TestSearch {

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.print("\n");

        if (search.count() < G.V()) System.out.print("Not ");
        System.out.print("connected");



    }
}
