package chapter4.section1;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
public class Graph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new Object[V];

        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<Integer>();
    }

    public Graph(In in) {
        this(in.readInt());
        this.E= in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            this.addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        //don`t have test for v, w
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        String toReturn = V + " vertices, " + E + " edges\n";
    }

}
