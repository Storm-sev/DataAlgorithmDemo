package com.storm.stormtestdemo.utils

import androidx.constraintlayout.solver.state.HelperReference

/**
 *  线索化 二叉树前遍历 中序遍历 后序遍历
 */
class ThreaderBinaryTree(root: HeroNode) {


    var rootNode: HeroNode? = root


    private var  preNode : HeroNode? = null;


    public  fun threaderNodes(){
        threaderNodes(rootNode)

    }
    /**
     * 线索化 二叉树
     */
    fun threaderNodes(node: HeroNode?) {
        node?.let {
            threaderNodes(it.left)
            // 处理前节点
            if (it.left == null) {
                it.left = preNode
                it.leftType = 1
            }
            // 处理后继节点
            if (null != preNode &&  preNode!!.right == null) {
                preNode!!.right= it
                preNode!!.rightType = 1
            }
            preNode  = it
            threaderNodes(it.right)
        }
    }

    fun preOrder() {
        if (null == rootNode) {
            System.out.println("二叉树为空 无法遍历")

        } else {
            rootNode!!.preOrder()
        }
    }

    fun threadInOrder(){
        if (null == rootNode) {
            System.out.println("二叉树为空, 无法遍历")
        }else{
            rootNode!!.threaderInOrder()
        }

    }
    fun inOrder() {
        if (null == rootNode) {
            System.out.println("二叉树为空, 无法遍历")
        } else {
            rootNode!!.inOrder()
        }
    }

    fun posOrder() {
        if (null == rootNode) {
            System.out.println("二叉树为空, 无法遍历")

        } else {
            rootNode!!.posOrder()
        }
    }

    fun queryOrderSearch(no: Int): HeroNode? {
        if (null == rootNode) {
            System.out.println("二叉树为空 无法查询")
            return null
        }

        return rootNode!!.queryPreOrder(no)

    }

    /**
     * 中序查找
     *
     */
    fun queryInOrderSeach(no: Int): HeroNode? {
        if (null == rootNode) {
            System.out.println("二叉树为空")
            return null;
        }
        return rootNode!!.queryInOrder(no)
    }

    // 后序查找
    fun queryPosOrderSearch(no: Int): HeroNode? {
        if (null == rootNode) {
            System.out.println("二叉树为空")
            return null;
        }
        return rootNode!!.queryPosOrder(no)
    }

    // 删除节点
    fun delNode(no: Int) {
        if (rootNode != null && rootNode!!.no == no) {
            rootNode = null
            return
        }

        if (rootNode != null) {
            rootNode!!.delPreOrder(no)
        }
    }

}