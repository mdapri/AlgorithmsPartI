package common;

import common.datastruct.Queue;

/**
 * Created by maurizio.dapri on 2/21/2015.
 */
public class MyUtilsTest {

    public static void main(String[] args) {
        System.out.println("//START");
//        test_allBigger();
//        test_allSmaller();
        test_AssertListEqual();
        System.out.println("//END");
    }


    private static void test_allBigger(){
        System.out.println("*test_allBigger");
        Integer[] vector = new Integer[] {3,5,7,8};
        int start = 0;
        int end=vector.length-1;
        int key=2;
        boolean orEqual=false;
        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.allBigger(key,vector,start,end,orEqual), "Test1: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,2};
        start = 1;
        end=2;
        key=3;
        orEqual=false;
        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.allBigger(key,vector,start,end,orEqual), "Test2: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,2};
        start = 0;
        end=2;
        key=3;
        orEqual=false;
        //all bigger shoudl be false
        MyUtils.Assert(! MyUtils.allBigger(key,vector,start,end,orEqual), "Test3: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,2};
        start = 0;
        end=2;
        key=3;
        orEqual=true;
        //all bigger shoudl now be true, because equal is admitted
        MyUtils.Assert(MyUtils.allBigger(key,vector,start,end,orEqual), "Test4: "+ key +", " + start +", "+ end +", "+ orEqual    );



    }

    private static void test_allSmaller(){
        System.out.println("*test_allSmaller");
        Integer[] vector = new Integer[] {3,5,7,9};
        int start = 0;
        int end=vector.length-1;
        int key=10;
        boolean orEqual=false;
        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.allSmaller(key, vector, start, end, orEqual), "Test1: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,9};
        start = 1;
        end=2;
        key=8;
        orEqual=false;
        //MyUtils.Dump(vector);
        MyUtils.Assert(MyUtils.allSmaller(key, vector, start, end, orEqual), "Test2: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,9};
        start = 0;
        end=2;
        key=7;
        orEqual=false;
        //all bigger should be false
        MyUtils.Assert(! MyUtils.allSmaller(key, vector, start, end, orEqual), "Test3: "+ key +", " + start +", "+ end +", "+ orEqual    );

        vector = new Integer[] {3,5,7,9};
        start = 0;
        end=2;
        key=7;
        orEqual=true;
        //all bigger shoudl now be true, because equal is admitted
        MyUtils.Assert(MyUtils.allSmaller(key,vector,start,end,orEqual), "Test4: "+ key +", " + start +", "+ end +", "+ orEqual    );
    }

    private static void test_AssertListEqual(){
        System.out.println("*test_AssertListEqual");
        Queue<Integer> expected = new Queue<Integer>();
        Queue<Integer> actual = new Queue<Integer>();
        expected.enqueue(1);
        expected.enqueue(2);
        expected.enqueue(3);
        expected.enqueue(4);

        actual.enqueue(1);
        actual.enqueue(2);
        actual.enqueue(3);
        actual.enqueue(4);

        MyUtils.AssertListEqual( actual,  expected,  "!Test_01");

        //different element, same length
        actual = new Queue<Integer>();
        actual.enqueue(1);
        actual.enqueue(3);
        actual.enqueue(2);
        actual.enqueue(4);
        MyUtils.AssertListEqual( actual,  expected,  "!Test_02");

        //same element, different length
        actual = new Queue<Integer>();
        actual.enqueue(1);
        actual.enqueue(2);
        actual.enqueue(3);

        MyUtils.AssertListEqual( actual,  expected,  "!Test_03");

        //same element, different length
        actual = new Queue<Integer>();
        actual.enqueue(1);
        actual.enqueue(2);
        actual.enqueue(3);
        actual.enqueue(4);
        actual.enqueue(5);

        MyUtils.AssertListEqual( actual,  expected,  "!Test_04");

        //different element, different length
        actual = new Queue<Integer>();
        actual.enqueue(1);
        actual.enqueue(2);
        actual.enqueue(4);
        actual.enqueue(3);
        actual.enqueue(5);

        MyUtils.AssertListEqual( actual,  expected,  "!Test_05");


    }

}
