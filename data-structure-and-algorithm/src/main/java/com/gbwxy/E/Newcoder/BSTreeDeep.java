package com.gbwxy.E.Newcoder;


import com.gbwxy.E.Newcoder.entity.BSTNode;

public class BSTreeDeep {

    /**
     * @param root BSTNode类
     * @return int整型
     */
    /**
     * 思路1：
     * 深度优先遍历（DFS）
     * 如果当前节点是空，则最小深度为 0，返回
     * 效率低
     */
    public int run(BSTNode root) {
        if (root == null) {
            return 0;
        }
        int l = run(root.getLeft());
        int r = run(root.getRight());
        if (l == 0 || r == 0) {
            return l + r + 1;
        }
        return Math.min(l, r) + 1;
    }




}
