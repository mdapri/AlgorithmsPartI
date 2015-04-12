package P2.W2;

/**
 * Interface representing directed and undirected graph
 */
public interface IGraphEdge {

    /**
     // connects 2 vertices. Parallel edges and self loops allowed
     */
    public void  addEdge(Edge edge);

    /**
     * the list of vertex adjacent to v
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v);

    /**
     * all edges in this graph
     * @return
     */
    Iterable<Edge> edges();


    /**
     * the count of vertex
     * @return
     */
    public  int V();

    /**
     * the count of edges
     * @return
     */
    public  int E();
}
