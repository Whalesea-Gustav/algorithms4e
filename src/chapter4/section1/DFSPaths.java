package chapter4.section1;

import edu.princeton.cs.algs4.*;

public class DFSPaths {
    private int[] edgeTo; // 父节点数组
    private boolean[] marked;
    private final int s; // source Node
    public DFSPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked(w)) {
                edgeTo[w] = v; //record father Node
                dfs(G, w);
            }
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public boolean hasPathTo(int s) {
        return marked[s];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> toReturn = new Stack<>();

        for (int w = v; w != s; w = edgeTo[w]) {
            toReturn.push(w);
        }
        toReturn.push(s);
        return toReturn;
    }
}
