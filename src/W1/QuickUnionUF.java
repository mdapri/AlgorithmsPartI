package W1;
import common.MyUtils;

/**
 * Created by maurizio.dapri on 5/23/2015.
 */
public class QuickUnionUF implements  UF{

    protected int id[];

    public QuickUnionUF(int capacity) {
        id = new int[capacity];
        for (int index = 0; index < capacity; index++) {
            id[index] = index;
        }
    }
    @Override
    public boolean connected(int p, int q) {
        return root(p)==root(q);
    }

    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] =j ;
    }

    protected int root(int p){
        int i= p;
        while(i!=id[i])
            i=id[i];
        return i;
    }

    public static void main(String[] args) {
        System.out.println("BEGIN");
        // write your code here
        test_root_WhenNoUnionThenRootIsMe();
        test_root_When0Union1ThenRootIs1();
        test_root_When0Union1And1Union2ThenRootFor1And2Is2();
        test_root_When0_1and0_2And3_4and0_4ThenRootFor1234Is4();
        //test_findmin();
        //test_connected();
        System.out.println("END");
    }

    private static  QuickUnionUF CreateUF () {
        QuickUnionUF q= new QuickUnionUF(10);
        return q;
    }

    private  static void  test_root_WhenNoUnionThenRootIsMe(){
        System.out.println("*test_root_WhenNoUnionThenRootIsMe - Start");
        QuickUnionUF q = new QuickUnionUF(3);
        MyUtils.AssertEqual(q.root(1), 1, "Error2");
        MyUtils.AssertEqual (q.root(2),2, "Error3");
        MyUtils.AssertEqual (q.root(0),0, "Error1");
        System.out.println("*test_root_WhenNoUnionThenRootIsMe - Finish");
    }

    private  static void  test_root_When0Union1ThenRootIs1(){
        System.out.println("*test_root_When0Union1ThenRootIs1 - Start");
        QuickUnionUF q = new QuickUnionUF(3);
        q.union(0, 1);
        MyUtils.AssertEqual (q.root(0),1, "Error1");
        MyUtils.AssertEqual(q.root(1), 1, "Error2");
        System.out.println("*test_root_When0Union1ThenRootIs1 - Finish");
    }

    private  static void  test_root_When0Union1And1Union2ThenRootFor1And2Is2(){
        System.out.println("*test_root_When0Union1And1Union2ThenRootFor1And2Is2 - Start");

        QuickUnionUF q = new QuickUnionUF(3);
        q.union(0, 1);
        q.union(1, 2);
        MyUtils.AssertEqual (q.root(0),2, "Error1");
        MyUtils.AssertEqual(q.root(1), 2, "Error2");
        MyUtils.AssertEqual(q.root(2),2 , "Error3");

        System.out.println("*test_root_When0Union1And1Union2ThenRootFor1And2Is2 - Finish");
    }


    private  static void  test_root_When0_1and0_2And3_4and0_4ThenRootFor1234Is4(){
        System.out.println("*test_root_When0_1and0_2And3_4and0_4ThenRootFor1234Is4 - Start");

        QuickUnionUF q = new QuickUnionUF(5);
        q.union(0, 1);
        q.union(1, 2);
        q.union(3, 4);
        MyUtils.AssertEqual(q.root(3), 4, "Error0");
        q.union(0, 4);

        MyUtils.AssertEqual (q.root(0),4, "Error1");
        MyUtils.AssertEqual(q.root(1), 4, "Error2");
        MyUtils.AssertEqual(q.root(2),4 , "Error3");
        MyUtils.AssertEqual(q.root(3),4 , "Error4");
        MyUtils.AssertEqual(q.root(4),4 , "Error5");

        System.out.println("*test_root_When0_1and0_2And3_4and0_4ThenRootFor1234Is4 - Finish");
    }

    private  static void test_connected(){
        System.out.println("*test_connected");
        QuickUnionUF q= new QuickUnionUF(10);
        MyUtils.Dump(q.id);
        MyUtils.Assert(!q.connected(1,2), "Error 1");
        MyUtils.Dump(q.id);
        q.union(1,2);
        MyUtils.Assert(q.connected(1,2), "Error 2");
        MyUtils.Dump(q.id);

        q.union(5,4);
        q.union(2,5);
        MyUtils.Assert(q.connected(1,4), "Error 3");
        MyUtils.Dump(q.id);
    }
}

