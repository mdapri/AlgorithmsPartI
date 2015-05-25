package common.datastruct;

/**
 * Created by maurizio.dapri on 5/25/2015.
 */
public interface IQueue<Item> {
    void enqueue(Item item);
    Item dequeue();
    boolean isEmpty() ;
    int size();
}
