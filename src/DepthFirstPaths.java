import common.MyUtils;


import java.util.Stack;


/**
 * Created by maurizio.dapri on 3/28/2015.
 */
public class DepthFirstPaths {

    private boolean trace=false;

    int timer;
    // time the dfs enters the node
    private int in[];
    // time the dfs leave the node
    private int out[];


    // the predecessor of the node
    private int[] toEdge;
    private IGraph graph;
    int start;
    private boolean marked[];

    public DepthFirstPaths(IGraph g, int s){
        this(g,s,false);
    }

    public DepthFirstPaths(IGraph g, int s, boolean traceFlag) {
        start=s;
        graph=g;
        trace=traceFlag;
        timer=0;
        in = new int[g.V()];
        out = new int[g.V()];
        marked= new boolean[g.V()];
        toEdge = new int[g.V()];
        for (int i=0; i<g.V();i++)
        {
            marked[i]=false;
            in[i] =0;
            out[i]=0;
            toEdge[i]=-1;
        }

        dfs(graph,start);
    }



    public boolean hasPathTo(int v){
        return marked[v];
    }

    public  Iterable<Integer> pathTo(int v){
        Stack<Integer> path = new Stack<Integer>();
        if (hasPathTo(v)) {
            for (int i = v; i != start; i = toEdge[i]) {
                path.push(i);
            }
            path.push(start);
        }
        return path;
    }

    public int visitIn(int v){
        return in[v];
    }

    public int out(int v){
        return out[v];
    }

    private void dfs(IGraph g,int v ){
        if(trace) System.out.println("dfs "+v);
        in[v] =timer++;
        marked[v]=true;
        for(int i:g.adj(v)){
            if(!marked[i]) {
                toEdge[i] = v;
                dfs(g, i);
            }
            else if(trace) System.out.println("check "+i);
        }

        if(trace) System.out.println("done "+v);

        out[v]=timer++;
    }


    public String toString()
    {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<graph.V();i++) {

            b.append(i + "; ");
            b.append(marked[i] + "; ");
            b.append(toEdge[i] + "; ");
            b.append(in[i] + "; ");
            b.append(out[i] + "; ");
            b.append("\n ");
        }
        return b.toString();

    }


    /////////////////////////// TEST
    public static void main(String[] args){
        System.out.println("START");
        test_Constructor();


        System.out.println("END");

    }
    private static void test_Constructor(){
        System.out.println("*test_Constructor");
        int size=0;
        Graph g;
        g = new Graph(1);
        DepthFirstPaths d = new DepthFirstPaths(g,0);
        MyUtils.Assert(d.hasPathTo(0) == true, "!Test01");
        MyUtils.Assert(d.visitIn(0) ==0, "!Test02");
        MyUtils.Assert(d.out[0] ==1, "!Test03");
        //add
        g = new Graph(2);
        d = new DepthFirstPaths(g,0);
        MyUtils.Assert(d.hasPathTo(0) == true, "!Test04");
        MyUtils.Assert(d.visitIn(0) ==0, "!Test05");
        MyUtils.Assert(d.out[0] ==1, "!Test06");
        MyUtils.Assert(d.marked[0] ==true, "!Test07");

        MyUtils.Assert(d.visitIn(1) ==0, "!Test08");
        MyUtils.Assert(d.out[1] ==0, "!Test09");
        MyUtils.Assert(d.marked[1] ==false, "!Test10");
    }
}
