package W3;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/22/2015.
 * this implements the 3 way quicskort to deal with duplicted keys
 */
public class Quick3Way {

    static int comparesCounter=0;

    public static void sort(Comparable[] a) {
        //No random shuffle in my implementation.. it will suffer of the N^2 problem
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert MyUtils.isSorted(a, 0, a.length - 1);
    }


    private static void sort(Comparable[] a , int lo, int hi)
    {
        if (hi <= lo) return;
        int i=lo; // the element under examination. note here: what happens if lo is the minimum? or the maximum?
        int lt=lo; //the index to the  element after the one  that is lower than the key
        int gt=hi;//index to the element before the first bigger
        Comparable v = a[lo];
        //the equals must be included
        while (i<=gt){


            int cmp= a[i].compareTo(v); ++comparesCounter;
            if(cmp<0) { //found something that is smaller
                exch(a, i, lt);
                i++;
                lt++;
            }
            else if(cmp>0){
                exch(a, i, gt);//no increment of i because we do not now if the element we have taken from gt is actually smaller that key
                gt--;
            }
            else i++; //when equal to the key, go on with the index

            //check that the definition is true
            assert assertLT(v,a,lt, lo);
            assert assertGT(v,a,gt, hi);

        }
        assert MyUtils.allBigger(v,a,gt+1,hi,true);
        assert MyUtils.allSmaller(v,a,lo,lt-1,true);


        //System.out.println("lo " + lo + ", hi " + hi + " ,j " + j+ ", counter " + comparesCounter);
        //MyUtils.Dump(a);
        //note here: we use gt and lt as new bounds not j-1 and j+1
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }

    private static boolean assertLT(Comparable v,Comparable[] a,int lt, int lo)
    {
        if(lt>lo) {return a[lt-1].compareTo(v) <0;} else return true;
    }

    private static boolean assertGT(Comparable v,Comparable[] a,int gt, int hi)
    {
        if(gt<hi) {return a[gt+1].compareTo(v) >0;} else return true;
    }

    private static boolean less(Comparable a,Comparable b) {
        return a.compareTo(b)<0;
    }

    private static void exch(Comparable[] a,int i, int j){
        Comparable swap = a[i];
        a[i] =a[j];
        a[j]= swap;
    }

    public static void main(String[] args) {
        Test_sort();
    }

    private static void Test_sort() {
        System.out.println("*Test_sort");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        //N-1 length
        int lo = 0;
        int hi = vector.length - 1;

//        System.out.println("//");
//        MyUtils.Dump(vector);
        //partition on all the vector
        sort(vector, lo, hi);

        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.isSorted(vector, lo, hi), "Test1");

        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        //N-1 length
        lo = 1; //E
        hi = 4; //E

        System.out.println("//");
        //MyUtils.Dump(vector);
        //partition on all the vector
        sort(vector, lo, hi);

        // MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.isSorted(vector, lo, hi), "Test2.1");
        MyUtils.Assert(!MyUtils.isSorted(vector, 0, vector.length - 1), "Test2.2");

        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        //N-1 length
        lo = 3;
        hi = 1;

        System.out.println("//");
        //MyUtils.Dump(vector);
        //partition on all the vector
        sort(vector, lo, hi);

        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.isSorted(vector, lo, hi), "Test3.1");
        MyUtils.Assert(!MyUtils.isSorted(vector, 0, vector.length - 1), "Test32");

    }

}
