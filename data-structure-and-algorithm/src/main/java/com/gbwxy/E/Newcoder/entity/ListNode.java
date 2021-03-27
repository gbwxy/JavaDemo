package com.gbwxy.E.Newcoder.entity;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/29
 */
public class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode() {
        this.val = 0;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

