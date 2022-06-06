package com.xxx.dataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树实现和遍历
 * 参考：https://www.cnblogs.com/LUA123/p/11820757.html
 */
public class BinaryTree2 {

    public static void main(String[] args){
        BinaryTree2 tree = new BinaryTree2();
        tree.batchInsert(new int[]{10,3,1,8,23,15,28});
        tree.prePrint();
        tree.midPrint();
        tree.postPrint();
        tree.tierPrint();
        tree.printDepth();
    }

    /**
     * 节点
     */
    private class TNode {
        int data;  // 数据
        TNode left; // 左指针
        TNode right; // 右指针
        private TNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TNode root;

    /**
     * 插入节点
     * @param data
     */
    public void insert(int data) {
        TNode newData = new TNode(data);
        if (root == null) {
            root = newData;
        } else {
            TNode parent = root;
            while (true) {
                if (data < parent.data) {
                    // 如果左边为空，那新数据就直接放在这
                    if (parent.left == null) {
                        parent.left = newData;
                        break;
                    }
                    // 进入左节点
                    parent = parent.left;
                } else {
                    // 如果右边为空，那新数据就直接放在这
                    if (parent.right == null) {
                        parent.right = newData;
                        break;
                    }
                    // 进入右节点
                    parent = parent.right;
                }
            }
        }
    }

    /**
     * 批量插入
     * @param arr
     */
    public void batchInsert(int[] arr) {
        for (int data : arr) {
            insert(data);
        }
    }

    /**
     * 前序遍历
     */
    public void prePrint() {
        System.out.print("前序遍历\t");
        if (root != null) {
            pre(root);
        }
        System.out.println();
    }

    private void pre(TNode node) {
        if (node != null) {
            System.out.print(node.data + "\t");
            pre(node.left);
            pre(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midPrint() {
        System.out.print("中序遍历\t");
        if (root != null) {
            mid(root);
        }
        System.out.println();
    }

    private void mid(TNode node) {
        if (node != null) {
            mid(node.left);
            System.out.print(node.data + "\t");
            mid(node.right);
        }
    }

    /**
     * 后序
     */
    public void postPrint() {
        System.out.print("后序遍历\t");
        if (root != null){
            post(root);
        }
        System.out.println();
    }

    private void post(TNode node){
        if (node != null) {
            post(node.left);
            post(node.right);
            System.out.print(node.data + "\t");
        }
    }

    /**
     * 层序遍历，利用队列先进先出
     */
    public void tierPrint() {
        if (root != null) {
            Queue<TNode> queue = new LinkedList<>();
            queue.add(root);
            System.out.print("层序遍历\t");
            while (! queue.isEmpty()) {
                TNode temp = queue.remove();
                System.out.print(temp.data + "\t");
                if (temp.left != null) {
                    // 左节点不为空，放进队列
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    // 右节点不为空，放进队列
                    queue.add(temp.right);
                }
            }
        }
        System.out.println();
    }

    /**
     * 求树高
     */
    public void printDepth(){
        if (root == null){
            System.out.println(0);
        }else{
            System.out.println("树高\t" + getDepth(root));
        }
    }

    private int getDepth(TNode node){
        if (node == null){
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right))+1;
    }

}
