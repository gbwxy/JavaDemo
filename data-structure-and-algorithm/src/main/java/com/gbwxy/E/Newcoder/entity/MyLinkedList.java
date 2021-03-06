package com.gbwxy.E.Newcoder.entity;

import java.util.Hashtable;

/**
 * 单链表常见算法
 *
 * @author bjh
 */
public class MyLinkedList {

    /**
     * 链表的头结点
     */
    ListNode head = null;

    public ListNode getHead() {
        return head;
    }

    /**
     * 链表添加结点:
     * 找到链表的末尾结点，把新添加的数据作为末尾结点的后续结点
     *
     * @param val
     */
    public void appent(int val) {
        ListNode newListNode = new ListNode(val);
        if (head == null) {
            head = newListNode;
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newListNode;
    }

    /**
     * 链表删除结点:
     * 把要删除结点的前结点指向要删除结点的后结点，即直接跳过待删除结点
     *
     * @param index
     * @return
     */
    public boolean deleteListNode(int index) {
        if (index < 1 || index > length()) {//待删除结点不存在
            return false;
        }
        if (index == 1) {//删除头结点
            head = head.next;
            return true;
        }
        ListNode preListNode = head;
        ListNode curListNode = preListNode.next;
        int i = 1;
        while (curListNode != null) {
            if (i == index) {//寻找到待删除结点
                preListNode.next = curListNode.next;//待删除结点的前结点指向待删除结点的后结点
                return true;
            }
            //当先结点和前结点同时向后移
            preListNode = preListNode.next;
            curListNode = curListNode.next;
            i++;
        }
        return true;
    }

    /**
     * 求链表的长度
     *
     * @return
     */
    public int length() {
        int length = 0;
        ListNode curListNode = head;
        while (curListNode != null) {
            length++;
            curListNode = curListNode.next;
        }
        return length;
    }

    /**
     * 链表结点排序,并返回排序后的头结点:
     * 选择排序算法,即每次都选出未排序结点中最小的结点，与第一个未排序结点交换
     *
     * @return
     */
    public ListNode linkSort() {
        ListNode curListNode = head;
        while (curListNode != null) {
            ListNode nextListNode = curListNode.next;
            while (nextListNode != null) {
                if (curListNode.val > nextListNode.val) {
                    int temp = curListNode.val;
                    curListNode.val = nextListNode.val;
                    nextListNode.val = temp;
                }
                nextListNode = nextListNode.next;
            }
            curListNode = curListNode.next;
        }
        return head;
    }

    /**
     * 打印结点
     */
    public void printLink() {
        ListNode curListNode = head;
        while (curListNode != null) {
            System.out.print(curListNode.val + " ");
            curListNode = curListNode.next;
        }
        System.out.println();
    }

    /**
     * 去掉重复元素:
     * 需要额外的存储空间hashtable，调用hashtable.containsKey()来判断重复结点
     */
    public void distinctLink() {
        ListNode temp = head;
        ListNode pre = null;
        Hashtable<Integer, Integer> hb = new Hashtable<Integer, Integer>();
        while (temp != null) {
            if (hb.containsKey(temp.val)) {//如果hashtable中已存在该结点，则跳过该结点
                pre.next = temp.next;
            } else {//如果hashtable中不存在该结点，将结点存到hashtable中
                hb.put(temp.val, 1);
                pre = temp;
            }
            temp = temp.next;
        }
    }

    /**
     * 返回倒数第k个结点,
     * 两个指针，第一个指针向前移动k-1次，之后两个指针共同前进，
     * 当前面的指针到达末尾时，后面的指针所在的位置就是倒数第k个位置
     *
     * @param k
     * @return
     */
    public ListNode findReverListNode(int k) {
        if (k < 1 || k > length()) {//第k个结点不存在
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < k - 1; i++) {//前移k-1步
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 查找正数第k个元素
     */
    public ListNode findListNode(int k) {
        if (k < 1 || k > length()) {//不合法的k
            return null;
        }
        ListNode temp = head;
        for (int i = 0; i < k - 1; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表，在反转指针钱一定要保存下个结点的指针
     */
    public void reserveLink() {
        ListNode curListNode = head;//头结点
        ListNode preListNode = null;//前一个结点
        while (curListNode != null) {
            ListNode nextListNode = curListNode.next;//保留下一个结点
            curListNode.next = preListNode;//指针反转
            preListNode = curListNode;//前结点后移
            curListNode = nextListNode;//当前结点后移
        }
        head = preListNode;
    }

    /**
     * 反向输出链表，三种方式：
     * 方法一、先反转链表，再输出链表，需要链表遍历两次
     * 方法二、把链表中的数字放入栈中再输出，需要维护额外的栈空间
     * 方法三、依据方法2中栈的思想，通过递归来实现，递归起始就是将先执行的数据压入栈中，再一次出栈
     */
    public void reservePrt(ListNode ListNode) {
        if (ListNode != null) {
            reservePrt(ListNode.next);
            System.out.print(ListNode.val + " ");
        }
    }

    /**
     * 寻找单链表的中间结点：
     * 方法一、先求出链表的长度，再遍历1/2链表长度，寻找出链表的中间结点
     * 方法二、：
     * 用两个指针遍历链表，一个快指针、一个慢指针，
     * 快指针每次向前移动2个结点，慢指针一次向前移动一个结点，
     * 当快指针移动到链表的末尾，慢指针所在的位置即为中间结点所在的位置
     */
    public ListNode findMiddleListNode() {
        ListNode slowPoint = head;
        ListNode quickPoint = head;
        //quickPoint.next == null是链表结点个数为奇数时，快指针已经走到最后了
        //quickPoint.next.next == null是链表结点数为偶数时，快指针已经走到倒数第二个结点了
        //链表结点个数为奇数时,返回的是中间结点；链表结点个数为偶数时，返回的是中间两个结点中的前一个
        while (quickPoint.next != null && quickPoint.next.next != null) {
            slowPoint = slowPoint.next;
            quickPoint = quickPoint.next.next;
        }
        return slowPoint;
    }


    /**
     * 判断链表是否有环：
     * 设置快指针和慢指针，慢指针每次走一步，快指针每次走两步
     * 当快指针与慢指针相等时，就说明该链表有环
     */
    public boolean isRinged() {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回链表的最后一个结点
     */
    public ListNode getLastListNode() {
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 在不知道头结点的情况下删除指定结点：
     * 删除结点的重点在于找出其前结点，使其前结点的指针指向其后结点，即跳过待删除结点
     * 1、如果待删除的结点是尾结点，由于单链表不知道其前结点，没有办法删除
     * 2、如果删除的结点不是尾结点，则将其该结点的值与下一结点交换，然后该结点的指针指向下一结点的后续结点
     */
    public boolean deleteSpecialListNode(ListNode n) {
        if (n.next == null) {
            return false;
        } else {
            //交换结点和其后续结点中的数据
            int temp = n.val;
            n.val = n.next.val;
            n.next.val = temp;
            //删除后续结点
            n.next = n.next.next;
            return true;
        }
    }

    /**
     * 判断两个链表是否相交：
     * 两个链表相交，则它们的尾结点一定相同，比较两个链表的尾结点是否相同即可
     */
    public boolean isCross(ListNode head1, ListNode head2) {
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1.next != null) {
            temp1 = temp1.next;
        }
        while (temp2.next != null) {
            temp2 = temp2.next;
        }
        if (temp1 == temp2) {
            return true;
        }
        return false;
    }

    /**
     * 如果链表相交，求链表相交的起始点：
     * 1、首先判断链表是否相交，如果两个链表不相交，则求相交起点没有意义
     * 2、求出两个链表长度之差：len=length1-length2
     * 3、让较长的链表先走len步
     * 4、然后两个链表同步向前移动，没移动一次就比较它们的结点是否相等，第一个相等的结点即为它们的第一个相交点
     */
    public ListNode findFirstCrossPoint(MyLinkedList linkedList1, MyLinkedList linkedList2) {
        //链表不相交
        if (!isCross(linkedList1.head, linkedList2.head)) {
            return null;
        } else {
            int length1 = linkedList1.length();//链表1的长度
            int length2 = linkedList2.length();//链表2的长度
            ListNode temp1 = linkedList1.head;//链表1的头结点
            ListNode temp2 = linkedList2.head;//链表2的头结点
            int len = length1 - length2;//链表1和链表2的长度差

            if (len > 0) {//链表1比链表2长，链表1先前移len步
                for (int i = 0; i < len; i++) {
                    temp1 = temp1.next;
                }
            } else {//链表2比链表1长，链表2先前移len步
                for (int i = 0; i < len; i++) {
                    temp2 = temp2.next;
                }
            }
            //链表1和链表2同时前移,直到找到链表1和链表2相交的结点
            while (temp1 != temp2) {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1;
        }
    }

}