package com.storm.dataalgorithmdemo.utils


/**
 * 二叉搜索树
 *
 */
class AVLTree {

    var root: Node? = null


    public fun addNode(node: Node) {
        if (null == root) {
            root = node
        } else {
            root!!.addNode(node)
        }
    }

    public fun inOrder() {
        root?.let { it.inOrder() }
    }


    class Node {
        var value: Int
        var left: Node? = null
        var right: Node? = null

        constructor(value: Int) {
            this.value = value
        }


        //左旋转
        public fun leftRote() {
            //创建当前结点的临时变量
            var newNode = Node(value) // 直接= this 相当于指向地址值
            // 新的节点的左子结点 为当前结点的左子结点
            newNode.left = left
            //新结点的右子结点 等于 当前结点的右子结点的左子结点
            // 这个地方可能要做递归
            newNode.right = right!!.left
            // 改变当前结点的值为右子结点的值
            value = right!!.value
            // 当前结点的右子结点 等于右子结点 的右子结点
            right = right!!.right
            //当前结点的左子结点等于新的节点
            left = newNode

        }

        public fun rightRouute() {
            var newNode = Node(value)
            newNode.right = right
            newNode.left = left!!.right
            value = left!!.value
            left = left!!.left
            right = newNode

        }

        /**
         * 获取左子树的height
         */
        public fun leftHeight(): Int {
            return if (null == left) 0 else left!!.height()
        }

        /**
         * 返回右子树的高度
         */
        public fun rightHeight(): Int {
            return if (null == right) 0 else right!!.height()

        }

        /*
         * 返回以当前结点的高度  就是以当前结点为root 结点计算其高度
         */
        public fun height(): Int {

            return Math.max(
                if (null == left) 0 else left!!.height(),
                if (null == right) 0 else right!!.height()
            ) + 1

        }


        /**
         * 查找当前结点的父节点
         */
        public fun searchParent(value: Int): Node? {
            if ((null != left && left!!.value == value) ||
                null != right && right!!.value == value
            ) {
                return this
            } else {
                if (value < this.value) {
                    return left?.searchParent(value)
                } else if (value >= this.value) {
                    return right?.searchParent(value)
                } else {
                    return null // 没有找到父结点 .
                }

            }
        }

        /*
         * 查找目 标结点
         *
         */
        public fun search(value: Int): Node? {
            if (this.value == value) {
                return this
            } else if (value < this.value) {
                if (null == left) {
                    return null
                } else {
                    return left!!.search(value)

                }
            } else {
                if (null == right) {
                    return null
                } else {
                    return right!!.search(value)

                }
            }

        }

        /**
         * 按照二叉排序树的方法进行排序
         * 二叉排序的规则 先和当前node的值进行比较, 如果node 的值 小于当前的node 的值 那么放在左边,
         * 否则就放在右边的位置上 递归对每一个node 进行比较
         *
         */
        public fun addNode(node: Node) {
            if (node.value < this.value) {
                if (null == this.left) {
                    this.left = node
                } else {
                    this.left!!.addNode(node)

                }
            } else {
                if (null == this.right) {
                    this.right = node
                } else {
                    this.right!!.addNode(node)
                }
            }

            // 添加完成之后进行做旋转
            if (rightHeight() - leftHeight() > 1) {
                // 出现一种情况 就是当需要左旋转时候
                // 右子树的左子结点的高度 大于右子结点的高度
                // 需要先对其右子树进行右旋转
                if (null != right && right!!.leftHeight() > right!!.rightHeight()) {
                    right!!.rightRouute()
                }
                leftRote()
                return
            }


            if (leftHeight() - rightHeight() > 1) {

                if (null != left && left!!.rightHeight() > left!!.leftHeight()) {
                    left!!.leftRote()
                }
                rightRouute()
            }

        }


        /**
         * 中序遍历
         */
        public fun inOrder() {
            this.apply {
                this.left?.inOrder()
                println(value)
                this.right?.inOrder()
            }
        }

        override fun toString(): String {
            return "Node(value=$value)"
        }
    }
}

