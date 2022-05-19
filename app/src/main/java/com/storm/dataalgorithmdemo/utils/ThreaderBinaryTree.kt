package com.storm.stormtestdemo.utils

import androidx.constraintlayout.solver.state.HelperReference

/**
 *  线索化 二叉树前遍历 中序遍历 后序遍历
 */
class ThreaderBinaryTree(root: HeroNode) {


    var rootNode: HeroNode? = root


    private var preNode: HeroNode? = null;


    public fun threaderNodes() {
        threaderNodes(rootNode)

    }




    public fun threaderListNodes() {
        var node = rootNode
        while (null != node) {
            // 找到中序最开始的节点
            while (node!!.leftType == 0) {
                node = node.left
            }
            // 打印节点
            System.out.println(node)

            while (node!!.rightType == 1) {
                node = node.right
                System.out.println(node)
            }
            node = node.right
        }
    }


    public fun threaderListNodePre() {
        var node = rootNode

        while (null != node) {

            System.out.println(node)
            while (node!!.leftType == 0) {
                node = node.left
                System.out.println(node)

            }
            while (node!!.rightType == 1) {
                node = node.right
                System.out.println(node)

            }
            node = node.right

        }

    }

    public fun threaderListNodePos(){
        var node = rootNode

        while (null != node) {
            while (node!!.leftType == 0) {
                node = node.left
            }
            System.out.println(node)
            while (node!!.rightType == 1) {
                node = node.right
                System.out.println(node)

            }



        }
    }

    fun threadNodesPre() {
        threadNodesPre(rootNode)
    }

    fun threaderNodePos() {
        threaderNodePos(rootNode)

    }

    fun threaderNodePos(node: HeroNode?) {
        node?.let {
            if (it.leftType == 0) {
                threaderNodePos(it.left)

            }

            if (it.rightType == 0) {
                threaderNodePos(it.right)

            }
            if (it.left == null) {
                it.left = preNode
                it.leftType = 1
            }

            if (null != preNode && preNode!!.right == null) {
                preNode!!.right = it
                preNode!!.rightType = 1

            }

            preNode = it

        }
    }

    /**
     * 线索化二叉树前序
     */
    fun threadNodesPre(node: HeroNode?) {

        node?.let {

            if (it.left == null) {
                it.left = preNode
                it.leftType = 1
            }

            //上一次的设置
            if (null != preNode && preNode!!.right == null) {


                preNode!!.right = it
                preNode!!.rightType = 1
            }

            preNode = it

            if (it.leftType == 0) {
                threadNodesPre(it.left)

            }
            if (it.rightType == 0) {
                threadNodesPre(it.right)
            }
        }
    }

    /**
     * 线索化二叉树 中序
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
            if (null != preNode && preNode!!.right == null) {
                preNode!!.right = it
                preNode!!.rightType = 1
            }
            preNode = it
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

    fun threadInOrder() {
        if (null == rootNode) {
            System.out.println("二叉树为空, 无法遍历")
        } else {
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