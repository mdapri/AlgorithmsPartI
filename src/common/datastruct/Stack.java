package common.datastruct;

import common.MyUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Copy if the Sedgewick Course implementation
 * The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 *  for peeking at the top item, testing if the stack is empty, and iterating through
 *  the items in LIFO order.
 *  <p> 
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes.See {@link W2.LinkedStackOfStrings} for a similar realization
 *  The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Stack<Item> implements Iterable<Item>  {

    private int N;                // size of the stack
    //the head of the list
    Node<Item> first=null;

    //this is a condition to test
    public boolean isEmpty(){
        return first==null;
    }

    public void push(Item s) {
        Node<Item> n= new Node();
        n.item=s;
        n.next=first;
        first=n;
        N++;
    }

    public Item pop(){
        Node<Item> popped=first;
        first=popped.next;
        N--;
        return popped.item;

    }

    public int Count(){
        return N;
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public  Iterator<Item> iterator(){

        return  new ListIterator<Item>(first);
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public static void main(String[] args){
        test_isEmpty();
        test_push();
        test_pop();
    }

    private static void test_isEmpty(){
        System.out.println("*test_isEmpty");
       Stack<String> l = new Stack<String>();
        MyUtils.Assert(l.isEmpty(), "!Test01");
        l.push("a");
        MyUtils.Assert(!l.isEmpty(), "!Test02");
        l.pop();
        MyUtils.Assert(l.isEmpty(), "!Test03");
    }

    private static void test_push() {
        System.out.println("*test_push");
       Stack<String> l = new Stack<String>();

        l.push("a");
        MyUtils.Assert(l.first.item=="a", "!Test01");
        l.push("b");
        MyUtils.Assert(l.first.item=="b", "!Test02");
    }

    private static void test_pop() {
        System.out.println("*test_pop");
       Stack<String> l = new Stack<String>();
        String expected=null;
        String actual=null;

        expected="a";
        l.push(expected);
        actual=l.pop();
        MyUtils.AssertEqual(actual,expected,"!Test01");

        l = new Stack<String>();
        l.push("a");
        expected="b";
        l.push(expected);
        actual=l.pop();
        MyUtils.AssertEqual(actual,expected,"!Test02");

        l = new Stack<String>();
        l.push("a");
        l.push("b");
        actual=l.pop();
        actual=l.pop();
        expected="a";
        MyUtils.AssertEqual(actual,expected,"!Test03");

    }
}