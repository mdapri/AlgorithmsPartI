package P2.W2;

/**
 * a model for a weighted edge
 */
public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;

    /**
     *  contstructor
     * @param v
     * @param w
     * @param weight
     */
    public Edge(int v, int w, double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }
    /**
     * either endompoint
     * @return
     */
    public int either(){ return v;}

    /**
     * the other endompoint
     * @return
     */
    public int other(int vertex) {
        if (vertex == this.v) return w;
        else return this.v;
    }


    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight) return -1;
        else if (this.weight > o.weight) return +1;
        else
            return 0;
    }
}
