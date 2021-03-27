package com.gbwxy.others.HW;

import java.util.*;

public class HwTest1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        LinkedHashMap<Integer, List<String>> map = new LinkedHashMap();

        int kk = 0;
        while (scanner.hasNext()) {
            String str = scanner.next();
            char[] subStr = str.toCharArray();
            Arrays.sort(subStr);
            str = new String(subStr);
            //坑1:空字符串不处理
            if (isStringBlank(str)) {
                map.put(kk, null);
                continue;
            }
            int length = str.length();
            int div = length / 8;
            if (div == 0) {
                List<String> strs = new ArrayList<>();
                strs.add(get8Char(str));
                map.put(kk, strs);
            } else {
                List<String> strs = new ArrayList<>();
                int r = length % 8;
                for (int ii = 0; ii < div; ii++) {
                    //坑2:substring(begin,end)--begin包含，end不包含  [begin,end)
                    strs.add(str.substring(8 * ii, 8 * (ii + 1)));
                }
                if (r > 0) {
                    //马虎：这里sub是从div*8开始的，不是length-div*8开始
                    strs.add(get8Char(str.substring(div * 8)));
                }
                map.put(kk, strs);
            }
            kk++;

        }
        scanner.close();

        for (int ii = num - 1; ii >= 0; ii--) {
            List<String> strs = map.get(ii);
            if (map != null) {
                for (int jj = 0; jj < strs.size(); jj++) {
                    System.out.print(strs.get(jj));
                    if (jj < strs.size() - 1) {
                        System.out.print(" ");
                    }
                }
                if (ii > 0 && !isNull(map, ii)) {
                    System.out.print(" ");
                }
            }
        }


    }

    private static boolean isNull(Map map, int kk) {
        for (int jj = kk; jj >= 0; jj--) {
            if (map.get(jj) != null) {
                return false;
            }
        }
        return true;
    }

    private static String get8Char(String str) {
        int length = str.length();
        for (int ii = length; ii < 8; ii++) {
            str += "0";
        }
        return str;
    }

    private static boolean isStringBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
