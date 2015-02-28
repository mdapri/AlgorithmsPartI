package W4;

import common.MyUtils;

/**
 * Created by maurizio.dapri on 2/27/2015.
 * Symbol Table (ST) implementation using linked list
 *  see slides 3.1 for reference
 */
public class LinkedST <Key extends Comparable<Key>,Value>{

    class Node<Key,Value>{
        Key key;
        Value value;
        Node next;
    }

//create a symbol table
    public LinkedST(){}
    Node first=null;

//    put key-value pair into the table
//            (remove key from table if value is null)
    public void put(Key key, Value val){
        Node<Key,Value> n= new Node<Key,Value>();
        n.key=key;
        n.value=val;
        Node<Key,Value> x=first;
//        n.next=first;
//        first=n;
        while(x!=null) {
            int cmp = key.compareTo(x.key);
            if (cmp != 0)
                x = x.next;
            if (cmp == 0) {
                x.value = val;
                return;
            }
        }
        x=n;
        return;

      }
//    value paired with key
//            (nullif key is absent)
    public Value get(Key key){return null;}
    //remove key(and its value) from table
    void delete(Key key) {;}
    //is there a value paired with key?
    boolean contains(Key key) {
        return get(key)!=null;
    };
    //is the table empty?
    boolean isEmpty(){return  first==null;}
    //number of key-value pairs in the table
    int size(){return 0;}
    //all the keys in the table
    Iterable<Key> keys() {return null;}

    public static void main(String[] args){
        test_isEmpty();
       // test_Contains();
        test_Get();

//        test_push();
//        test_pop();
    }

    private static void test_isEmpty(){
        System.out.println("*test_isEmpty");
        LinkedST<String,Integer> l = new LinkedST<String,Integer>();
        MyUtils.Assert(l.isEmpty(), "!Test01");
        l.put("a", 1);
        MyUtils.Assert(!l.isEmpty(), "!Test02");
        l.put("b", 1);
        MyUtils.Assert(!l.isEmpty(), "!Test03");
    }

    private static void test_Contains(){
        System.out.println("*test_Contains");
        LinkedST<String,Integer> l = new LinkedST<String,Integer>();
        String key= "a";
        MyUtils.Assert(!l.contains(key), "!Test01");

        l.put(key, 1);
        MyUtils.Assert(l.contains(key), "!Test02");
        key="b";
        l.put(key,1);
        MyUtils.Assert(l.contains(key), "!Test03");
        MyUtils.Assert(!l.contains(""), "!Test04");

    }

    private static void test_Get(){
        System.out.println("*test_Get");
        LinkedST<String,Integer> l = null;
        Integer expected=null;
        Integer actual=null;
        String key= null;

        l=new LinkedST<String,Integer>();
        expected=null;
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test01");

        key="a";
        l=new LinkedST<String,Integer>();
        expected=null;
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test02");

        key="a";
        l=new LinkedST<String,Integer>();
        expected=1;
        l.put(key, expected);
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test03");

        key="b";
        l=new LinkedST<String,Integer>();
        expected=2;
        l.put(key, expected);
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test04");



    }


}

