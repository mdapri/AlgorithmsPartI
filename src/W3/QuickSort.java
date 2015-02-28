package W3;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/19/2015.
 */
public class QuickSort  {

    static int comparesCounter=0;

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        //No random shuffle in my implementation.. it will suffer of the N^2 problem
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert MyUtils.isSorted(a, 0, a.length - 1);
    }
    // returns the index where the lo value has been put
    private static int partition(Comparable[] a , int lo, int hi){
        assert lo<=hi;

        int i=lo; // note here: what happens if lo is the minimum? or the maximum?
        int j=hi+1;
        while (i<j){
            while(less(a[++i],a[lo])) { ++comparesCounter; if (i == hi) break; }//otherwise it evaluates the while after the upper bound is reached and IndexOutOfBound happens
            while (less(a[lo], a[--j])){ ++comparesCounter;if (j == lo) break; ;}
            if (i<=j) exch(a,i,j);
        }
        exch(a,lo,j);
        assert MyUtils.allBigger(a[j],a,j+1,hi,true);
        assert MyUtils.allSmaller(a[j],a,lo,j-1,true);
        return j;
    }

    private static void sort(Comparable[] a , int lo, int hi)
    {
        if (hi <= lo) return;
        int j =partition(a,lo,hi);
        //System.out.println("lo " + lo + ", hi " + hi + " ,j " + j+ ", counter " + comparesCounter);
        //MyUtils.Dump(a);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static boolean less(Comparable a,Comparable b) {
        return a.compareTo(b)<0;
    }

    private static void exch(Comparable[] a,int i, int j){
        Comparable swap = a[i];
        a[i] =a[j];
        a[j]= swap;
    }

    public static void main(String[] args){

//        TEST_partition();
//        Test_sort();
        //Test_performance();
        Assignment_test();
    }

    private static void TEST_partition() {
        System.out.println("*TEST_partition");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        //N-1 length
        int hi = vector.length-1;
        int low=0;


        System.out.println("//");
        MyUtils.Dump(vector);
        //partition on all the vector
        int j = partition(vector, low, hi);
        int expected = 2;

        MyUtils.Dump(vector);
        MyUtils.AssertEqual(expected, j, "Test1. The value " + vector[expected] + "is not in the correct place. There is " + vector[j] + " instead");

        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};

        hi = 3;
        low=1;
        System.out.println("//");
        MyUtils.Dump(vector);
//partition on all the
        j = partition(vector, low, hi);
        expected = 1;
        MyUtils.Dump(vector);
        MyUtils.AssertEqual(expected, j, "Test 2. The value " + vector[expected] + "is not in the correct place. There is " + vector[j] + " instead");

        //this causes a crash
        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        hi = 9;
        low=8;
        System.out.println("//");
        MyUtils.Dump(vector);
//partition on all the
        j = partition(vector, low, hi);
        expected = 9;
        MyUtils.Dump(vector);
        MyUtils.AssertEqual(expected, j, "Test 2. The value " + vector[expected] + "is not in the correct place. There is " + vector[j] + " instead");

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

    private static  void Test_performance(){
        System.out.println("*Test_performance");
//check what happens when the array has lot fo duplicates..
        Integer[] a= new Integer[]{3,3,3,3,2,3,3,3};
        //N-1 length
        int lo=0;
        int hi =a.length-1;

        System.out.println("//");
        MyUtils.Dump(a);
        //partition on all the vector
        sort(a, lo, hi);

//        MyUtils.Dump(a);
        MyUtils.Assert(MyUtils.isSorted(a,lo,hi), "Test4");
        //MyUtils.Assert(! MyUtils.isSorted(vector,0,vector.length-1), "Test32");



    }

    private static void Assignment_test()
    {
        Integer[] a= new Integer[] {73, 84 ,18, 11, 62,12 ,32 ,24 ,86 ,42 ,22 ,27 };
        int j=partition(a,0,a.length-1);
        MyUtils.Dump(a);
        System.out.println(j);

        String[] b = new String[]{"B", "A", "B", "B", "A", "B", "B", "A","A","B","A","A" };
         j=partition(b,0,b.length-1);
        MyUtils.Dump(b);
        System.out.println(j);

    }
}
