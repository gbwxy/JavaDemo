package com.gbwxy.E.Newcoder.entity;

// 双向链表“节点”对应的结构体
public class DNode<T> {
    public DNode prev;
    public DNode next;
    public T value;

    public DNode(T value, DNode prev, DNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}