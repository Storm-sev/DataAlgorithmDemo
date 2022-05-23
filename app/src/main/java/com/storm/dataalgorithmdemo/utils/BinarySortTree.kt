package com.storm.dataalgorithmdemo.utils

import kotlin.math.min

/**
 * 二叉排序树
 */
class BinarySortTree {

    var root: Node? = null

    constructor()


    public fun delNode(value: Int) {
        root?.let {

            var target = search(value)
            // 没有找到
            if (null == target) {
                return
            }

            // 判断如果target 没有父结点 也就是当前二叉树 就一个结点
            if (it.left == null && it.right == null) {
                // 只有一个root 结点
                root = null
                return
            }
            var parent = searchParent(value)
            // 第一种情况 叶子结点
            if (null == target.left && null == target.right) { // 表示是叶子节点
                if (parent!!.left != null && parent.left!!.value == value) {
                    parent.left = null
                    return
                }
                if (parent?.right != null && parent.right!!.value == value) {

                    parent.right = null
                }
            } else if (null != target!!.left && null != target.right) {
                var minValue = delRightTreeMin(target)
                target.value = minValue
            } else {
                if (null != target.left) {
                    if (null != parent) {
                        //左子树不为空
                        if (null != parent.left) {
                            parent.left = target.left
                        } else {
                            parent.right = target.left
                        }

                    } else {
                        root = target.left
                    }

                } else {
                    if (null != parent) {
                        if (null != parent.left) {
                            parent.left = target.right
                        } else {
                            parent.right = target.right
                        }
                    } else {
                        root = target.right
                    }
                }
            }

        }
    }


    public fun delRightTreeMin(node: Node): Int {
        var target = node
        while (null != target.left) {
            target = target.left!!
        }

        delNode(target.value)
        return target.value


    }

    // 查找结点
    public fun search(value: Int): Node? {
        return if (null == root) {
            null
        } else {
            root!!.search(value)
        }
    }

    /**
     * 查到当前值的父结点
     */
    public fun searchParent(value: Int): Node? {

        return if (null == root) {
            null
        } else {
            root!!.searchParent(value)
        }
    }

    // 添加结点
    public fun addNode(node: Node) {

        if (null == root) {
            root = node
        } else {
            root!!.addNode(node)
        }

    }

    public fun inOrder() {
        root?.inOrder()
    }


    class Node {
        var value: Int
        var left: Node? = null
        var right: Node? = null

        constructor(value: Int) {
            this.value = value
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