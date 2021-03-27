package com.gbwxy.demo.others.ConsolidatedTable;

//题目描述
//        数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
//
//输入描述:
//        先输入键值对的个数
//        然后输入成对的index和value值，以空格隔开
//
// 输出描述:
//        输出合并后的键值对（多行）
//
// 示例1
//      输入
//        4
//        0 1
//        0 2
//        1 2
//        3 4
//     输出
//        0 3
//        1 2
//        3 4

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Map<Integer, Integer> map = new HashMap<>();
            int num = scanner.nextInt();
            TreeSet<Integer> keys = new TreeSet();
            for (int ii = 0; ii < num; ii++) {
                int key = scanner.nextInt();
                int value = scanner.nextInt();
                if (map.keySet().contains(key)) {
                    value = map.get(key) + value;
                    map.remove(key);
                    map.put(key, value);
                } else {
                    map.put(key, value);
                }
                keys.add(key);
            }

            for (int key : keys) {
                System.out.printf("%d %d\n", key, map.get(key));
            }
        }
    }

}
