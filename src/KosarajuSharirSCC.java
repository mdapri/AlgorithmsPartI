/**
 * Strongly connected component of a directed graph
 */
public class KosarajuSharirSCC {

    boolean trace  =false;

    private boolean marked[];
    private int[] id;
    private int count;

    public KosarajuSharirSCC(Digraph G)
    {
        this(G,false);

    }
    public KosarajuSharirSCC(Digraph G, boolean traceFlag)
    {
        trace=traceFlag;
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        if(trace) {
            for (int v : dfs.reversePost())
            {
                System.out.print(v + " ");
            }
        }

        for (int v : dfs.reversePost())
        {

            if (!marked[v])
            {
                dfs(G, v);
                count++;
            }
        }
    }
    private void dfs(Digraph G, int v)
    {
        if(trace) System.out.println("dfs "+v);
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }
    public boolean stronglyConnected(int v, int w)
    { return id[v] == id[w]; }


    public String toString()
    {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<id.length;i++) {

            b.append(i + "; ");
            b.append(id[i] + "; ");
            b.append("\n ");
        }
        return b.toString();

    }
    /////////////////////////////////////////////////////
    public static void main(String[] args){
        System.out.println("START");

        test_QuizDigraphQ3();
        System.out.println("END");

    }
    private static void test_QuizDigraphQ3(){
        System.out.println("*test_QuizDigraphQ3");
        int size=0;
        Digraph g;
        //replicate the graph  the slide
        g = new Digraph(10);
        //  A B C D E F G H I J
        //  0 1 2 3 4 5 6 7 8 9
        /*
        A:  F G //order must be reverted!!
        B:  A
        C:  H G B D  // 3 1 6 7
        D:  E H // 7 4
        E:  J
        F:  G
        G:  B
        H:  G I // 8 6
        I:  J D // 3 9
        J:  D
         */

        g.addEdge(0,6); g.addEdge(0,5);
        g.addEdge(1,0);
        g.addEdge(2,3);g.addEdge(2,1);g.addEdge(2,6);g.addEdge(2,7);
        g.addEdge(3,7);g.addEdge(3,4);
        g.addEdge(4,9);
        g.addEdge(5,6);
        g.addEdge(6,1);
        g.addEdge(7,8);g.addEdge(7,6);
        g.addEdge(8,3);g.addEdge(8,9);
        g.addEdge(9,3);



        KosarajuSharirSCC scc = new KosarajuSharirSCC(g,true);
        System.out.println(scc.toString());

        /*
        postorder
        0 1 6 5 7 3 2 9 4 8
        A B G F H D C J E I
        !PERFECT!


         */

    }
}
