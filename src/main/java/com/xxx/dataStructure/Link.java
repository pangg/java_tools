package com.xxx.dataStructure;

/**
 * 链表：
 * 链表的操作包括了创建、删除、插入、输出。
 * 创建就是空间的分配，将头、尾指针及链表结点个数等初始化。
 * 删除和插入根据被操作元素的位置可以细分为头删除（插入），尾删除（插入），中间删除（插入）
 */
public class Link {
    private int size = 0;
    private Node first;
    private Node last;

    /**
     * 链表初始化
     */
    public Link() {}

    /**
     * 返回链表长度
     * @return
     */
    public int getLength() {
        return size;
    }

    /**
     * 获取指定位置的节点
     * @param index 位置【0-size】
     * @return
     */
    public Node get(int index) {
        Node temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * 链表中插入第一个元素时，头和尾是同一个元素
     * @param element
     */
    private void oneNode(int element) {
        first = new Node();
        first.setData(element);
        last = first;
    }

    /**
     * 链表只剩一个节点时，清除first和last
     */
    private void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 插入尾节点
     * @param element
     */
    public void addTail(int element) {
        if (size == 0) {  // 链表为空时，插入尾节点即第一个元素
            oneNode(element);
        } else {
            Node node = new Node();
            node.setData(element);
            last.setNext(node);
            last = node;  // 尾节点设置为插入的节点
        }
        size ++;
    }

    /**
     * 链表头插入
     * @param element
     */
    public void addHead(int element) {
        if (size == 0) {
            oneNode(element);
        } else {
            Node node = new Node();
            node.setData(element);
            node.setNext(first);  // 新插入元素的指针指向原头元素
            first = node;  // 新插入的元素设为头节点
        }
        size ++;
    }

    /**
     * 插入中间元素，考虑头尾两种特殊情况
     * @param index 位置
     * @param element 值
     */
    public void add(int index, int element) {
        if (index < size) {
            if (size == 0) {  // 空链表
                oneNode(element);
                size ++;
            } else if (index == 0) {  // 插入的位置是头
                addHead(element);
            } else if (size == index) {  // 插入的位置是尾
                addTail(element);
            } else {
                Node temp = get(index - 1);  // 获取插入位置的节点
                Node node = new Node();  // 插入新节点
                node.setData(element);
                node.setNext(temp.getNext());
                temp.setNext(node);
                size ++;
            }
        } else {
            throw new IndexOutOfBoundsException("插入位置无效或超出列表长度");
        }
    }

    /**
     * 删除头节点
     */
    public void deleFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("空链表，无元素可删除");
        } else if (size == 1) {  // 只剩一个节点时，清除first和last
            clear();
        } else {
            Node temp = first;   // 为了将删除的头节点置空
            first = first.getNext();
            temp = null;
            size --;
        }
    }

    /**
     * 删除尾节点
     */
    public void deleLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("空链表，无元素可删除");
        } else if (size == 1) {  // 只剩一个节点时，清除first和last
            clear();
        } else {
            Node temp = get(size - 2);  //获取最后元素的前一个节点（前驱）
            temp.setNext(null);
            size --;
        }
    }

    /**
     * 删除中间元素，考虑头尾超界
     * @param index
     */
    public void deleMid(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("空链表，无节点可删");
        } else if (size == 1) {
            clear();
        } else {
            if (index == 0) {
                deleFirst();
            } else if (index == size - 1) {
                deleLast();
            } else if (index > size) {
                throw new IndexOutOfBoundsException("删除位置超届");
            } else {
                Node temp = get(index - 1);
                temp.setNext(get(index).getNext());
                size --;
            }
        }
    }

    /**
     * 获取链表
     */
    public void printLink() {
        Node temp = first;
        System.out.print(temp.getData());
        while ((temp = temp.getNext()) != null) {
            System.out.print("--->" + temp.getData());
        }
        System.out.println();
    }
}


