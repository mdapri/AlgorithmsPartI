package P2.W1;

import common.MyUtils;
import common.datastruct.*;

/**
 * Created by maurizio.dapri on 3/28/2015.
 */
public class Digraph implements IGraph {

    private int _vertex;
    private  int _edges;
    private  Bag<Integer>[] adj;



    public Digraph(int V) {
        assert V>=0;
        _vertex=V;
        _edges=0;
        adj= (Bag<Integer>[]) new Bag[V];

        for (int i=0;i<_vertex;i++){
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     // connects 2 vertices. Parallel edges and self loops allowed
     */
    public void  addEdge(int v, int w) {
        assert v>=0 && v<_vertex;
        assert w>=0 && w<_vertex;

        adj[v].add(w);
        _edges++;
    }
    /**
     // the adjacency list of the vertex
     */
    public Iterable<Integer> adj(int v){
        assert v>=0 && v<_vertex;
        return adj[v];
    }

    /**
     * the count of vertex
     * @return
     */
    public  int V(){
        return _vertex;
    }

    /**
     *
     * @return  the count of edges
     */
    public int E() {
        return _edges;
    }


    public String toString(){
        StringBuilder b = new StringBuilder();
        for (int v = 0; v < _vertex; v++){
            for (int w : adj(v)) {
                b.append(v + "->" + w);
                b.append("\n");
            }
        }
        return b.toString();
    }

    /**
     *
     * @return a graph with edges reverted
     */
    public Digraph reverse(){
        Digraph reversed=  new Digraph(_vertex);
        for (int v=0;v<_vertex;v++){
            for(int w:adj(v) ){
                reversed.addEdge(w,v);
            }
        }
        return  reversed;
    }
    ///////////////////////////////
    // tet section
    //

    public static void main(String[] args){
        System.out.println("START");
        //test_Constructor();
        //test_add();
        test_revert();

        System.out.println("END");

    }

    private static void test_Constructor(){
        System.out.println("*test_Constructor");
        int size=0;
        Digraph g = new Digraph(1);

        MyUtils.Assert(g.V() == 1, "!Test01");
        MyUtils.Assert(g.E()==0, "!Test02");
        //add
        g = new Digraph(2);
        MyUtils.Assert(g.V() == 2, "!Test03");
        MyUtils.Assert(g.E()==0, "!Test04");
    }
    private static void test_add(){
        System.out.println("*test_add");
        int size=0;
        Digraph g = new Digraph(1);
        g.addEdge(0,0);
        MyUtils.Assert(g.V()==1, "!Test01");
        MyUtils.Assert(g.E()==1, "!Test02");

        g = new Digraph(2);
        g.addEdge(0,1);
        MyUtils.Assert(g.E() == 1, "!Test03");
        MyUtils.Assert(g.adj[0].size()==1, "!Test04");
        MyUtils.Assert(g.adj[1].size()==0, "!Test05");
        // add the reverse and check the edge have been added

        g.addEdge(1,0);
        MyUtils.Assert(g.E() == 2, "!Test06");
        MyUtils.Assert(g.adj[0].size()==1, "!Test07");
        MyUtils.Assert(g.adj[1].size()==1, "!Test08");

        System.out.println(g.toString());

    }

    private static void test_revert() {
        System.out.println("*test_revert");
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        System.out.println(g.toString());
        Digraph r = g.reverse();
        System.out.println(r.toString());
    }
}
