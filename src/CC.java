import common.MyUtils;

/**
 * Conneccted component on an undurected graph
 */
public class CC {
    // the predecessor of the node
    private int[] component;
    int components;
    private Graph graph;
    int start;


    public CC(Graph g) {

        graph=g;
        components=0;
        component = new int[g.V()];
        for (int i=0; i<g.V();i++)
        {
            component[i]=-1;
        }
        for(int i =0;i<g.V();i++){
            if(component[i]==-1)
            {
                FindComponentFrom(g, i);
                components++;
            }
        }
    }

    private void FindComponentFrom(Graph g, int i) {
        DepthFirstPaths d= new DepthFirstPaths(g,i);
        for(int j =0;j<g.V();j++){
            if (d.hasPathTo(j))
                component[j]=components;
        }
        System.out.println(d.toString());
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
        test_Connected();

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
}
