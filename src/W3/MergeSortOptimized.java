package W3;

import W2.InsertionSort;
import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/16/2015.
 */
public class MergeSortOptimized extends MergeSort {
    //this is the CUTOFF value to decide if use mergeSort or insertion sort
    static int CUTOFF  = 7;
//    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
//        if(hi<=lo) return;
//        //use insertion sort
//        if(hi-lo+1<CUTOFF) {
//            InsertionSort.sort(a,lo,hi);
//            //System.out.println("cutoff");
//        }else { //normal procedure
//            int mid = lo + (hi - lo) / 2;
//            sort(a, aux, lo, mid);
//            sort(a, aux, mid + 1, hi);
//            //second improvement: merge can be avoided if second half is already bigger than first half
//            if(a[mid].compareTo(a[mid+1])>0) {
//                merge(a, aux, lo, mid, hi);
//                //System.out.println("not skipped");
//            }
//        }
//    }

    //optimized also with auxiliary array swap
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi<=lo) return;
        //use insertion sort
        if(hi-lo+1<CUTOFF) {
            InsertionSort.sort(aux, lo, hi);
            //System.out.println("cutoff");
        } else {
            int mid = lo + (hi - lo) / 2;
            sort(aux, a, lo, mid);
            sort(aux, a, mid + 1, hi);
            //swap aux and a
            merge(a, aux, lo, mid, hi);
        }
    }

    //swap the aux and a vectors
    protected static void merge(Comparable[] a, Comparable[] aux, int lo,int mid, int hi){
        assert MyUtils.isSorted(a, lo, mid);
        assert MyUtils.isSorted(a, mid + 1, hi);

        int i=lo;
        int j = mid+1;

        for (int k=lo; k<=hi;k++){
            if(i>mid)                           aux[k]=a[j++];
            else if(j>hi)                       aux[k]=a[i++];
            else if(a[i].compareTo(a[j])>0)     aux[k]=a[j++];
            else                                aux[k]=a[i++];
        }
        //NOTE here is AUX that is sorted, not A!!
        assert MyUtils.isSorted(aux, lo, hi);

    }

    public static void sort(Comparable[]a){
        int hi =a.length-1;
        int lo=0;
        Comparable [] aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        sort(a,aux,lo,hi);
    }

    public static void main(String[] args){

        TEST_Sort();
    }

    private static void TEST_Sort() {
        System.out.println("TEST_Sort");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "T", "X"};
        String [] aux = new String[vector.length];
        initVectorTest(vector,aux);

        sort(vector, aux, 1,9 );
        System.out.println("//");
        MyUtils.Dump(vector);

        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "T", "X"};
        initVectorTest(vector,aux);
        sort(vector, aux, 0,4 );
        sort(vector, aux, 5,9 );
        sort(vector, aux, 0,9 );
        System.out.println("//");
        MyUtils.Dump(vector);

        vector = new String[]{"G", "E", "M", "R", "E", "O", "X", "R", "S", "T"};
        initVectorTest(vector,aux);
        sort(vector, aux, 0,2 );
//        sort(vector, aux, 5,9 );
        MyUtils.Dump(vector);
        sort(vector, aux, 0,5 );
        System.out.println("//");
        MyUtils.Dump(vector);

    }
    private static void initVectorTest(Comparable[]a, Comparable[]aux){
        for (int k = 0; k <= a.length-1; k++)
            aux[k] = a[k];
    }
}
