package P2.W2;

import W1.QuickFindUF;
import common.MyUtils;
import common.datastruct.Queue;
import W1.UF;
import W4.MinPQ;



/**
 * Consider edges in ascending order of weight.
 * Add next edge to tree T unless doing so would create a cycle.
 *
 * running time (E=edges V=Vertices) E*logE
 */
public class KruskalMST implements IMST {

    private Queue<Edge> mst = new Queue<Edge>();
    private MinPQ<Edge> pq;
    private double weigth;

    public KruskalMST(EdgeWeightedGraph g) {

        pq = new MinPQ<Edge>(g.E());
        for (Edge e : g.edges()) {
            pq.insert(e);
        }

        UF unionFind = new QuickFindUF(g.V());
        while (!pq.isEmpty()) {
            Edge e = pq.delTop();
            int v = e.either();
            int w = e.other(v);
            if (!unionFind.connected(v, w)) {
                unionFind.union(v, w);
                mst.enqueue(e);
                weigth += e.weight();
            }
        }

    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weigth;
    }

    /*
0-7 0.16
2-3 0.17
1-7 0.19
0-2 0.26
5-7 0.28
1-3 0.29
1-5 0.32
2-7 0.34
4-5 0.35
1-2 0.36
4-7 0.37
0-4 0.38
6-2 0.40
3-6 0.52
6-0 0.58
6-4 0.93
     */

    /////////////////////
    // TEST
    ////////////////////

    public static void main(String[] args) {
        System.out.println("//START");
        test_Slide50();

        System.out.println("//END");
    }
    private static EdgeWeightedGraph CreateGraphSlide50(){
        EdgeWeightedGraph wg = new EdgeWeightedGraph(16);
        wg.addEdge(new Edge(0,7, 0.16));
        wg.addEdge(new Edge(2,3, 0.17));
        wg.addEdge(new Edge(1,7, 0.19));
        wg.addEdge(new Edge(0,2, 0.26));
        wg.addEdge(new Edge(5,7, 0.28));
        wg.addEdge(new Edge(1,3, 0.29));
        wg.addEdge(new Edge(1,5, 0.32));
        wg.addEdge(new Edge(2,7, 0.34));
        wg.addEdge(new Edge(4,5, 0.35));
        wg.addEdge(new Edge(1,2, 0.36));
        wg.addEdge(new Edge(4,7, 0.37));
        wg.addEdge(new Edge(0,4, 0.38));
        wg.addEdge(new Edge(6,2, 0.40));
        wg.addEdge(new Edge(3,6, 0.52));
        wg.addEdge(new Edge(6,0, 0.58));
        wg.addEdge(new Edge(6,4, 0.93));
        return wg;
    }

    private static void test_Slide50() {
        System.out.println("*test_Slide50");
        EdgeWeightedGraph wg= CreateGraphSlide50();
        KruskalMST kmst = new KruskalMST(wg);
        // expected is 0-7 1-7 0-2 2-3 5-7 4-5 6-2
        Queue<Edge> expected =new Queue<Edge>();
        expected.enqueue(new Edge(0,7, 0.16));
        expected.enqueue(new Edge(1, 7, 0.19));
        expected.enqueue(new Edge(0, 2, 0.26));
        expected.enqueue(new Edge(2, 3, 0.17));
        expected.enqueue(new Edge(5, 7, 0.28));
        expected.enqueue(new Edge(4, 5, 0.35));
        expected.enqueue(new Edge(6, 2, 0.40));

        Queue<Edge> actual = kmst.mst;

        MyUtils.AssertListEqual(actual, expected, "!Test_01");
    }
}