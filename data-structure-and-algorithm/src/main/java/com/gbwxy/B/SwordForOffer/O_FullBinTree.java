package com.gbwxy.B.SwordForOffer;


import com.gbwxy.A.Entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/25
 */

/**
 * 题目描述：判断一棵树是否为满二叉树
 */
public class O_FullBinTree {


    public static void main(String[] args) {
        TreeNode node = new TreeNode();
        TreeNode root = node;
        node.val = 1;
        node.left = new TreeNode();
        node.left.val = 2;
        node.right = new TreeNode();
        node.right.val = 3;

        node.left.left = new TreeNode();
        node.left.left.val = 4;
        node.left.right = new TreeNode();
        node.left.right.val = 5;
        node.right.left = new TreeNode();
        node.right.left.val = 6;
        node.right.right = new TreeNode();
        node.right.right.val = 7;


        boolean fullBinTree2 = isFullBinTree2(root);
        System.out.println(fullBinTree2);

    }

    //方法一：

    /**
     * @author chenlang
     * 判断一棵树是否是满二叉树
     * 思路一：
     * 递归解决，对于每一个结点来说， 由左子树求得的高度与由右子树求得的高度相等，
     * 当满足该条件时，当前树为满二叉树。
     * 步骤：
     * 1. 根节点由左子树求得的高度等于由右子树求得的高度。
     * 2. 根节点的左子结点由左子树求得的高度等于由右子树求得的高度，根节点的右子结点由左子树求得的高度等于由右子树求得的高度。
     * 3. 依次处理树中的每个结点。
     * <p>
     * （结点的高度：结点到叶子结点的最长路径（边数））
     */
    public static boolean isFullBinTree(TreeNode root) {

        if (root == null) {
            return false;
        }
        //结点的左右子树均为null，则当前结点是满二叉树。
        if (root.left == null && root.right == null) {
            return true;
        }
        //由根结点的左子结点求根结点的高度
        int leftHeight = getTreeNodeHeight(root.left);
        //由根结点的右子结点求根结点的高度
        int rightHeight = getTreeNodeHeight(root.right);

        if (leftHeight != rightHeight) {
            return false;
        }
        //根结点的左右子结点得进行相同的处理
        if (isFullBinTree(root.left) && isFullBinTree(root.right)) {
            return true;
        }
        return false;
    }

    /*
     * 由treeNode获取 treeNode父结点的高度，叶子结点的高度为0。
     * 某个结点treeNode的左子结点为null， 则由左子结点求得treeNode的高度为0
     * 某个结点treeNode的右子结点为null， 则由右子结点求得treeNode的高度为0
     */
    public static int getTreeNodeHeight(TreeNode treeNode) {

        // treeNode为null，则由treeNode求得的父结点高度为0
        if (treeNode == null) {
            return 0;
        }
        //由左子树求当前结点treeNode的高度
        int leftHeight = getTreeNodeHeight(treeNode.left);
        //由右子树求当前结点treeNode的高度
        int rightHeight = getTreeNodeHeight(treeNode.right);
        //当前结点的高度+1 为 父结点的高度
        int height = leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
        return height;

    }


    //方法二

    /**
     * 如果 树的节点个数 = 2^n -1 则是全二叉树，否则不是
     * 其中 n 是树的深度
     *
     * @param root
     * @return
     */
    public static boolean isFullBinTree2(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null) {
            return true;
        }

        List<Integer> values = new ArrayList<>();
        int deepAndValues = getDeepAndValues(root, values);
        if (values.size() == (1 << deepAndValues) - 1) {
            return true;
        }

        return false;
    }

    /**
     * 获取深度和所有节点
     *
     * @param treeNode
     * @param values
     * @return
     */
    public static int getDeepAndValues(TreeNode treeNode, List<Integer> values) {

        // treeNode为null，则由treeNode求得的父结点高度为0
        if (treeNode == null) {
            return 0;
        }
        values.add(treeNode.val);
        //由左子树求当前结点treeNode的高度
        int leftHeight = getDeepAndValues(treeNode.left, values);
        //由右子树求当前结点treeNode的高度
        int rightHeight = getDeepAndValues(treeNode.right, values);
        //当前结点的高度+1 为 父结点的高度
        int height = leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
        return height;

    }

}
