package W2;
import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/27/2015.
 */
public class LinkedStackOfStrings {
    class Node {
        String item;
        Node next;
    }
//the head of the list
    Node first=null;

    //this is a condition to test
    public boolean isEmpty(){
        return first==null;
    }

    public void push(String s) {
        Node n= new Node();
        n.item=s;
        n.next=first;
        first=n;
    }

    public String pop(){
        Node popped=first;
        first=popped.next;
        return popped.item;
    }


    public static void main(String[] args){
        test_isEmpty();
        test_push();
        test_pop();
    }

    private static void test_isEmpty(){
        System.out.println("*test_isEmpty");
        LinkedStackOfStrings l = new LinkedStackOfStrings();
        MyUtils.Assert(l.isEmpty(),"!Test01");
        l.push("a");
        MyUtils.Assert(!l.isEmpty(), "!Test02");
        l.pop();
        MyUtils.Assert(l.isEmpty(), "!Test03");
    }

    private static void test_push() {
        System.out.println("*test_push");
        LinkedStackOfStrings l = new LinkedStackOfStrings();

        l.push("a");
        MyUtils.Assert(l.first.item=="a", "!Test01");
        l.push("b");
        MyUtils.Assert(l.first.item=="b", "!Test02");
    }

    private static void test_pop() {
        System.out.println("*test_pop");
        LinkedStackOfStrings l = new LinkedStackOfStrings();
        String expected=null;
        String actual=null;

        expected="a";
        l.push(expected);
        actual=l.pop();
        MyUtils.AssertEqual(actual,expected,"!Test01");

        l = new LinkedStackOfStrings();
        l.push("a");
        expected="b";
        l.push(expected);
        actual=l.pop();
        MyUtils.AssertEqual(actual,expected,"!Test02");

        l = new LinkedStackOfStrings();
        l.push("a");
        l.push("b");
        actual=l.pop();
        actual=l.pop();
        expected="a";
        MyUtils.AssertEqual(actual,expected,"!Test03");

    }

}
