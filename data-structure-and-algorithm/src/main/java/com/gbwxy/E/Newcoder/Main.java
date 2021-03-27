package com.gbwxy.E.Newcoder;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


//        int[] nums = new int[]{2, 1};
//        int candy = Candy.candy(nums);
//        System.out.println(candy);


//        dict =["cat", "cats", "and", "sand", "dog"]
        String s = "catsanddog";
        Set<String> dict = new HashSet<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        System.out.println(DynameicProgram2.wordBreak(s, dict));

        /**
         *  word break
         */
        //"aaaaaaa",["aaaa","aaa"]
//        String s = "aaaaaaa";
//        Set<String> dict = new HashSet<>();
//        dict.add("aaaa");
//        dict.add("aaa");
//        String s = "leetcode";
//        Set<String> dict = new HashSet<>();
//        dict.add("leet");
//        dict.add("code");
//        System.out.println(DynamicProgram.wordBreak(s, dict));


        /**
         * 计算逆波兰式（后缀表达式）的值
         */
//        //["0","3","/"]
//        String[] tokens = new String[]{"0", "3", "/"};
//        StackOpt.evalRPN(tokens);


        //  4,3,[1,2,3,4],[2,2,4]
        //System.out.println(Water.solve(4, 3, new int[]{1, 2, 3, 4}, new int[]{2, 2, 4}));

        /**
         * 单链表排序
         */
//        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.appent(8);
//        linkedList.appent(2);
//        linkedList.appent(5);
//        linkedList.appent(81);
//        linkedList.appent(18);
//        linkedList.appent(52);
//        linkedList.appent(33);
//        linkedList.appent(24);
//        linkedList.appent(66);
//        ListNode resNode = SingleListNodeSort.sortList(linkedList.getHead());
//
//        while (resNode != null) {
//            System.out.println(resNode.getVal());
//            resNode = resNode.getNext();
//        }


    }

/**
 * stack 测试
 */
//    public static void main(String[] args) {
//        String tmp;
//        ArrayStack<String> astack = new ArrayStack<String>(String.class);
//
//        // 将10, 20, 30 依次推入栈中
//        astack.push("10");
//        astack.push("20");
//        astack.push("30");
//
//        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
//        tmp = astack.pop();
//        System.out.println("tmp=" + tmp);
//
//        // 只将“栈顶”赋值给tmp，不删除该元素.
//        tmp = astack.peek();
//        System.out.println("tmp=" + tmp);
//
//        astack.push("40");
//        astack.PrintArrayStack();    // 打印栈
//    }


/**
 * queue 测试
 */
//public static void main(String[] args) {
//    int tmp = 0;
//    ArrayQueue astack = new ArrayQueue(12);
//
//    // 将10, 20, 30 依次推入栈中
//    astack.add(10);
//    astack.add(20);
//    astack.add(30);
//
//    // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
//    tmp = astack.pop();
//    System.out.printf("tmp=%d\n", tmp);
//
//    // 只将“栈顶”赋值给tmp，不删除该元素.
//    tmp = astack.front();
//    System.out.printf("tmp=%d\n", tmp);
//
//    astack.add(40);
//
//    System.out.printf("isEmpty()=%b\n", astack.isEmpty());
//    System.out.printf("size()=%d\n", astack.size());
//    while (!astack.isEmpty()) {
//        System.out.printf("size()=%d\n", astack.pop());
//    }
//}
}
