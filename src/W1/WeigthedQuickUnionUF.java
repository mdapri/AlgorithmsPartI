package W1;

/**
 *
 */
public class WeigthedQuickUnionUF  extends QuickUnionUF {


    /**
     * the size for each node of the subtree rooted at it
     */
    int height[];


    public WeigthedQuickUnionUF(int capacity) {
        super(capacity);
        for (int index = 0; index < capacity; index++) {
            height[index] = 1;
        }

    }
//    @Override
//    public boolean connected(int p, int q) {
//        return root(p)==root(q);
//    }

    @Override
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if(height[rootQ]> height[rootP]) {
            id[rootP] = rootQ;

        }
        else if(height[rootQ]< height[rootP]) {
            id[rootQ] = rootP;
        }
        else {
            id[rootQ] = rootP;
            height[rootP]++;
        }

    }

//    protected int root(int p){
//        int i= p;
//        while(i!=id[i])
//            i=id[i];
//        return i;
//    }

    public static void main(String[] args) {
        System.out.println("BEGIN");
        // write your code here

        //test_findmin();
        //test_connected();
        System.out.println("END");
    }

    private static WeigthedQuickUnionUF CreateUF() {
        WeigthedQuickUnionUF q = new WeigthedQuickUnionUF(10);
        return q;
    }
}
