package com.gbwxy.E.Newcoder;




//题目描述
//     在O(n log n)的时间内使用常数级空间复杂度对链表进行排序。
//     Sort a linked list in O(n log n) time using constant space complexity.
//示例1
//    输入
//      {3,2,4}
//    输出
//      {2,3,4}


import com.gbwxy.E.Newcoder.entity.ListNode;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/29
 */
public class SingleListNodeSort {

    /**
     * @param head ListNode类
     * @return ListNode类
     */
    public static ListNode sortList(ListNode head) {
        // write code here
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode midNext = mid.getNext();
        mid.setNext(null);
        return mergeSort(sortList(head), sortList(midNext));

    }

    /**
     * 快慢指针，获取中间节点
     *
     * @param head
     * @return
     */
    private static ListNode getMid(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode slow = head, quick = head;
        while (quick.getNext() != null && quick.getNext().getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }


    private static ListNode mergeSort(ListNode n1, ListNode n2) {
        ListNode preHead = new ListNode();
        ListNode cur1 = n1;
        ListNode cur2 = n2;
        ListNode cur = preHead;
        while (cur1 != null && cur2 != null) {
            if (cur1.getVal() < cur2.getVal()) {
                cur.setNext(cur1);
                cur1 = cur1.getNext();
            } else {
                cur.setNext(cur2);
                cur2 = cur2.getNext();
            }
            cur = cur.getNext();
        }
        if (cur1 != null) {
            cur.setNext(cur1);
        }
        if (cur2 != null) {
            cur.setNext(cur2);
        }
        return preHead.getNext();
    }

}
