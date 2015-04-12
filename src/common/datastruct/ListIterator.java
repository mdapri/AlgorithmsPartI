package common.datastruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by maurizio.dapri on 4/11/2015.
 */
// an iterator, doesn't implement remove() since it's optional
class ListIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> starting){
        current=starting;
    }
    public boolean hasNext() {
        return current != null;
    }
    @Override
    /**
     * return the current item and advances the pointer
     * NOTE: hasNext has to be called before
     */
    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current=current.next;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
