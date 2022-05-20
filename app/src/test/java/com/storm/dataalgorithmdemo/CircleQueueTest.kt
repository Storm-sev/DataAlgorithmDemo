package com.storm.dataalgorithmdemo

import android.widget.HeaderViewListAdapter
import com.storm.dataalgorithmdemo.utils.*
import com.storm.stormtestdemo.utils.HeroNode
import com.storm.stormtestdemo.utils.PreTraversal
import com.storm.stormtestdemo.utils.ThreaderBinaryTree
import org.junit.Test
import kotlin.concurrent.thread

class CircleQueueTest {

    @Test
    fun circleTest() {

        var queue = CircleArrayQueue(4)

        queue.addQueue(10)
        queue.addQueue(20)
        queue.addQueue(30)
        queue.addQueue(40)
        queue.getQueue()
        queue.addQueue(40)
        queue.getQueue()
        queue.addQueue(50)
        queue.showQueue()
    }

    @Test
    fun stackTest() {
        var stack = Stack(4)
        stack.addStack(10)
        stack.addStack(20)
        stack.addStack(30)
        stack.addStack(40)
        stack.addStack(50)
        stack.showStack()
        stack.pop()
        stack.showStack()

    }

    @Test
    fun traversalTest() {
        var node1 = HeroNode(1, "节点1")
        var node2 = HeroNode(2, "节点2")
        var node3 = HeroNode(3, "节点3")
        var node4 = HeroNode(4, "节点4")
        var node5 = HeroNode(5, "节点5")
        var node6 = HeroNode(6, "节点6")
        var node7 = HeroNode(7, "节点7")


        node1.left = node2
        node1.right = node3
        node2.left = node4
        node2.right = node5
        node3.left = node6
        node3.right = node7
        // 测试前序输出
        var preTraversal = PreTraversal(node1)
        preTraversal.posOrder();

//        val queryNode = preTraversal.queryOrderSearch(6)
//        System.out.println("查找到的node --> ${queryNode.toString()}")
//        preTraversal.delNode(3)
//        preTraversal.preOrder()
    }

    @Test
    fun arrBinaryThree() {
        var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        var arrOrder = ArrBinaryThree(arr)
        arrOrder.posOrder(0)

    }

    @Test
    fun threaderNodesTest() {
        var node1 = HeroNode(1, "节点1")
        var node2 = HeroNode(2, "节点2")
        var node3 = HeroNode(3, "节点3")
        var node4 = HeroNode(4, "节点4")
        var node5 = HeroNode(5, "节点5")
        var node6 = HeroNode(6, "节点6")
        var node7 = HeroNode(7, "节点7")


        node1.left = node2
        node1.right = node3
        node2.left = node4
        node2.right = node5
        node3.left = node6

        var threaderNods = ThreaderBinaryTree(node1)
//        threaderNods.posOrder()

        threaderNods.threaderNodePos()

        System.out.println("node4 left - ${node4.left} + right - ${node6.left}")
        threaderNods.threaderListNodePos()
    //        threaderNods.threaderListNodePre()
//        threaderNods.threaderNodes()
//        threaderNods.threaderListNodes()
//        System.out.println("自己写的遍历")
//        threaderNods.threadInOrder()
    }


    /**
     * 堆排序测试
     */
    @Test
    public fun heapSortTest(){
        // 创建数组
        var arr = arrayOf(4, 6, 8, 5, 9)
        var heapSort = HeapSort(arr)
        heapSort.heapSort()
        heapSort.arrToString()

    }

    @Test
    public fun huffmanTreeTest(){
        var arr = arrayOf(13,7,8,3,29,6,1)
        var huffmanTree = HuffmanTree(arr)
        huffmanTree.createHuffManTree()
        val nodes = huffmanTree.getNodes()
        if (nodes.size == 1) {
            nodes[0].preOrder()
        }

    }
}