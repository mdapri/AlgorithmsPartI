package W3;
import common.MyUtils;

import java.util.Objects;

/**
 * Created by maurizio.dapri on 2/16/2015.
 */
public class MergeSort {

    static int recurseCount;
    //uses auxiliary vector. Copy the input a into the auxiliary at first step
    protected static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert MyUtils.isSorted(a, lo, mid);
        assert MyUtils.isSorted(a, mid + 1, hi);

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        int k = lo;
        //proceed with the comparison
        //Dump(a,i, mid);
        //Dump(a,j, hi);

        while (k <= hi) { //k<=hi?
            if (i > mid)                             a[k++] = aux[j++];
            else if (j > hi)                         a[k++] = aux[i++];
            else if (aux[i].compareTo(aux[j]) <= 0)  a[k++] = aux[i++];
            else                                    a[k++] = aux[j++]; //if (aux[i].compareTo(aux[j]) > 0)
        }
        assert MyUtils.isSorted(a, lo, hi);
    }

    //does not allocate the aux here to avoid repetition
    //recursive sort step using the same @big@ external allocated auxiliary array
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
//        recurseCount++;
//        System.out.println(recurseCount);
        if(hi<=lo) { MyUtils.Dump(a); return;}
        int mid = lo+ (hi-lo)/2;
        sort(a,aux,lo, mid);
        sort(a,aux,mid+1, hi);
        merge(a,aux,lo,mid,hi);
       // MyUtils.Dump(a);
    }

    public static void sort(Comparable[] a){
        Comparable[] aux;
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }


    ////////// TEST section



    public static void main(String[] args){
        //TEST_merge();
        //TEST_Sort();
        Assessment_Test();
    }

    private static void TEST_merge(){
        System.out.println("TEST_merge");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector= new String[] {  "E","E","G","M", "R",  "O", "R" ,"S","T" ,"X"  };
        String [] aux = new String[vector.length];
        int hi=vector.length-1;
        int lo=0;
        int mid =(hi-lo)/2;
        //MergeSort ms=new MergeSort();
        merge(vector, aux, lo, mid, hi);
        //System.out.println(vector);
        MyUtils.Dump(aux);
        System.out.println("//");
        MyUtils.Dump(vector);
    }

    private static void TEST_Sort(){
        System.out.println("TEST_Sort");
        //String[] vector= new String[] { "M", "E", "R", "G", "E", "S", "O", "R" ,"T" ,"E" ,"X" ,"A" ,"M", "P", "L", "E" };
        String[] vector= new String[] { "G", "E","M", "R", "E", "O", "R" ,"S","T" ,"X"  };
        String [] aux = new String[vector.length];
        //leave out the last 2 elements
        int hi=vector.length-2;
        int lo=1;

        //MergeSort ms=new MergeSort();
        sort(vector, aux, lo, hi);
        //System.out.println(vector);
        assert vector[0] =="G";
        assert vector[hi+1] =="X";
        //Dump(aux);
        System.out.println("//");
        MyUtils.Dump(vector);
    }

    private static void  Assessment_Test(){
        Integer[] a = new Integer[] {31 ,	30, 	55, 	82, 	70, 	95, 	41, 	36, 	75, 	81, 	28, 	93};
        sort(a);
    }



}
