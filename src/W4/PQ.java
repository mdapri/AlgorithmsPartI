package W4;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 4/12/2015.
 * using the heap ordered implementation,
 * that uses an array implementation navigated using the power of 2 addressing
 * the index 0 in the array representation is NOT used
 */
public abstract class PQ <Key extends Comparable<Key>> {
    protected Key[] pq;
    protected int N;//points to the last element in the pq;

    //create an empty priority queue
    public PQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; //the location 0 is not used
    }

    //create a priority queue with given keys. the array should be a PQ
    PQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        N = 0;
        for (int i = 0; i < a.length; i++) {
            pq[i + 1] = a[i];
            if (a[i] != null) N++;

        }

    }

    //insert a key into the priority queue
    //add a node as last element than swim up
    public void insert(Key v) {
        //the element at position 0 is not used;
        pq[++N] = v;
        swim(N);
    }

    //return and remove the uppermost  key
    // Exchange root with node at end, then sink it down.
    // Cost. At most 2 lg N compares.
    public Key delTop() {
        Key top = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return top;

    }

    public boolean isEmpty() {
        return N == 0;
    } // is the priority queue empty?
    //Key max() return the largest key
    //int size() number of entries in the priority queue

    //brings a key upper in the tree in case of insertion or when Child's key becomes smaller  than its parent's key.
    //useful in insertion
    private void swim(int k) {
        //root element is 1
        //int parent = k/2;
        while (k > 1 && lower(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    //Parent's key becomes smaller than one (or both) of its children's
    private void sink(int k) {

        while (2 * k <= N) {
            int maxChild = 2 * k;

            if (maxChild < N && lower(maxChild, maxChild + 1)) { //maxChild< N redundant? see the while condition
                maxChild++;
            }
            if (!lower(k, maxChild)) break;

            //swap and continue sinking
            exch(k, maxChild);
            k = maxChild;
        }

    }

    //tells if the key in i has to stay lower in the tree than key in j
    protected abstract boolean lower(int i, int j);

    private void exch(int i, int j) {
        exch(pq, i, j);
    }

    private void exch(Key[] a, int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}