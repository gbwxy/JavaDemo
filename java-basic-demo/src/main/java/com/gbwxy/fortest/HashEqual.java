package com.gbwxy.fortest;

import java.util.*;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/8/20
 */

public class HashEqual {

    private String fullName;
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashEqual)) return false;

        HashEqual hashEqual = (HashEqual) o;

        if (count != hashEqual.count) return false;
        return fullName.equals(hashEqual.fullName);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + count;
        return result;
    }


    public void Func() {

        List abc = new ArrayList();
        abc = Collections.synchronizedList(abc);

        Map mapT = Collections.synchronizedMap(new HashMap<>());

    }
}
