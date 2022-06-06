package com.xxx.dataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 参考：https://www.cnblogs.com/LUA123/p/11820757.html
 */
public class BalancedBinaryTree {

    public static void main(String[] args){
        BalancedBinaryTree tree = new BalancedBinaryTree();
        tree.batchInsert(new int[]{8,6,3,4,5,20,15,23,28,1,2});
        tree.tierPrint();
        tree.prePrint();
        tree.midPrint();
        tree.postPrint();
    }

    // 节点
    private class TNode {
        int data;
        TNode left;
        TNode right;

        private TNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TNode root;

    /**
     * 左旋(右子为轴,当前结点左旋)
     *     p                      pr
     *    / \                    / \
     *   pl  pr       =>        p   rr
     *      / \                / \
     *     rl  rr             pl  rl
     * 参考：https://www.jianshu.com/p/94a1ce4128bd
     * @param p
     * @return
     */
    private TNode leftRotate(TNode p) {
        TNode temp = p.right; // temp指向p的右子树
        p.right = temp.left;  // p的右子树指向temp的左子树
        temp.left = p;   // temp左子树指向p
        return temp;
    }

    /**
     * 右旋
     *       p                pl
     *      / \              / \
     *     pl  pr    =>     ll  p
     *    / \                  / \
     *   ll  lr               lr  pr
     *
     * @param p
     * @return
     */
    private TNode rightRotate(TNode p) {
        TNode temp = p.left;
        p.left = temp.right;
        temp.right = p;
        return temp;
    }

    /**
     * 先左旋再右旋
     * @param p
     * @return
     */
    private TNode leftRightRotate(TNode p) {
        p.left = leftRotate(p.left);
        return rightRotate(p);
    }

    /**
     * 先右旋再左旋
     * @param p
     * @return
     */
    private TNode rightLeftRotate(TNode p) {
        p.right = rightRotate(p.right);
        return leftRotate(p);
    }

    /**
     * 树高
     * @param node
     * @return
     */
    private int getDepth(TNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

    /**
     * 平衡因子(左高：>1 等高：0 右高：<-1)
     *
     * @param node
     * @return
     */
    public int balanceFactor(TNode node) {
        if (node == null) {
            return 0;
        }
        return getDepth(node.left) - getDepth(node.right);
    }

    /**
     * 插入
     * @param node
     * @param data
     * @return
     */
    public TNode insert(TNode node, int data) {
        TNode newData = new TNode(data);
        if (node == null) {
            return newData;
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        int bf = balanceFactor(node);
        if (bf > 1 && data < node.left.data) {
            // LL 右旋
            System.out.println("LL: " + data);
            return rightRotate(node);
        } else if (bf < -1 && data > node.right.data) {
            // RR 右旋
            return leftRotate(node);
        } else if (bf > 1 && data > node.left.data){
            // LR
            System.out.println("LR: " + data);
            return leftRightRotate(node);
        } else if (bf < -1 && data < node.right.data){
            // RL
            System.out.println("RL: " + data);
            return rightLeftRotate(node);
        }
        return node;
    }

    /**
     * 批量插入
     * @param arr
     */
    public void batchInsert(int[] arr) {
        for (int data : arr) {
            root = insert(root, data);
        }
    }

    /**
     * 前序遍历
     */
    public void prePrint(){
        System.out.print("前序遍历\t");
        if (root != null){
            pre(root);
        }
        System.out.println();
    }

    private void pre(TNode node){
        if (node != null) {
            System.out.print(node.data + "\t");
            pre(node.left);
            pre(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midPrint(){
        System.out.print("中序遍历\t");
        if (root != null){
            mid(root);
        }
        System.out.println();
    }

    private void mid(TNode node){
        if (node != null) {
            mid(node.left);
            System.out.print(node.data + "\t");
            mid(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postPrint(){
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
    public void tierPrint(){
        if (root != null){
            Queue<TNode> queue = new LinkedList<>();
            queue.add(root);
            System.out.print("层序遍历\t");
            while (!queue.isEmpty()){
                TNode temp = queue.remove();
                System.out.print(temp.data + "\t");
                if (temp.left != null){
                    // 左节点不为空，放进队列
                    queue.add(temp.left);
                }
                if (temp.right != null){
                    // 右节点不为空，放进队列
                    queue.add(temp.right);
                }
            }
        }
        System.out.println();
    }
}
