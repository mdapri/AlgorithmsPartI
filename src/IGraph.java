/**
 * Interface representing directed and undirected graph
 */
public interface IGraph {

    /**
     // connects 2 vertices. Parallel edges and self loops allowed
     */
    public void  addEdge(int v, int w);

    /**
     * the list of vertex adjacent to v
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v);

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
