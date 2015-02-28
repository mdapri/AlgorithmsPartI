package W4;

import common.MyUtils;
import org.omg.CORBA.INTERNAL;

/**
 * Created by maurizio.dapri on 2/27/2015.
 * Binary Search Tree.
 * Invariant. The key at a node is bigger than the keys at the left subtree and smaller than the ones at the right
 */
public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            left=null;
            right=null;
        }

        public String toString(){
            return String.format("%s / %s",key,val);
        }
    }

    public int size()
    { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        return x.count;
    }

    //    put key-value pair into the table
//            (remove key from table if value is null)
    public void put(Key key, Value val)
    {
        root=put(root,key,val);
    }

    private Node put(Node x ,Key key, Value val) {
        Node n = new Node(key, val);
        //eof: node added
        if (x == null) return n;
        //continue to explore
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            x.right = put(x.right, key, val);
        else if (cmp < 0)
            x.left = put(x.left, key, val);
        if (cmp == 0) {
            x.val = val;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key)
    {
        Node x=get(root,key);
        if (x != null)
            return x.val;
        else

            return null;
    }

    private Node get(Node x,Key key){

        while(x!=null) {
            int cmp = key.compareTo(x.key);
            if (cmp >0)
                x = x.right;
            else if (cmp <0)
                x = x.left;
            if (cmp == 0) {
                return x ;
            }
        }
        return null;
    }

    //not lazy. The lazy implementation requires a restructuring of the tree
    // this will be the Hibbard deletion
    public void delete(Key key)
    {
        //node to eliminate
        root=delete(root,key);
    }

    // deletes the key starting the search from the node x
    // and returns the value to be put as the parent new descendnt (new root of the subtree)
    private Node delete(Node x, Key key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp>0) x.right=delete(x.right,key);
        else if (cmp<0) x.left=delete(x.left,key);
        else { //node found
            //delete node has one child
            if (x.left == null)
                x = x.right;
            else if (x.right == null)
                x = x.left;
                //deleted node has 2 children
            else {
                Node t = x;
                //get the successor
                x=min(t.right);
                //adjust the successor and subtree of the new root
                x.right=deleteMin(t.right);
                x.left=t.left;
            }
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node deleteMin(Node x) {
        if(x== null) return null;
        if(x.left!=null)
            x.left= deleteMin(x.left);
        else
            return x.right;
        x.count = 1 + size(x.left) + size(x.right);
        return x;

    }


    public Iterable<Key> iterator()
    { /* see next slides */ return null;}

    //max, min, rank operation

    public Key max(){
        Node x=root;
        while (x.right!=null) {
            x = x.right;
        }
        return x.key;
    }


    public Key min(){
        Node x=min(root);
        return x.key;
    }

    //starting from the node find the minimum
    private Node min(Node x){

        while (x.left!=null) {
            x = x.left;
        }
        return x;
    }


    //returns the largest key &lte; a given key
    public Key floor(Key key) {

        Node x = floor(root, key);
        if(x!=null){
            return x.key;
        }
        else return null;
    }

    private Node floor(Node x,Key key) {
        //if the node is null there is no bigger key that is  less that the given key, so return
        //MyUtils.Trace(MyUtils.toStringSafe(x));
        if(x==null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        //assert x !=null;
        else if (cmp <0) {
            // MyUtils.Trace("left");
            x = floor(x.left, key);
//            Node n;
//            if (n != null)
//                x=n;
//            else
//                return null;
        }
        else if (cmp >0) {
            Node n;
            //MyUtils.Trace("right");
            n = floor(x.right, key);
            if ( n!= null)
                x=n;
            else
                ;//do not update x, nothing found
            //return x;
        }
        return x;
    }

    ///////////////////////////////////////////////////////
    public static void main(String[] args){
        //test_isEmpty();
        // test_Contains();
        test_min();
        test_getPut();
        test_Floor();
    }

    private static void  test_getPut(){
        System.out.println("*test_getPut");
        BST<String,Integer> l = new BST<String, Integer>();

        Integer expected=null;
        Integer actual=null;
        String key= null;

        l= new BST<String, Integer>();
        expected=null;
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test01");

        key="a";
        l= new BST<String, Integer>();
        expected=null;
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test02");

        key="a";
        l= new BST<String, Integer>();
        expected=1;
        l.put(key, expected);
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test03");


        key="b";
        l= new BST<String, Integer>();
        expected=2;
        l.put(key, expected);
        actual=l.get(key);
        MyUtils.AssertEqual(actual, expected, "!Test04");


    }

    private static void  test_Floor(){
        System.out.println("*test_isFloor");
        BST<String,Integer> l = new BST<String, Integer>();

        String expected=null;
        String actual=null;
        String key= null;

        l= new BST<String, Integer>();
        expected=null;
        actual=l.floor(key);
        MyUtils.AssertEqual(actual, expected, "!Test01");

        key="a";
        l= new BST<String, Integer>();
        expected=null;
        actual=l.floor(key);
        MyUtils.AssertEqual(actual, expected, "!Test02");

        key="a";
        l= new BST<String, Integer>();
        expected="a";
        l.put(key, 1);
        actual=l.floor(key);;
        MyUtils.AssertEqual(actual, expected, "!Test03");


        key="c";
        //l= new BST<String, Integer>();
        l.put(key, 1);
        expected="a";
        actual = l.floor("b");;
        MyUtils.AssertEqual(actual, expected, "!Test04");


    }

    private static void  test_min() {
        System.out.println("*test_min");
        String key, expected,actual;
        BST<String, Integer> l;
        key="a";
        l= new BST<String, Integer>();
        expected="a";
        l.put(key, 1);
        actual=l.min();;
        MyUtils.AssertEqual(actual, expected, "!Test01");


        key="c";
        //l= new BST<String, Integer>();
        l.put(key, 1);
        expected="a";
        actual=l.min();
        MyUtils.AssertEqual(actual, expected, "!Test02");
    }
}
