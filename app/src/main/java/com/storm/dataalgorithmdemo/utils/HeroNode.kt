package com.storm.stormtestdemo.utils

/**
 * 节点
 */
class HeroNode {
    var no: Int? = null
    var name: String? = null

    var left: HeroNode? = null
    var right: HeroNode? = null
    var leftType: Int = 0
    var rightType: Int = 0
    // 0 代表当前的节点的左右节点是节点指向的左子树
    // 1 代表当前节点 left 前驱节点 后驱节点 .

    constructor()
    constructor(no: Int, name: String) {
        this.no = no
        this.name = name

    }

    override fun toString(): String {
        return "HeroNode(no=$no, name=$name)"
    }

    /**
     * 前序 遍历
     */
    fun preOrder() {

        System.out.println(this)
        this.left?.preOrder()
        this.right?.preOrder()

    }

    // 中序遍历
    fun inOrder() {
        this.left?.inOrder()
        System.out.println(this)
        this.right?.inOrder()
    }


    /**
     * 线索化二叉树的遍历
      */
    fun threaderInOrder(){
         if (this.leftType == 0){
             this.left?.threaderInOrder()
         }

        System.out.println(this)
        if (this.rightType == 0) {
            this.right?.threaderInOrder()
        }
    }


    /**
     * 后序遍历
     */
    fun posOrder() {
        this.left?.posOrder()
        this.right?.posOrder()
        System.out.println(this)
    }


    /**
     * 前序查找节点
     */
    fun queryPreOrder(no: Int): HeroNode? {
        if (this.no == no) {
            return this
        }

        var node: HeroNode? = null
        if (null != this.left) {
            node = this.left!!.queryPreOrder(no)
        }

        if (null != node) {
            return node
        }
        if (null != this.right) {
            node = this.right!!.queryPreOrder(no)

        }
        return node
    }

    fun queryInOrder(no: Int): HeroNode? {
        var node: HeroNode? = null
        if (null != this.left) {
            node = this.left!!.queryInOrder(no)
        }
        if (null != node) {
            return node
        }
        if (this.no == no) {
            return this
        }

        if (null != this.right) {
            node = this.right!!.queryInOrder(no)

        }
        return node
    }

    fun queryPosOrder(no: Int): HeroNode? {
        var node: HeroNode? = null
        if (null != this.left) {
            node = this.left!!.queryPosOrder(no)

        }

        if (null != node) return node

        if (null != this.right) {
            node = this.right!!.queryPosOrder(no)

        }

        if (this.no == no) {
            return this
        }
        return node

    }

    // 删除节点
    fun delPreOrder(no: Int) {
        if (null != this.left) {
            if (this.left!!.no == no) {
                this.left = null
                return
            } else {
                this.left!!.delPreOrder(no)
            }
        }

        if (null != this.right) {
            if (this.right!!.no == no) {
                this.right = null
                return
            } else {
                this.right!!.delPreOrder(no)
            }
        }

    }
}