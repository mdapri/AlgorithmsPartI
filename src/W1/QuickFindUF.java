package W1;

import common.MyUtils;

/**
 * connecting 2 nodes sets the ID of all the nodes in the connected component as equal
 */
public class QuickFindUF implements UF{

    private int[] id;
    public QuickFindUF(int capacity) {
        id = new int[capacity];

        for (int index = 0; index < capacity; index++) {
            id[index] = index;
        }
    }
    public boolean connected(int p, int q){
        assert p<id.length;
        assert q<id.length;

        return false;
    }

    public void union(int p, int q){
        assert p<id.length;
        assert q<id.length;
        ;
    }

    public static void main(String[] args) {
        // write your code here
        //test_findmax();
        //test_findmin();
        test_connected();
    }

    private  static void test_connected(){
        System.out.println("*test_connected");
        QuickFindUF q= new QuickFindUF(10);
        MyUtils.Dump(q.id);
        MyUtils.Assert(!q.connected(1,2), "Error 1");
        MyUtils.Dump(q.id);
        q.union(1,2);
        MyUtils.Assert(q.connected(1,2), "Error 2");
        MyUtils.Dump(q.id);
    }
}
