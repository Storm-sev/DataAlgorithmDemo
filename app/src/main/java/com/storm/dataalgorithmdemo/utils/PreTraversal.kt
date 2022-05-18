package com.storm.stormtestdemo.utils

/**
 *  前序遍历
 */
class PreTraversal(root: HeroNode) {


    var rootNode: HeroNode? = root

    fun preOrder() {
        if (null == rootNode) {
            System.out.println("二叉树为空 无法遍历")

        } else {
            rootNode!!.preOrder()
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