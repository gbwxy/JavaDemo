package com.gbwxy.B.SwordForOffer;

import com.gbwxy.A.Entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/22
 */
public class A_52_DoubleListNode {

    /**
     * 输入两个链表，找出它们的第一个公共节点。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 方法一 利用 HashSet
     * 先把第一个链表的节点全部存放到集合set中，
     * 然后遍历第二个链表的每一个节点，判断在集合set中是否存在，
     * 如果存在就直接返回这个存在的结点。
     * 如果遍历完了，在集合set中还没找到，说明他们没有相交，直接返回null即可
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //创建集合set
        Set<ListNode> set = new HashSet<>();
        //先把链表A的结点全部存放到集合set中
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        //然后访问链表B的结点，判断集合中是否包含链表B的结点，如果包含就直接返回
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }
        //如果集合set不包含链表B的任何一个结点，说明他们没有交点，直接返回null
        return null;
    }


    /**
     * 先统计两个链表的长度
     * 先统计两个链表的长度，
     * 如果两个链表的长度不一样，就让链表长的先走，
     * 直到两个链表长度一样，这个时候两个链表再同时每次往后移一步，
     * 看节点是否一样，
     * 如果有相等的，说明这个相等的节点就是两链表的交点，
     * 否则如果走完了还没有找到相等的节点，说明他们没有交点，直接返回null即可
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //统计链表A和链表B的长度
        int lenA = length(headA), lenB = length(headB);

        //如果节点长度不一样，节点多的先走，直到他们的长度一样为止
        while (lenA != lenB) {
            if (lenA > lenB) {
                //如果链表A长，那么链表A先走
                headA = headA.next;
                lenA--;
            } else {
                //如果链表B长，那么链表B先走
                headB = headB.next;
                lenB--;
            }
        }

        //然后开始比较，如果他俩不相等就一直往下走
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        //走到最后，最终会有两种可能，一种是headA为空，
        //也就是说他们俩不相交。还有一种可能就是headA
        //不为空，也就是说headA就是他们的交点
        return headA;
    }

    //统计链表的长度
    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

    /**
     * 双指针解决
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        //tempA和tempB我们可以认为是A,B两个指针
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            //如果指针tempA不为空，tempA就往后移一步。
            //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
            tempA = tempA == null ? headB : tempA.next;
            //指针tempB同上
            tempB = tempB == null ? headA : tempB.next;
        }
        //tempA要么是空，要么是两链表的交点
        return tempA;
    }


}
