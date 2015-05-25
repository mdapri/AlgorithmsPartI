package common.datastruct;

import common.MyUtils;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * Stack Based on Array representation
 */
public class StackArray<Item> implements IStack<Item>{

    Item[] items;
    int count = 0;

    public  StackArray(int capacity){
        items = (Item[]) new Object[capacity];
    }
    @Override
    public Item pop() {
        resizeIfNeeded();
        Item poppedItem= items[--count];
        return poppedItem;
    }

    @Override
    public void push(Item s) {
        resizeIfNeeded();
        items[count++] = s;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public int Count(){
        return count;
    }

    /**
     * check if the array is too small or too big and changes the size
     */
    private void resizeIfNeeded(){
        //double when the count equals the size
        int newSize = 0;
        if (count==items.length)
        {
            newSize = 2*items.length;
            resizeItemArray( newSize, count);
        }
        //halve when the count is 1/4th of size
        if (count > 0 && count==items.length/4)
        {
            newSize = items.length/2;
            resizeItemArray( newSize, count);
        }
    }

    private void resizeItemArray(int newSize, int copyCount) {
        Item [] newArray =(Item[]) new Object[newSize];
        copyTo(items,newArray, copyCount );
        items=newArray;
    }

    private void copyTo(Item[] source , Item[] target, int copyCount ){
        assert copyCount<=target.length;

        for (int itemIdx = 0; itemIdx <copyCount ; itemIdx++) {
            target[itemIdx] = source[itemIdx];
        }
    }
    private int ItemsLength(){
        return items.length;
    }

    //////////////////  TEST ////////////////////////////////
    public static void main(String[] args) {
        System.out.println("//START");
        test_WhenEmpty_ThenCountIs0();
        test_WhenPushOnEmpty_ThenCountIs1();
        test_WhenPushandPopOnEmpty_ThenCountIs0();
        test_WhenCountReachesCapacity_ThenItemArrayDoubles();
        test_WhenCountReachesOneFourthCapacity_ThenItemArrayHalves();

        System.out.println("//END");
    }

    private static void test_WhenEmpty_ThenCountIs0(){

        System.out.println("{{"+"test_WhenEmpty_ThenCountIs0");
        StackArray<Integer> s=  new StackArray<Integer>(2);
        MyUtils.AssertEqual(s.Count(), 0, "Error1");
        System.out.println("}}"+"test_WhenEmpty_ThenCountIs0");
    }

    private static void test_WhenPushOnEmpty_ThenCountIs1(){
        System.out.println("{{"+"test_WhenPushOnEmpty_ThenCountIs1");
        StackArray<Integer> s=  new StackArray<Integer>(2);
        s.push( 10 );
        MyUtils.AssertEqual(s.Count(),1,"Error1");
        System.out.println("}}"+"test_WhenPushOnEmpty_ThenCountIs1");
    }

    private static void test_WhenPushandPopOnEmpty_ThenCountIs0(){
        System.out.println("{{"+"test_WhenPushandPopOnEmpty_ThenCountIs0");
        StackArray<Integer> s=  new StackArray<Integer>(2);
        s.push( 10 );
        Integer actual= s.pop();
        MyUtils.AssertEqual(s.Count(),0,"Error1");
        MyUtils.AssertEqual(actual,10,"Error2");
        System.out.println("}}"+"test_WhenPushandPopOnEmpty_ThenCountIs0");
    }

    private static void test_WhenCountReachesCapacity_ThenItemArrayDoubles(){
        System.out.println("{{"+"test_WhenCountReachesCapacity_ThenItemArrayDoubles");
        StackArray<Integer> s=  new StackArray<Integer>(1);
        s.push( 10 );
        s.push( 11 );
        MyUtils.AssertEqual(s.ItemsLength(),2,"Error1");
        s.push( 12 );
        MyUtils.AssertEqual(s.ItemsLength(),4,"Error2");
        System.out.println("}}"+"test_WhenCountReachesCapacity_ThenItemArrayDoubles");
    }


    private static void test_WhenCountReachesOneFourthCapacity_ThenItemArrayHalves(){
        System.out.println("{{"+"test_WhenCountReachesOneFourthCapacity_ThenItemArrayHalves");
        StackArray<Integer> s=  new StackArray<Integer>(2);
        s.push( 10 );
        s.push( 11 );
        MyUtils.AssertEqual(s.ItemsLength(),2,"Error1");
        s.push( 12 );
        MyUtils.AssertEqual(s.ItemsLength(),4,"Error2");
        s.pop();
        MyUtils.AssertEqual(s.ItemsLength(),4,"Error3");
        s.pop();
        s.pop();
        MyUtils.AssertEqual(s.ItemsLength(),2,"Error4");
        System.out.println("}}"+"test_WhenCountReachesOneFourthCapacity_ThenItemArrayHalves");
    }
}
