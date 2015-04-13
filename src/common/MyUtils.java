package common;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by maurizio.dapri on 2/16/2015.
 */


public class MyUtils {

    public static void Dump(Object[] list, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            System.out.print(toStringSafe(list[i]) + " ");
        }
        System.out.println();
    }

    public static String toStringSafe(Object o) {
        if (o !=null)
             return o.toString() ;
        else
            return "<null>" ;
    }

    public static void Dump(Object[] list) {
        Dump(list, 0, list.length-1);
    }
    public static void Dump(int[] list) {
        Integer[] vals= new Integer[list.length];
        for(int i=0;i<list.length;i++)
            vals[i]=list[i];
        Dump(vals, 0, list.length-1);
    }

    public static boolean isSorted(Comparable[] list, int lo,int hi) {
        boolean sorted = true;
        for (int i = lo; i < hi; i++) {
            if (list[i].compareTo(list[i + 1]) > 0) {
                sorted = false;
            }
        }
        return sorted;
    }

    //tells if all elements from start to end included are bigger than the key k, the flag is true if the relation is greater or equal
    public static boolean allBigger(Comparable k, Comparable[] a, int start, int end, boolean alsoEqual)
    {
        boolean allBigger=true;
        for (int i=start;i<=end;i++){
            int compare =(a[i].compareTo(k));
            if (compare <=0) {
                allBigger = alsoEqual && compare == 0;
            }
        }
        return allBigger;
    }

    //tells if all elements from start to end included are bigger than the key k, the flag is true if the relation is less or equal
    public static boolean allSmaller(Comparable k, Comparable[] a, int start, int end, boolean alsoEqual)
    {
        boolean allSmaller=true;
        for (int i=start;i<=end;i++){
            int compare =(a[i].compareTo(k));
            if (compare >=0) {
                allSmaller = alsoEqual && compare == 0;
            }
        }
        return allSmaller;
    }

    public static void main(String[] args){
        System.out.println("Start test");

        System.out.println("end");
    }

    public static void Assert(boolean assertTrue, String message) {
        if (!assertTrue){
            System.out.println(message);// stdout.print(message);
        }
    }

    public static void AssertEqual(Object actual, Object expected, String message) {
        boolean equals=true;
        if (actual==null || expected==null) {
            equals =( actual == null && expected == null);
        } else
        equals = actual.equals(expected);
        if (!equals){
            System.out.println(message + " Actual was: " + toStringSafe(actual)+". Expected was: " + toStringSafe(expected));// stdout.print(message);
        }
    }

    public static void AssertListEqual(Iterable actual, Iterable expected, String message) {
        boolean equals=true;
        boolean equalLength = true;
        Iterator iteratorExp= expected.iterator();
        Iterator iteratorAct= actual.iterator();
        Object elmActual;
        Object elmExpected;
        while(iteratorAct.hasNext() && equals) {
            if(!iteratorExp.hasNext()){
                equalLength=false;
                break;
            }
            else
            {
                elmActual= iteratorAct.next();
                elmExpected= iteratorExp.next();
                equals= elmActual.equals(elmExpected);
            }
        }
        //exit because iteratorAct has finished. Check for different length, that means that iteratorExp.hasNext() also has no more items
        if(iteratorExp.hasNext() )
            equalLength=false;

        if (!equals){
            System.out.println(message);
            System.out.println("/ Actual was:   " + toStringSafe(actual));
            System.out.println("/ Expected was: " + toStringSafe(expected));// stdout.print(message);
        }
        if(!equalLength){
            System.out.println(message + " length differs");
        }
    }

    public static void Trace(String message){
        System.out.println(message) ;
    }

}

