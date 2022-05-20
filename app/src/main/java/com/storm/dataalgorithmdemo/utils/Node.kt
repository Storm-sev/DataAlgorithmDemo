package com.storm.dataalgorithmdemo.utils

import com.storm.stormtestdemo.utils.HeroNode

/**
 * 节点
 */
class Node : Comparable<Node> {

    var value: Int

    var left: Node? = null
    var right: Node? = null

    constructor(value: Int) {
        this.value = value

    }

    override fun toString(): String {
        return "Node(value=$value)"
    }

    override fun compareTo(other: Node): Int {
        return this.value - other.value
    }


    // 前序遍历
    fun preOrder() {
        println(this)
        this.left?.preOrder()
        this.right?.preOrder()


    }

}