package W2;
import common.MyUtils;
/**
 * Created by maurizio.dapri on 2/16/2015.
 */
public class SelectionSort {

//finds the index of the maximum element from start, included, on the rigth
    private  static int getIndexOfMax(Comparable[] a, int start, int stop){
        assert (stop<a.length)&& (start>=0) && (start<=stop);
        int max=start;
        for (int i=start;i<=stop;i++)
        {
            if(a[max].compareTo(a[i])<0) max= i;
        }
        return max;
    }


    private  static int getIndexOfMin(Comparable[] a, int start, int stop){
//        System.out.println(start + "." + stop);
        assert (stop<a.length)&& (start>=0) && (start<=stop);
        int min=start;
        for (int i=start;i<=stop;i++)
        {
            if(a[min].compareTo(a[i])>0) min= i;
        }
        return min;
    }

    //swap 2 indices
    private static void exch(Comparable[] a,int i, int j){
        Comparable swap = a[i];
        a[i] =a[j];
        a[j]= swap;
    }

    public static void sort (Comparable[] a){
        int hi=a.length-1;
        for (int i = 0;i<a.length;i++){
            int min=i;
            //the minumum on the rigth
            int minResidual =getIndexOfMin(a,i,hi);
            if(min!=minResidual) exch(a,min,minResidual);
        }
    }


    public static void main(String[] args) {
        // write your code here
        //test_findmax();
        //test_findmin();
        test_sort();
    }

    //test part
    private static void test_findmax(){
        System.out.println("*test_findmax");
        // initialize an array and find the maximum given the initial step
        Integer[] test = new Integer[] {0,1,4,3,2};
        int actual = getIndexOfMax(test, 0,test.length-1);
        int expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 1");

        actual=getIndexOfMax(test, 2,test.length-1);
        expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 2");
        actual=getIndexOfMax(test, 3,test.length-1);
        expected=3;
        MyUtils.AssertEqual(actual, expected,"Error 3");
        actual=getIndexOfMax(test, 4,test.length-1);
        expected=4;
        MyUtils.AssertEqual(actual, expected,"Error 4");

        actual = getIndexOfMax(test, 0,1);
        expected=1;
        MyUtils.AssertEqual(actual, expected,"Error 5");

        //add a duplicate
        test = new Integer[] {0,1,4,4,3,2,4};

        actual=getIndexOfMax(test, 2,test.length-1);
        expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 6");

        actual=getIndexOfMax(test, 3,4);
        expected=3;
        MyUtils.AssertEqual(actual, expected,"Error 7");

        actual=getIndexOfMax(test, 4,test.length-1);
        expected=6;
        MyUtils.AssertEqual(actual, expected,"Error 8");

        System.out.println("*Test_constructor");

    }

    private static void test_findmin(){
        System.out.println("*test_findmin");
        // initialize an array and find the maximum given the initial step
        Integer[] test = new Integer[]  {4,3,0,1,2};
        int actual = getIndexOfMin(test, 0, test.length - 1);
        int expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 1");

        actual=getIndexOfMin(test, 2, test.length - 1);
        expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 2");
        actual=getIndexOfMin(test, 3, test.length - 1);
        expected=3;
        MyUtils.AssertEqual(actual, expected,"Error 3");
        actual=getIndexOfMin(test, 4, test.length - 1);
        expected=4;
        MyUtils.AssertEqual(actual, expected,"Error 4");

        actual = getIndexOfMin(test, 0, 1);
        expected=1;
        MyUtils.AssertEqual(actual, expected,"Error 5");

        //add a duplicate
        test = new Integer[]  {4,3,0,0,1,2,0};

        actual=getIndexOfMin(test, 2, test.length - 1);
        expected=2;
        MyUtils.AssertEqual(actual, expected,"Error 6");

        actual=getIndexOfMin(test, 3, 4);
        expected=3;
        MyUtils.AssertEqual(actual, expected,"Error 7");

        actual=getIndexOfMin(test, 4,test.length-1);
        expected=6;
        MyUtils.AssertEqual(actual, expected,"Error 8");

        System.out.println("*test_findmin end");

    }


    private static void test_sort() {
        System.out.println("*test_sort");
        Integer[] test = new Integer[]  {4,3,0,0,1,2,0};
        sort(test);
        MyUtils.Assert(MyUtils.isSorted(test,0,test.length-1),"Error 1");
        MyUtils.Dump(test);

       test = new Integer[]  {0,1,4,3,2};
        sort(test);
        MyUtils.Assert(MyUtils.isSorted(test,0,test.length-1),"Error 2");
        MyUtils.Dump(test);

        System.out.println("*test_sort end");
    }
}
