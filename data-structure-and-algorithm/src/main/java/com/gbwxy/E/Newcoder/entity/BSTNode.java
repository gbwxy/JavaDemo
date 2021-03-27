package com.gbwxy.E.Newcoder.entity;

public class BSTNode<T extends Comparable<T>> {
    T key;                // 关键字(键值)
    BSTNode<T> left;    // 左孩子
    BSTNode<T> right;    // 右孩子
    BSTNode<T> parent;    // 父结点

    public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public BSTNode<T> getParent() {
        return parent;
    }

    public void setParent(BSTNode<T> parent) {
        this.parent = parent;
    }

    public String toString() {
        return "key:" + key;
    }
}