package P2.W2;

import P2.W1.IGraph;
import common.datastruct.Bag;

/**
 * Created by maurizio.dapri on 4/12/2015.
 */
public class EdgeWeightedGraph implements IGraphEdge{

    private int _vertex;
    private  int _edges;
    private  Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        assert V>=0;
        _vertex=V;
        _edges=0;
        adj= (Bag<Edge>[]) new Bag[V];
        for( int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    @Override
    public void addEdge(Edge e) {
        int vertex= e.either();
        adj[vertex].add(e);
        adj[e.other(vertex)].add(e);
        _edges++;
    }

    @Override
    public Iterable<Edge> adj(int vertex) {
        return adj[vertex];
    }


    /**
     * Returns all edges in the edge-weighted graph.
     * To iterate over the edges in the edge-weighted graph, use foreach notation:
     * <tt>for (Edge e : G.edges())</tt>.
     * @return all edges in the edge-weighted graph as an Iterable.
     */
    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < _vertex; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    @Override
    public int V() {
        return _vertex;
    }

    @Override
    public int E() {
        return _edges;
    }

    //////////////////////
    //TEST
    /////////////////////
    public static void main(String[] args){
        System.out.println("START");

        test_QuizDigraphQ3();
        System.out.println("END");

    }
    private static void test_QuizDigraphQ3() {
        System.out.println("*test_QuizDigraphQ3");
    }
}
