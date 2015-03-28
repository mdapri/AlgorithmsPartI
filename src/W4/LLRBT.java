package W4;

/**
 * Left Leaning Red Black Tree.
 * A data structure realizing the 2-3 tree
 * Similar to tree but lot of operations changes
 */
public class LLRBT<Key extends Comparable<Key>, Val> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
enum COLOR {
    RED_RIGHT,
    RED_Left_Left, //2 red in a row
    RED_left_Right  //2 red in a row,
} ;
    private Node root;
    class Node{
        private Key key;
        private Val val;
        private Node left,right;
        // the color of the parent link
        private boolean color;

        public Node(Key key, Val val) {
            this.key = key;
            this.val = val;
            left=null;
            right=null;
            color=RED;
        }
    }

    //
    // Search is the same as for elementary BST
    //
    public Val get(Key key)
    {
        Node x = root;
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0)return x.val;
        }
        return null;
    }
   
    public void put(Key key, Val val){

        root=put(root,key,val);;
    }

    private Node put(Node x, Key key, Val val){
        if(x==null) return new Node(key,val);
        int cmp=key.compareTo(x.key);
        if(cmp>0)  {
            x.right=put(x.right,key,val);// regularization needed
         }
        if(cmp<0)  {
            x.left=put(x.left,key,val);// regularization needed
        }
        else
            x.val=val;

        //NOTE: the restructuring are successive
        //now restructure MYSELF when needed
        //RED_RIGHT: first of all rotate
        if(isRed(x.right)&& !isRed(x.left)) x=rotateLeft(x);

        //note: it is not an ELSE IF
        //Red_Left_left
        if(isRed(x.left)&& isRed(x.left.left)) x=rotateRight(x); //balance 4-node

        //note: it is not an ELSE IF
        if( isRed(x.left) && isRed(x.right)) flipColors(x); //split 4-node

        assert colorConstraintRespected(x);
        return x;
    }

    public void delete(Key key)
    {
        //node to eliminate
        root=delete(root,key);
    }

    // deletes the key starting the search from the node x
    // and returns the value to be put as the parent new descendant (new root of the subtree)
    // DO NOT USE HIBBARD DELETION
    private Node delete(Node x, Key key){
        return null;
    }

    ///
    // Restructuring methods
    ///

    //the node has a right child red
    private Node rotateLeft(Node x){
        assert isRed(x.right);
        Node t = x.right;
        x.right=t.left;
        t.color=x.color;
        x.color=RED;
        t.left=x;
        return t;
    }

    private Node  rotateRight(Node h){
        assert isRed(h.left);
        Node x = h.left;
        h.left=x.right;
        x.right=h;
        x.color=h.color;
        h.color=RED;

        return x;
    }
    private void  flipColors(Node h){
          assert !isRed(h);
          assert isRed(h.left);
          assert isRed(h.right);
          h.color = RED;
          h.left.color = BLACK;
          h.right.color = BLACK;
    }

    private boolean colorConstraintRespected(Node x){
        boolean respected=true;
        if(  isRed(x.right) ) respected=false;
        else if (isRed( x.left)&& isRed(x)) respected=false;
        return respected;
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }



}
