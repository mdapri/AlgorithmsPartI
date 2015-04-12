package P2.W1;
import common.datastruct.*;

/**
 * Topological Sort of directed graph: slide 45
 */
public class DepthFirstOrder {

    private boolean trace=false;


    // the predecessor of the node
    private int[] toEdge;
    private Digraph graph;
    int start;
    private boolean marked[];
    //the node in visiting postorder
    private Stack<Integer> reversePost;


    public DepthFirstOrder(Digraph g){
        this(g,false);
    }

    public DepthFirstOrder(Digraph g, boolean traceFlag) {

        graph=g;
        trace=traceFlag;
        reversePost = new Stack<Integer>();
        marked= new boolean[g.V()];
        toEdge = new int[g.V()];

        for (int i=0; i<g.V();i++)
        {
            marked[i]=false;
            toEdge[i]=-1;
        }
        for(int i =0;i<graph.V();i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(Digraph g,int v ){

        if(trace) System.out.println("dfs "+v);

        marked[v]=true;
        for(int i:g.adj(v)){
            if(!marked[i]) {
                toEdge[i] = v;
                dfs(g, i);
            }
            else if(trace) System.out.println("check "+i);
        }
        reversePost.push(v);
        if(trace) System.out.println("done "+v);


    }

    public Iterable<Integer> reversePost()
    { return reversePost; }

    /////////////////////////////////////////////////////////
    public static void main(String[] args){
        System.out.println("START");

        test_Quiz();
        System.out.println("END");

    }
    private static void test_Quiz(){
        System.out.println("*test_Quiz");
        int size=0;
        Digraph g;
        //replicate the graph  the slide
        g = new Digraph(8);
        //  A B C D E F G H I J
        //  0 1 2 3 4 5 6 7 8 9
        /*
        A:  E B     // 0: 1 4
        B:  G E F   // 1: 5 4 6
        C:  B G D   // 2: 3 6 1
        D:  H       // 3: 7
        E:  F       // 4: 5
        F:  G       // 5: 6
        G:  H D     // 6: 7 3
        H:
         */
        // 0: 1 4
        g.addEdge(0,1);        g.addEdge(0,4);
        // 1: 5 4 6
        g.addEdge(1,5);g.addEdge(1,4);g.addEdge(1,6);
        // 2: 3 6 2
        g.addEdge(2,3);g.addEdge(2,6);g.addEdge(2,1);
        // 3: 7
        g.addEdge(3,7);
        // 4: 5
        g.addEdge(4,5);
        // 5: 6
        g.addEdge(5,6);
        // 6:  7 3
        g.addEdge(6,3);g.addEdge(6,7);



        DepthFirstOrder depthorder = new DepthFirstOrder(g,true);
        for (int i: depthorder.reversePost())
            System.out.print(i+" ");

        /*
        2 0 1 4 5 6 3 7
        C A B E F G D H
        //  A B C D E F G H I J
        //  0 1 2 3 4 5 6 7 8 9
         */

    }
}
