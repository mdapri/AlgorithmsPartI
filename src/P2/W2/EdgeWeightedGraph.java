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

    @Override
    public Iterable<Edge> edges(){return null;}

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
