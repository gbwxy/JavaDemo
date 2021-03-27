package com.gbwxy.demo.others.ReSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static char[] wordChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static String wordStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            List<String> word = new ArrayList<>();
            //坑1 scanner.next 空格，换行都可以作为next，scanner.nextLine() 读取一行
            String str = scanner.nextLine();
            int idx = 0;
            int count = 0;
            String wd = new String("");
            for (char c : str.toCharArray()) {
                count++;
                if (!wordStr.contains(String.valueOf(c))) {
                    if (wd.length() > 0) {
                        word.add(wd);
                        wd = new String("");
                        idx = 0;
                    }
                    continue;
                }
                if (idx > 20) {
                    word.add(wd);
                    wd = new String("");
                    idx = 0;
                    continue;
                }
                wd += c;
                idx++;

                //需要处理最后一个单词
                if (count == str.length()) {
                    word.add(wd);
                }
            }

            for (int ii = word.size() - 1; ii >= 0; ii--) {
                System.out.print(word.get(ii));
                if (ii > 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
