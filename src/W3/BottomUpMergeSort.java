package W3;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/17/2015.
 */
public class BottomUpMergeSort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo,int mid, int hi){
        assert MyUtils.isSorted(a, lo, mid);
        assert MyUtils.isSorted(a, mid + 1, hi);
//first copy
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;
        int k = lo;

        while (k <= hi) { //k<=hi?
            if (i > mid)                             a[k++] = aux[j++];
            else if (j > hi)                         a[k++] = aux[i++];
            else if (aux[i].compareTo(aux[j]) <= 0)  a[k++] = aux[i++];
            else                                     a[k++] = aux[j++]; //if (aux[i].compareTo(aux[j]) > 0)
        }
        assert MyUtils.isSorted(a, lo, hi);
    }

    public  static void sort(Comparable[] a){
        int N=a.length;
        Comparable[] aux = new Comparable[N];
        for ( int sz=1; sz < N; sz+=sz ){
            for (int lo = 0; lo<N-sz; lo+=sz+sz) {
                int mid= lo+sz-1;
                int hi= Math.min(mid + sz, N - 1);
                merge(a, aux,lo,mid,hi);
            }
        }
    }


    public static void main(String[] args){

        TEST_Sort();
    }

    private static void TEST_Sort() {
        System.out.println("TEST_Sort");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        String [] aux = new String[vector.length];
        initVectorTest(vector,aux);

        sort(vector );
        System.out.println("//");
        MyUtils.Dump(vector);

        vector = new String[]{"G", "E", "M", "R", "E", "O", "R", "S", "X", "T"};
        initVectorTest(vector,aux);
        sort(vector );
        System.out.println("//");
        MyUtils.Dump(vector);

        vector = new String[]{"G", "E", "M", "R", "E", "O", "X", "T", "S", "R"};
        initVectorTest(vector,aux);
        sort(vector );
//        sort(vector, aux, 5,9 );
        MyUtils.Dump(vector);
        sort(vector );
        System.out.println("//");
        MyUtils.Dump(vector);

    }
    private static void initVectorTest(Comparable[]a, Comparable[]aux){
        for (int k = 0; k <= a.length-1; k++)
            aux[k] = a[k];
    }
}
