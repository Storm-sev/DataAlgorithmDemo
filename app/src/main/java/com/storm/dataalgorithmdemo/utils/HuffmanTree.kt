package com.storm.dataalgorithmdemo.utils

import java.util.*
import kotlin.collections.ArrayList

// 赫夫曼数
class HuffmanTree {

    private var array: Array<Int>
    private var nodes: ArrayList<Node>

    constructor(
        array: Array<Int>

    ) {
        this.array = array

        nodes = initNodes(this.array)

    }

    private fun initNodes(array: Array<Int>): ArrayList<Node> {
        var nodes = arrayListOf<Node>()

        for (i in array) {
            nodes.add(Node(i))
        }

        nodes.sort()

        return nodes
    }

    fun getNodes(): ArrayList<Node> {
        return nodes
    }

   public fun createHuffManTree() {


        while (nodes.size > 1) {
            nodes.sort()
            // 最后集合中就只有一个
            // 取出 左右节点
            var left = nodes[0]
            var right = nodes[1]

            // 创建 huffman 节点
            // 创建权重结点
            var parent = Node(left.value + right.value)
            // 设置权重结点的两个结点
            parent.left = left
            parent.right = right


            nodes.remove(left)
            nodes.remove(right)
            nodes.add(parent)
            nodes.sort()

        }

    }


}