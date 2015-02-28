package W2;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/16/2015.
 */
public class InsertionSort {

    public static void sort(Comparable[] a, int lo, int hi){
        for (int i = lo; i<=hi;i++){
            //int max=i;

            for (int j =i;j>lo;j--) {

                if (a[j].compareTo(a[j - 1]) < 0) exch(a, j, j - 1);
                else break;
            }
            assert MyUtils.isSorted(a,lo,i);
        }
    }

    public static void sort(Comparable[] a){
//        for (int i = 0; i<a.length;i++){
//            //int max=i;
//
//            for (int j =i;j>0;j--) {
//
//                if (a[j].compareTo(a[j - 1]) < 0) exch(a, j, j - 1);
//                else break;
//            }
//            assert MyUtils.isSorted(a,0,i);
//        }
        sort(a,0,a.length-1);
    }

    private static void exch(Comparable[] a,int i, int j){
        Comparable swap = a[i];
        a[i] =a[j];
        a[j]= swap;
    }

    ////////////////////// TEST
    public static void main(String[] args) {

        test_sort();
    }

    private static void test_sort() {
        System.out.println("*test_sort");
        Integer[] test = new Integer[]  {4,3,1};
        sort(test,0,0);
        MyUtils.Assert(MyUtils.isSorted(test, 0, 0), "Error 0.1");
        MyUtils.Dump(test);

        test = new Integer[]  {4,3,1};
        sort(test,0,1);
        MyUtils.Assert(MyUtils.isSorted(test, 0, 1), "Error 0.2");
        MyUtils.Dump(test);

        test = new Integer[]  {4,3,1};
        sort(test,1,2);
        MyUtils.Assert(MyUtils.isSorted(test, 1, 2), "Error 0.3");
        MyUtils.Dump(test);


        test = new Integer[]  {4,3,1};
        sort(test);
        MyUtils.Assert(MyUtils.isSorted(test, 0, test.length - 1), "Error 1.1");
        MyUtils.Dump(test);

        test = new Integer[]  {4,3,0,0,1,2,0};
        sort(test);
        MyUtils.Assert(MyUtils.isSorted(test, 0, test.length - 1), "Error 1.2");
        MyUtils.Dump(test);

        test = new Integer[]  {0,1,4,3,2};
        sort(test);
        MyUtils.Assert(MyUtils.isSorted(test,0,test.length-1),"Error 1.3");
        MyUtils.Dump(test);


        System.out.println("*test_sort end");
    }

}
