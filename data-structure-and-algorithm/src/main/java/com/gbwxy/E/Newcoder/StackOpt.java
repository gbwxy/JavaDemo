package com.gbwxy.E.Newcoder;

//题目描述
//        计算逆波兰式（后缀表达式）的值
//        运算符仅包含"+","-","*"和"/"，被操作数可能是整数或其他表达式
// 例如：
//        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9↵  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
//        Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//        Valid operators are+,-,*,/. Each operand may be an integer or another expression.
//
//        Some examples:
//
//        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9↵  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
// 示例1
//     输入
//        ["2","1","+","3","*"]
//     输出
//        9

import java.util.ArrayDeque;

public class StackOpt {
    /**
     * @param tokens string字符串一维数组
     * @return int整型
     */
    public static int evalRPN(String[] tokens) {
        // write code here
        /**
         * 方法一：自己实现栈
         */
//        int[] nums = new int[tokens.length];
//        int count = -1;
//        for (String str : tokens) {
//            switch (str.trim()) {
//                case "+":
//                    int num1 = nums[count];
//                    nums[count] = 0;
//                    count--;
//                    int num2 = nums[count];
//                    nums[count] = num2 + num1;
//                    break;
//                case "-":
//                    num1 = nums[count];
//                    nums[count] = 0;
//                    count--;
//                    num2 = nums[count];
//                    nums[count] = num2 - num1;
//                    break;
//                case "*":
//                    num1 = nums[count];
//                    nums[count] = 0;
//                    count--;
//                    num2 = nums[count];
//                    nums[count] = num2 * num1;
//                    break;
//                case "/":
//                    num1 = nums[count];
//                    nums[count] = 0;
//                    count--;
//                    num2 = nums[count];
//                    nums[count] = num2 / num1;
//                    break;
//                default:
//                    count++;
//                    nums[count] = Integer.valueOf(str);
//            }
//        }
//        return nums[0];


        /**
         * 自带的dequeue
         */
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (String a: tokens){
            if (a.compareTo("+") == 0){
                int x2 = deque.pop(), x1 = deque.pop();
                deque.push(x1+x2);
            } else if (a.compareTo("-") == 0){
                int x2 = deque.pop(), x1 = deque.pop();
                deque.push(x1-x2);
            } else if (a.compareTo("*") == 0){
                int x2 = deque.pop(), x1 = deque.pop();
                deque.push(x1*x2);
            }else if (a.compareTo("/") == 0){
                int x2 = deque.pop(), x1 = deque.pop();
                deque.push(x1/x2);
            } else {
                deque.push(Integer.valueOf(a));
            }
        }
        return deque.pop();
    }

}
