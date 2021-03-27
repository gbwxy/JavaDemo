package com.gbwxy.others;

// Java默认模板

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/6/11
 */
public class PseudoRegular {

    /**
     * 场景考虑不全面
     */
//    private static final String Letter = "1234567890";
//    private static final String Letterm = "1234567890*";
//    public static void main(String[] args) {
//        // System.arraycopy(original, 0, destination, length);
//        // Arrays.copyOf(original, newLength);
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int n = Integer.valueOf(in.nextLine());
//            for (int ii = 0; ii < n; ii++) {
//                String m = in.nextLine();
//                String s = in.nextLine();
//                if (s == null || s.length() == 0) {
//                    System.out.println("NO");
//                } else {
//                    boolean isMatch = true;
//                    int sum = 0;
//                    boolean isStart = false;
//                    int mIdx = 0;
//                    boolean mMatch = false;
//                    for (int jj = 0; jj < s.length(); jj++) {
//                        char sc = s.charAt(jj);
//                        if (Letter.indexOf(sc) == -1) {
//                            isMatch = false;
//                            break;
//                        }
//                        if (mIdx >= m.length()) {
//                            isMatch = false;
//                            break;
//                        }
//                        char mc = m.charAt(mIdx);
//                        if (Letterm.indexOf(mc) == -1) {
//                            isMatch = false;
//                            break;
//                        }
//                        if (!isStart && mc == '*') {
//                            mIdx++;
//                            mMatch = false;
//                            continue;
//                        } else {
//                            if (mc != '*') {
//                                if (!isStart) {
//                                    isStart = true;
//                                }
//                                if (mc != sc) {
//                                    isMatch = false;
//                                    break;
//                                }
//                                sum += Integer.valueOf(String.valueOf(mc));
//                                mIdx++;
//                                mMatch = false;
//                            } else {
//                                int mI = sum % 10;
//                                //场景要考虑全面-多写几个测试用例
//                                if (mI != Integer.valueOf(String.valueOf(sc))) {
//                                    if (mMatch && mIdx < m.length() - 1) {
//                                        mIdx++;
//                                        mMatch = false;
//                                        mc = m.charAt(mIdx);
//                                        if (mc != sc) {
//                                            isMatch = false;
//                                            break;
//                                        } else {
//                                            mIdx++;
//                                            continue;
//                                        }
//                                    } else {
//                                        isMatch = false;
//                                        break;
//                                    }
//                                } else {
//                                    mMatch = true;
//                                }
//                            }
//                        }
//                    }
//                    if (mIdx < m.length() - 1) {
//                        isMatch = false;
//                    }
//                    if (isMatch) {
//                        System.out.println("YES");
//                    } else {
//                        System.out.println("NO");
//                    }
//                }
//            }
//            in.nextLine();
//        }
//        in.close();
//    }


    /**
     * 遍历m
     */

    private static final String Letter = "1234567890";
    private static final String Letterm = "1234567890*";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = Integer.valueOf(in.nextLine());
            for (int ii = 0; ii < n; ii++) {
                String m = in.nextLine();
                String s = in.nextLine();

                if (s == null || s.length() == 0) {
                    System.out.println("NO");
                } else {

                    boolean isStart = false;
                    boolean isMatch = true;
                    int sum = 0;
                    int mIdx = 0;
                    int sIdx = 0;
                    while (mIdx < m.length() && sIdx < s.length()) {
                        char mc = m.charAt(mIdx);
                        if (Letterm.indexOf(mc) == -1) {
                            isMatch = false;
                            break;
                        }
                        char sc = s.charAt(sIdx);
                        if (Letter.indexOf(sc) == -1) {
                            isMatch = false;
                            break;
                        }
                        if (!isStart && mc == '*') {
                            mIdx++;
                            sIdx++;
                            continue;
                        } else {
                            if (mc != '*') {
                                isStart = true;
                                if (sc == mc) {
                                    sum += Integer.valueOf(mc);
                                    mIdx++;
                                    sIdx++;
                                    continue;
                                } else {
                                    isMatch = false;
                                    break;
                                }
                            } else {
                                isMatch = isMatch(mIdx, sIdx, m, s, sum % 10);
                                mIdx = m.length();
                                sIdx = s.length();
                            }
                        }
                    }

                    if (mIdx != m.length() || sIdx != s.length()) {
                        isMatch = false;
                    }

                    if (isMatch) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
            }
            in.nextLine();
        }
        in.close();
    }

    private static boolean isMatch(int mIdx, int sIdx, String m, String s, int mNum) {

        if (m.length() - mIdx > s.length() - sIdx) {
            return false;
        } else if (m.length() - 1 == mIdx) {
            while (sIdx < s.length()) {
                if (Integer.valueOf(s.charAt(sIdx)) != mNum) {
                    return false;
                }
                sIdx++;
            }
            return true;
        } else {
            int ii = s.length() - 1;
            int jj = m.length() - 1;
            while (ii >= sIdx && jj >= mIdx) {
                char mc, sc;
                mc = m.charAt(jj);
                sc = s.charAt(ii);
                if (mc == '*') {
                    if (Integer.valueOf(sc) != mNum) {
                        return false;
                    } else {
                        ii--;
                        continue;
                    }
                } else {
                    if (sc != mc) {
                        return false;
                    } else {
                        ii--;
                        jj--;
                        continue;
                    }
                }
            }
            if (ii > sIdx || jj > mIdx) {
                return false;
            }
            return true;
        }
    }

}


///**
// * 题目描述
//     * 判断一个数字串是否匹配一个表达式，具体如下：
//     * 1）表达式m：一定不为空，只能由数字和“*”构成，其中的“*”表示匹配一个或多个“它前面所有非”*“的数字的和值除以10的余数”；
//          “*”如果在最前面或其前面都是“*”，则只可以是任意一个数字。
//     * 2）数字串s：可能为空，只能由数字构成，不能包含其它字符。
//     * 3）是否匹配：匹配要求覆盖整个数字串s，而不是某一部分匹配。

// * 解题要求：
//    * 不能将m转写成标准正则表达式来解题，需要自己编程实现匹配算法。
// * 输入描述:
//    * 见输入样例，其中最后一行的0表示结束输入
// * 输出描述:
//    * YES:匹配
//    * NO:不匹配

// * 示例1
//     * 输入
//         * 3
//         * 1*
//         * 11111
//         * **1
//         * 121
//         * **1
//         * 1221
//         * 0
//     * 输出
//         * YES
//         * YES
//         * NO
// * 说明
//     * 3表示case数有3个
//     * case 1：1*是表达式，11111是数字串，*前面所有数字的和值除以10的余数为1，*表示匹配1个或1个以上的前面数字”1“，刚好“1111”符合匹配规则，所以，输出YES。
//     * case 2：**1是表达式，121是数字串，第一个*在最前面，可以是任意数字，与“1”匹配；第二个“*”因为前面都是“*”，所以可以是任意一个数字，与“2”匹配。总体看，两者符合匹配规则，输出YES。
//     * case 3：**1是表达式，1221是数字串，第一个*与“1”匹配，第二个*与“2”匹配，但只能匹配一个“2”，整体不符合匹配规则，所以，输出NO。
// * 示例2
//     * 输入
//         * 3
//         * 1*
//         * 11111
//         * 21*
//         * 2103
//         * 22*
//         * 22*
//         * 0
//     * 输出
//         * YES
//         * NO
//         * NO
// * 说明
//     * 3表示case数有3个
//     * case 1：1*是正则表达式，11111是数字串，两者符合匹配规则，所以，输出YES。
//     * case 2：21*是正则表达式，2103是数字串，*前面所有数字的和值除以10的余数为3，刚好“03”并不符合匹配规则，所以，输出NO。
//     * case 3：22*是正则表达式，22*是数字串，但是因为其中包含了*这样的非数字字符，并不符合匹配规则，所以，输出NO。
// */


//  用例3:
//        5
//        1*b
//        1*b
//        21*2
//        21332
//        21*2
//        2122
//        *
//        100
//        *00
//        100
//        0
//
//  对应输出应该为:
//
//        NO
//        YES
//        NO
//        NO
//        YES
//
