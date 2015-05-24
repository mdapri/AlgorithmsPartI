package P2.W2;

import W4.MinPQ;
import common.MyUtils;
import common.datastruct.Queue;

/**
 * Slides 4.3 Minumum Spanning Trees, pg 52
 * Lazy because the edges are store in a priority queue  and not removed when "discarded"
 */
public class LazyPrimMST {

    MinPQ<Edge> _edges;
    int[] _treeVertices;

    Queue<Edge> _msTree;

    Edge selectMinCrossEdge() {
        return _edges.delTop();
    }


    /////////////////////
    // TEST
    ////////////////////

    public static void main(String[] args) {
        System.out.println("//START");

        test_Slide50();
        test_SelectEdge_EmptyTreeGetMinimum();
        System.out.println("//END");
    }

    private static void test_SelectEdge_EmptyTreeGetMinimum() {
        System.out.println("*test_SelectEdge_EmptyTreeGetMinimum");
        LazyPrimMST mst = new LazyPrimMST();
        Edge e0 = new Edge(1, 2, 0.1);
        Edge e1 = new Edge(1, 3, 0.2);
        Edge e2 = new Edge(2, 4, 0.3);

        mst._edges = new MinPQ<Edge>(3);
        mst._edges.insert(e2);
        mst._edges.insert(e0);
        mst._edges.insert(e1);

        Edge actual = mst.selectMinCrossEdge();
        Edge expected = e0;

        MyUtils.AssertEqual(actual, expected, "!test_SelectEdge_EmptyTreeGetMinimum");

    }

    private static void test_SelectEdge_NonEmptyAndMinimumIsInMSTGetNextEdge() {
        String testName = "test_SelectEdge_NonEmptyAndMinimumIsInMSTGetNextEdge";
        System.out.println("*"+testName);
        LazyPrimMST mst = new LazyPrimMST();
        Edge e0 = new Edge(1, 2, 0.1);
        Edge e1 = new Edge(1, 3, 0.2);
        Edge e2 = new Edge(2, 4, 0.3);

        mst._edges = new MinPQ<Edge>(3);
        mst._edges.insert(e2);
        mst._edges.insert(e0);
        mst._edges.insert(e1);

        Edge actual = mst.selectMinCrossEdge();
        Edge expected = e0;

        MyUtils.AssertEqual(actual, expected, "!"+ testName);

    }

    private static EdgeWeightedGraph CreateGraphSlide50() {
        EdgeWeightedGraph wg = new EdgeWeightedGraph(16);
        wg.addEdge(new Edge(0, 7, 0.16));
        wg.addEdge(new Edge(2, 3, 0.17));
        wg.addEdge(new Edge(1, 7, 0.19));
        wg.addEdge(new Edge(0, 2, 0.26));
        wg.addEdge(new Edge(5, 7, 0.28));
        wg.addEdge(new Edge(1, 3, 0.29));
        wg.addEdge(new Edge(1, 5, 0.32));
        wg.addEdge(new Edge(2, 7, 0.34));
        wg.addEdge(new Edge(4, 5, 0.35));
        wg.addEdge(new Edge(1, 2, 0.36));
        wg.addEdge(new Edge(4, 7, 0.37));
        wg.addEdge(new Edge(0, 4, 0.38));
        wg.addEdge(new Edge(6, 2, 0.40));
        wg.addEdge(new Edge(3, 6, 0.52));
        wg.addEdge(new Edge(6, 0, 0.58));
        wg.addEdge(new Edge(6, 4, 0.93));
        return wg;
    }

    private static void test_Slide50() {
        System.out.println("*test_Slide50");
        EdgeWeightedGraph wg = CreateGraphSlide50();
//        KruskalMST kmst = new KruskalMST(wg);
//        // expected is 0-7 1-7 0-2 2-3 5-7 4-5 6-2
//        Queue<Edge> expected =new Queue<Edge>();
//        expected.enqueue(new Edge(0,7, 0.16));
//        expected.enqueue(new Edge(1, 7, 0.19));
//        expected.enqueue(new Edge(0, 2, 0.26));
//        expected.enqueue(new Edge(2, 3, 0.17));
//        expected.enqueue(new Edge(5, 7, 0.28));
//        expected.enqueue(new Edge(4, 5, 0.35));
//        expected.enqueue(new Edge(6, 2, 0.40));
//
//        Queue<Edge> actual = kmst.mst;

//        MyUtils.AssertListEqual(actual, expected, "!Test_01");
    }
}

