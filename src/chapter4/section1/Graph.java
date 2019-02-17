package chapter4.section1;

import edu.princeton.cs.algs4.*;

public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public int V() {
        return V;
    }

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];

        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Integer>();
    }

    public Graph(In in) {
        this(in.readInt());
        int waitingForRead= in.readInt();
        for (int i = 0; i < waitingForRead; i++) {
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

    @Override
    public String toString() {
        String toReturn = V + " vertices, " + E + " edges\n";
        for (int i = 0; i < V; i ++) {
            toReturn += i + " : ";
            for (Integer k : adj[i]) {
                toReturn += k + " ";
            }
            toReturn += "\n";
        }
        return toReturn;
    }


}
