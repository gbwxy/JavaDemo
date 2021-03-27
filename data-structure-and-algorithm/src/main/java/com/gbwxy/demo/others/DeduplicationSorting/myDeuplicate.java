package com.gbwxy.demo.others.DeduplicationSorting;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/26
 */
public class myDeuplicate {

    /**
     * 用LinkedHashSet
     *
     * @param arr
     * @return
     */
    public static Object[] removeDupArray(Object[] arr) {
        LinkedHashSet<Object> temp = new LinkedHashSet<>();
        for (int i = 0; i < arr.length; i++) {
            temp.add(arr[i]);
        }
        return temp.toArray();
    }

    /**
     * 用list的contains
     *
     * @param list
     * @return
     */
    public static List removeDupList(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 用stream
     *
     * @param list
     * @return
     */
    public static List removeDupList2(List list) {
        return (List) list.stream().distinct().collect(Collectors.toList());
    }
}
