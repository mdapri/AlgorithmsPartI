package P2.W2;

/**
 * File 43MinimumSpanningTree, slide 30
 */
public interface IMST {
//    public MST(EdgeWeightedGraph g){
//
//    }

    /**
     * edges in MST
     * @return
     */
    Iterable<Edge> edges() ;

    /**
     *  weight of MST
     * @return
     */
    double weight();
}
