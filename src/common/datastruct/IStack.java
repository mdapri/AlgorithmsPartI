package common.datastruct;

/**
 * Created by maurizio.dapri on 5/25/2015.
 */
public interface IStack<Item> {
    Item pop();
    void push(Item s);
    boolean isEmpty();
    int Count();
}
