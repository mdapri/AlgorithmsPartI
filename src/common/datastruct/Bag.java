package common.datastruct;

import common.MyUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * just a copy of the course Alg4 library bag
 *  This implementation uses a singly-linked list with a static nested class Node.
 *  Insertion is done at the head of the Linked List
 *  See LinkedBag for the version from the
 *  textbook that uses a non-static nested class.
 */
public class Bag<Item> implements Iterable<Item> {
    private int N;               // number of elements in bag
    private Node<Item> first; // the head of the list

    public Bag(){
        first=null;
        N=0;
    }
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }



    /**
     * Adds the item to this bag.
     *
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        Node<Item> oldfirst=first;
        first=new Node<Item>();
        first.item=item;
        first.next=oldfirst;
        N++;
    }


    public static void main(String[] args) {
        test_isEmpty();
        test_Add();
        test_hasNext();
    }

    private static void test_isEmpty() {
        System.out.println("*test_isEmpty");
        Bag<Integer> b = new Bag<Integer>();
        MyUtils.Assert(b.isEmpty(), "!Test01");
        b.add(1);
        MyUtils.Assert(!b.isEmpty(), "!Test02");
        b.add(1);
        MyUtils.Assert(!b.isEmpty(), "!Test03");
    }

    private static void test_Add() {
        System.out.println("*test_Add");
        Bag<Integer> b = new Bag<Integer>();
        b.add(1);
        MyUtils.Assert(b.first!=null, "!Test01");
        MyUtils.Assert(b.size()==1, "!Test02");
        b.add(2);
        MyUtils.Assert(b.first!=null, "!Test03");
        MyUtils.Assert(b.size()==2, "!Test04");
        MyUtils.Assert(b.first.item==2, "!Test05");
    }

    private static void test_hasNext() {
        System.out.println("*test_hasNext");
        Bag<Integer> b = new Bag<Integer>();
        Iterator<Integer> i =b.iterator();
        MyUtils.Assert(!i.hasNext(), "!Test01");
        b.add(1);
        i =b.iterator();
        MyUtils.Assert(i.hasNext(), "!Test02");
        Integer actual = i.next();
        MyUtils.AssertEqual(actual, 1, "!Test03");
        MyUtils.Assert(!i.hasNext(), "!Test04");
    }
}
