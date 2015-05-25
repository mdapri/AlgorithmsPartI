package common.datastruct;

import common.MyUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Copy if the Sedgewick Course implementation
 *  The <tt>Queue</tt> class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Queue<Item> implements Iterable<Item>, IQueue<Item>{

    public Queue(){
        N=0;
        first=null;
        last=null;
    }


    private int N;                // size of the queue
    //the head of the list
    Node<Item> first=null;
    Node<Item> last =null;

    /**
     * insert a new string onto queue. Adds to the tail
     * @param item
     */
    public void enqueue(Item item)  {
        Node<Item> oldlast =last;
        last=new Node<Item>();
        last.item=item;

        if(isEmpty()){
            first=last;
        }
        else {
            oldlast.next = last;
        }
        N++;

    }

    /**
     * remove and return the item least recently added, removes from the head
     * @return
     */
    public Item dequeue() {
        assert !isEmpty();

//        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Node<Item> oldFirst = first;
        first=first.next;
        if (isEmpty()) last = null;   // to avoid loitering
//        oldFirst.next=null;
        N--;
        return oldFirst.item;
    }


    public boolean isEmpty()  {return  first==null;}

    public int size() {return N;}

    /**
     * Returns an iterator to this queue that iterates through the items in FIFO order.
     * @return an iterator to this queue that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator(){

        return  new ListIterator<Item>(first);
    }


    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }


    public static void main(String[] args){
        test_isEmpty();
        test_enqueue();
        test_dequeue();
    }

    private static void test_isEmpty(){
        System.out.println("*test_isEmpty");
        Queue<String> l = new Queue<String>();
        MyUtils.Assert(l.isEmpty(), "!Test01");
        l.enqueue("a");
        MyUtils.Assert(!l.isEmpty(), "!Test02");
        l.dequeue();
        MyUtils.Assert(l.isEmpty(), "!Test03");
    }

    private static void test_enqueue() {
        System.out.println("*test_enqueue");
        Queue<String> l = new Queue<String>();

        l.enqueue("a");
        MyUtils.Assert(l.first.item=="a", "!Test01");
        l.enqueue("b");
        MyUtils.Assert(l.first.item=="a", "!Test02");
        MyUtils.Assert(l.first.next.item=="b", "!Test03");
    }

    private static void test_dequeue() {
        System.out.println("*test_dequeue");
        Queue<String> l = new Queue<String>();
        String expected=null;
        String actual=null;

        expected="a";
        l.enqueue(expected);
        actual=l.dequeue();
        MyUtils.AssertEqual(actual,expected,"!Test01");

        l = new Queue<String>();
        expected="a";
        l.enqueue("a");
        l.enqueue("b");
        actual=l.dequeue();
        MyUtils.AssertEqual(actual,expected,"!Test02");

        l = new Queue<String>();
        l.enqueue("a");
        l.enqueue("b");
        actual=l.dequeue();
        actual=l.dequeue();
        expected="b";
        MyUtils.AssertEqual(actual,expected,"!Test03");

    }

}
