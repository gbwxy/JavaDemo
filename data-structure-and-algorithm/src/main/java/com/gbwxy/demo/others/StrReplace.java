package com.gbwxy.demo.others;

import java.util.Scanner;

public class StrReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            long xcount = 0;
            long num = 0;
            for (int ii = 0; ii < str.length(); ii++) {
                if (str.charAt(ii) == 'x') {
                    xcount++;
                } else {
                    long currNum = 1;
                    for (int jj = 0; jj < xcount; jj++) {
                        currNum = currNum * 2;
                    }
                    num = num + currNum - 1;
                }
            }

            System.out.println(num);

        }

        scanner.close();
    }
}
