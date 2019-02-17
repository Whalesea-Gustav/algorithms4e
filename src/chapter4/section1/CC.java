package chapter4.section1;

import edu.princeton.cs.algs4.*;
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) { // 如果未被标记，则是新的连通分量
                dfs(G, v);
                count++; // dfs结束后，设置新的连通分量标记
            }
        }
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return this.count;
    }

    public int id(int v) {return id[v];}
}
