package W4;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/22/2015.
 * using the heap ordered implementation,
 * that uses an array implementation navigated using the power of 2 addressing
 * the index 0 in the array representation is NOT used
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;//points to the last element in the pq;

    //create an empty priority queue
    public MaxPQ(int capacity) {
        pq= (Key[]) new Comparable[capacity+1]; //the location 0 is not used
    }

    //create a priority queue with given keys. the array should be a PQ
    MaxPQ(Key[] a) {
        pq= (Key[]) new Comparable[a.length+1];
        N=0;
        for (int i=0;i<a.length ;i++) {
            pq[i+1]=a[i];
            if(a[i]!=null) N++;

        }

    }
    //insert a key into the priority queue
    //add a node as last element than swim up
    void insert(Key v){
        //the element at position 0 is not used;
        pq[++N] =v ;
        swim(N);
    }

    //return and remove the largest key
    // Exchange root with node at end, then sink it down.
    // Cost. At most 2 lg N compares.
    Key delMax()  {
        Key max=pq[1];
        exch(1,N--);
        sink(1);
        pq[N+1] = null;
        return max;

    }
    boolean isEmpty() {return  N==0 ;} // is the priority queue empty?
    //Key max() return the largest key
    //int size() number of entries in the priority queue

    //brings a key upper in the tree in case of insertion or when Child's key becomes larger  than its parent's key.
    //useful in insertion
    private void swim(int k){
        //root element is 1
        //int parent = k/2;
        while(k>1 && less(k/2,k)) {
            exch(k, k / 2);
            k=k/2;
        }
    }

    //Parent's key becomes smaller than one (or both) of its children's
    private void sink(int k){

        while(2*k<=N){
            int maxChild=2*k;

            if(maxChild< N && less(maxChild, maxChild + 1)){ //maxChild< N redundant? see the while condition
                maxChild++;
            }
            if (!less(k, maxChild)) break;

            //swap and continue sinking
            exch(k,maxChild);
            k=maxChild;
        }

    }

    //tells if the key in i is less than key in j
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) <0;
    }

    private void exch(int i, int j){
        exch(pq,i,j);
    }

    private void exch(Key[] a,int i, int j){
        Key swap = a[i];
        a[i] =a[j];
        a[j]= swap;
    }
    public static void main(String[] args){

//        TEST_partition();
//        Test_sort();
        //Test_performance();
        Assignment_test();
    }

    private static void Assignment_test(){
        System.out.println("Q1");
        Integer[] a= new Integer[] {93,88,82,56,75,58,61,43,35,63,null,null,null};
        MaxPQ m= new MaxPQ(a);
        m.insert(46);
        m.insert( 66 );
        m.insert(47);
        MyUtils.Dump(m.pq);

        System.out.println("Q2");

        a= new Integer[] {93,74,92,61,62,18,68,23,20,44};
        System.out.println(m.N+" "+ m.pq[m.N]);
        m= new MaxPQ(a);
        m.delMax();
        m.delMax();
        m.delMax();
        MyUtils.Dump(m.pq);

        System.out.println("Q1.1");
        a= new Integer[] {96,85,82,67,41,73,53,12,21,18,null,null,null};
        m= new MaxPQ(a);
        m.insert(59 );
        m.insert( 83  );
        m.insert(98 );
        MyUtils.Dump(m.pq);

        System.out.println("Q2.1");

        a= new Integer[] {98,86,70,75,78,55,37,41,64,33};
        System.out.println(m.N+" "+ m.pq[m.N]);
        m= new MaxPQ(a);
        m.delMax();
        m.delMax();
        m.delMax();
        MyUtils.Dump(m.pq);



    }
}
