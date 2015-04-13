package W4;

import common.MyUtils;

/**
 * My personal realization using the PQ as base class
 */
public class MinPQ <Key extends Comparable<Key>> extends PQ<Key > {

    public MinPQ(int capacity) {
        super(capacity);
    }

    //create a priority queue with given keys. the array should be a PQ
    MinPQ(Key[] a) {
        super(a);
    }

    /**
     * tells if the key in i has to stay lower in the tree than key in j
     * @param i
     * @param j
     * @return
     */

    protected boolean lower(int i, int j){
        return pq[i].compareTo(pq[j]) > 0;
    }

    public static void main(String[] args) {

        System.out.println("START");
        Assignment_test();

        System.out.println("END");

    }

    private static void Assignment_test() {
        System.out.println("Q1");
        Integer[] a1,a2, a3; // = new Integer[]{93, 88, 82, 56, 75, 58, 61, 43, 35, 63, null, null, null};
        MinPQ<Integer> m = new MinPQ<Integer>(30);
        m.insert(46);
        m.insert(66);
        m.insert(47);
        m.insert(93 );
        m.insert( 88);
        m.insert( 82);
        m.insert(56);
        m.insert( 75);
        m.insert( 58);

        MyUtils.Dump(m.pq);

        System.out.println("Q2");

//        a1 = ((Integer[])m.pq).clone() ; //new Integer[]{93, 74, 92, 61, 62, 18, 68, 23, 20, 44};
//        a2 = ((Integer[])m.pq).clone();
//        a3 = ((Integer[])m.pq).clone() ; //new Integer[]{93, 74, 92, 61, 62, 18, 68, 23, 20, 44};
//
//                //System.out.println(m.N + " " + m.pq[m.N].toString());
//        m = new MinPQ<Integer> (a1);
        m.delTop();
        m.delTop();
        m.delTop();
        MyUtils.Dump(m.pq);

        System.out.println("Q1.1");

        //a = new Integer[]{96, 85, 82, 67, 41, 73, 53, 12, 21, 18, null, null, null};
        //m = new MinPQ<Integer> (a2);
        m.insert(59);
        m.insert(83);
        m.insert(98);
        MyUtils.Dump(m.pq);

        System.out.println("Q2.1");

        //a = new Integer[]{98, 86, 70, 75, 78, 55, 37, 41, 64, 33};
        //System.out.println(m.N + " " + m.pq[m.N]);
       // m = new MinPQ(a3);
        m.delTop();
        m.delTop();
        m.delTop();
        MyUtils.Dump(m.pq);


    }

}