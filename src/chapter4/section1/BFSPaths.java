package chapter4.section1;

import edu.princeton.cs.algs4.*;
public class BFSPaths {
    private int[] edgeTo; // 父节点数组
    private boolean marked[]; // 标记数组
    private final int s; // 源点
    public BFSPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    //breadth first search
    public void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();

        marked[s] = true;

        queue.enqueue(s);

        while (!queue.isEmpty()) {

            int v = queue.dequeue();

            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    edgeTo[w] = v;
                    marked[w] = true;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> stack = new Stack<>();

        for (int w = v; w != s; w = edgeTo[w]) {
            stack.push(w);
        }
        stack.push(s);
        return stack;
    }
}
