import common.MyUtils;

/**
 * Conneccted component on an undurected graph
 */
public class CC {

    private boolean trace=false;
    // the predecessor of the node
    private int[] component;
    int components;
    private Graph graph;
    int start;

    public CC(Graph g) {
        this(g,false);
    }

    public CC(Graph g,boolean traceFlag) {

        graph=g;
        components=0;
        trace=traceFlag;
        component = new int[g.V()];
        for (int i=0; i<g.V();i++)
        {
            component[i]=-1;
        }
        for(int i =0;i<graph.V();i++){
            if(component[i]==-1)
            {
                FindComponentFrom(graph, i);
                components++;
            }
        }
    }

    private void FindComponentFrom(Graph g, int i) {
        DepthFirstPaths d;

        if(trace)
            d= new DepthFirstPaths(g,i,true);
        else
            d=new DepthFirstPaths(g,i);

        for(int j =0;j<g.V();j++){
            if (d.hasPathTo(j))
                component[j]=components;
        }
       if(trace) System.out.println(d.toString());
    }

    public int count()    { return components; }
    public int id(int v)    { return component[v]; }


    public String toString()
    {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<graph.V();i++) {

            b.append(i + "; ");
            b.append(component[i] + "; ");
            b.append("\n ");
        }
        return b.toString();

    }



    ////////////////////////////////////////////

    public static void main(String[] args){
        System.out.println("START");
        //test_Constructor();
        //test_Connected();
        test_Quiz();
        System.out.println("END");

    }
    private static void test_Connected(){
        System.out.println("*test_Connected");
        int size=0;
        Graph g;
        //replicate the graph  the slide
        g = new Graph(13);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,5);
        g.addEdge(0,6);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(4,5);
        g.addEdge(4,6);

        g.addEdge(7,8);

        g.addEdge(9,10);
        g.addEdge(9,11);
        g.addEdge(9,12);
        g.addEdge(11,12);


        CC cc = new CC(g);

        MyUtils.Assert(cc.id(1) == 0, "!Test01");
        MyUtils.Assert(cc.id(0) ==0, "!Test02");
        MyUtils.Assert(cc.id(2) ==0, "!Test03");
        MyUtils.Assert(cc.id(7) ==1, "!Test04");
        MyUtils.Assert(cc.id(9) ==2, "!Test05");
        MyUtils.Assert(cc.id(12) ==2, "!Test06");

    }

    private static void test_Quiz(){
        System.out.println("*test_Quiz");
        int size=0;
        Graph g;
        //replicate the graph  the slide
        g = new Graph(10);
        //  A B C D E F G H I J
        //  0 1 2 3 4 5 6 7 8 9
        /*
        A:  B F G  // 0: 1 5 6
        B:  A H G  // 1: 0 7 6
        C:  I D    // 2: 8 3
        D:  I C    // 3: 8 2
        E:  J      // 4: 9
        F:  G A    // 5: 6 0
        G:  F B A H // 6: 5 1 0 7
        H:  B G     // 7: 1 6
        I:  D C     // 8: 3 2
        J:  E       // 9: 4
         */
        //A:  B F G
        g.addEdge(0,1);        g.addEdge(0,5);        g.addEdge(0,6);
        // 1: 0 7 6
        g.addEdge(1,7);g.addEdge(1,6);
        // 2: 8 3
        g.addEdge(2,8);g.addEdge(2,3);
        // 3: 8 2
        g.addEdge(3,8);
        // 4: 9
        g.addEdge(4,9);
        // 5: 6 0
        g.addEdge(5,6);
        // 6: 5 1 0 7
        g.addEdge(6,7);



        CC cc = new CC(g,true);

        System.out.println(cc.toString());
        /*
        A 0; 0;
        B 1; 0;
        C 2; 1;
        D 3; 1;
        E 4; 2;
        F 5; 0;
        G 6; 0;
        H 7; 0;
        I 8; 1;
        J 9; 2;
         */

    }
}
