package W4;


import common.MyUtils;

/**
 * Created by maurizio.dapri on 6/1/2015.
 */
public class STUnordered<Key extends Comparable<Key>,Value> implements ISymbolTable<Key,Value>  {

    class Node<Key,Value> {
        Key key;
        Value value;
        Node<Key,Value> next;

        Node(Key key, Value val) {
            this.key = key;
            this.value = val;
            this.next = null;
        }
    }

   Node<Key,Value>  first;

    STUnordered() {
        first= null;
        ;}

    /**
     * put key-value pair into the table(remove key from table if VALUE is null) LAZY VERSION OF DELETE
     * @param key
     * @param val
     */

   /* public void putOLd(Key key, Value val) {
        if (first==null) {
            first=new Node<Key, Value>(key,val);
            return;
        }
        Node<Key, Value> current=first;
        while (current != null) {
            if (current.key == key) {
                current.value = val;
                break;
            }
            if(current.next ==null) {
                current.next=new Node<Key, Value>(key, val);
                break;
            }
            current = current.next;
        }

    }*/

    @Override
    public void put(Key key, Value val) {

        Node<Key, Value> found=find(key);
        if(found==null){
            //add at the beginning
            found=new Node<Key, Value>(key,val);
            found.next=first;
            first=found;
        }
        else
            found.value=val;

    }

    private Node<Key,Value> find(Key key){
        Node<Key, Value> current=first;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.next;
        }
        return current;
    }


    /**
     * // value paired with key(null if key is absent, that means that also deleted putting null to the value)
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        Node<Key, Value> found=find(key);
        if(found!= null && found.key!=null)
            return found.value;
        else
            return null;
    }

    /**
     * remove key(and its value) from table. Lazy version
     * @param key
     */
    public void delete(Key key) {
        put(key,null);
    }

    public boolean contains(Key key) {return get(key)==null;}

    public boolean isEmpty() 	{return first==null;}

    /**
     * number of key-value pairs in the table
     * @return
     */
    public  int size() 	{return 0;}
    /**
     * all the keys in the table
     * @return
     */
    public Iterable<Key> keys() {return null;}

    public static void main(String[] args) {

        System.out.println("START");
        Put_WhenEmpty_ThenSetsFirst();
        Put_WhenEmpty_ThenPut2DifferentNodes();
        Put_When2EquakKeys_ThenOverWrite();
        Put_WhenDelete_ThenGetReturnsNull();
        System.out.println("END");

    }

    public static void Put_WhenEmpty_ThenSetsFirst(){
        System.out.println(">> "+"Put_WhenEmpty_ThenSetsFirst");
        STUnordered<String, Integer> st= new  STUnordered<String, Integer>();
        MyUtils.Assert(st.first == null, "Error1");
        st.put("a", 0);
        MyUtils.Assert(st.first != null, "Error2");
        MyUtils.Assert(st.first.key == "a", "Error3");
        MyUtils.Assert(st.first.value==0,"Error4");

        System.out.println("<< " + "Put_WhenEmpty_ThenSetsFirst");

    }
    public static void Put_WhenEmpty_ThenPut2DifferentNodes(){
        System.out.println(">> "+"Put_WhenEmpty_ThenPut2DifferentNodes");
        STUnordered<String, Integer> st= new  STUnordered<String, Integer>();
        MyUtils.Assert(st.first == null, "Error1");
        st.put("a", 0);
        st.put("b", 1);
        MyUtils.Assert(st.first != null, "Error2");
        MyUtils.Assert(st.first.key == "b", "Error3");
        MyUtils.Assert(st.first.value==1,"Error4");
        MyUtils.Assert(st.first.next.key == "a", "Error5");
        MyUtils.Assert(st.first.next.value==0,"Error6");

        System.out.println("<< " + "Put_WhenEmpty_ThenPut2DifferentNodes");

    }
    public static void Put_When2EquakKeys_ThenOverWrite(){
        System.out.println(">> "+"Put_When2EquakKeys_ThenOverWrite");
        STUnordered<String, Integer> st= new  STUnordered<String, Integer>();
        MyUtils.Assert(st.first == null, "Error1");
        st.put("a", 0);
        st.put("b", 1);
        st.put("a", 2);
        MyUtils.Assert(st.first != null, "Error2");
        MyUtils.Assert(st.first.key == "b", "Error3");
        MyUtils.Assert(st.first.value==1,"Error4");
        MyUtils.Assert(st.first.next.key == "a", "Error5");
        MyUtils.Assert(st.first.next.value==2,"Error6");

        System.out.println("<< " + "Put_When2EquakKeys_ThenOverWrite");

    }

    public static void Put_WhenDelete_ThenGetReturnsNull(){
        System.out.println(">> "+"Put_WhenDelete_ThenGetReturnsNull");
        STUnordered<String, Integer> st= new  STUnordered<String, Integer>();
        MyUtils.Assert(st.first == null, "Error1");
        st.put("a", 0);
        st.put("b", 1);
        st.delete("a");
        MyUtils.Assert(st.first != null, "Error2");
        MyUtils.Assert(st.first.key == "b", "Error3");
        MyUtils.Assert(st.first.value==1,"Error4");
        MyUtils.Assert(st.first.next.key == "a", "Error5");
        MyUtils.Assert(st.first.next.value==null,"Error6");
        Integer val = st.get("a");
        MyUtils.Assert(val==null,"Error7");
        val = st.get("b");
        MyUtils.Assert(val==1,"Error8");

        System.out.println("<< " + "Put_WhenDelete_ThenGetReturnsNull");

    }
}
