package com.storm.dataalgorithmdemo

import android.widget.HeaderViewListAdapter
import com.storm.dataalgorithmdemo.utils.*
import com.storm.stormtestdemo.utils.HeroNode
import com.storm.stormtestdemo.utils.PreTraversal
import com.storm.stormtestdemo.utils.ThreaderBinaryTree
import org.junit.Test
import org.junit.runners.model.TestClass
import java.io.File
import kotlin.concurrent.thread

class CircleQueueTest {

    /**
     * 图的邻接矩阵
     */
    @Test
    public fun edgesTest() {
        var array = arrayOf("A", "B", "C", "D", "E")

        var graph = Graph(5)

        for (s in array) {
            graph.insertVertex(s)
        }
        graph.showEdge()
        // 设置 边
        // a->b  a-c
        graph.insetEdges(0, 1, 1)
        graph.insetEdges(0, 2, 1)
        // b-c  b-d  b-e
        graph.insetEdges(1, 2, 1)
        graph.insetEdges(1, 3, 1)
        graph.insetEdges(1, 4, 1)


        println("----添加边之后---- ")
        graph.showEdge()
        println("深度遍历")
//        graph.dfs()
        println("广度遍历")
        graph.bfs()


    }

    @Test
    public fun avlTreeTest() {

        var array = arrayOf(10, 11, 7, 6, 8, 9)
        var tree = AVLTree()
        for (i in array) {
            tree.addNode(AVLTree.Node(i))

        }

        println(
            "heftHeight -> ${tree.root?.leftHeight()} " +
                    "rightHeight --> ${tree.root?.rightHeight()}  " +
                    "  height${tree.root?.height()}"
        )


        tree.root?.right


        // 添加一个树进行左旋转
//        tree.addNode(AVLTree.Node(10))
//        leftHeight = tree.root?.leftHeight();
//        rightHeight = tree.root?.rightHeight()
//        height = tree.root!!.height()
//
//
//        println(
//            "heftHeight -> $leftHeight " +
//                    "rightHeight --> $rightHeight" +
//                    "height$height"
//        )


    }

    @Test
    fun binarySortTree() {
        var array = arrayOf(7, 3, 10, 12, 5, 1, 9, 0)
        var sort = BinarySortTree()
        for (i in array) {
            sort.addNode(BinarySortTree.Node(i))

        }

//        sort.inOrder()
//        val search = sort.root?.search(2)
//        println(search.toString())
        sort.inOrder()
        sort.delNode(10)
        println("-----------------")
        sort.inOrder()

    }

    @Test
    fun huffmanFileZip() {
        //测试文件写入
        var fileurl = ClassLoader.getSystemResource("yao.jpg")
        var file = File(fileurl.path)
        val exists = file.exists()
        var zipUrl = ClassLoader.getSystemResource("yao.zip")


        var yaozip = File(zipUrl.path)

        HuffmanCode.zipFile(file, yaozip)

        var outpath = ClassLoader.getSystemResource("storm").path
        var outFile = File(outpath)

        HuffmanCode.unzipFile(yaozip, outFile)


//        HuffmanCode.unzipFile(file,)


    }

    @Test
    fun huffManCode() {
        var str = "haha i like love like java do you like a java"

        var byteArray = HuffmanCode.huffmanZip(str.toByteArray())

        println(byteArray.toList())

        HuffmanCode.huffmanUnzip(byteArray, HuffmanCode.huffmanCode)


//        var huffmanCode = HuffmanCode(str.toByteArray())

//        System.out.println("---> ${huffmanCode.nodes}")
//        val root = huffmanCode.huffManCodeTree()
//        huffmanCode.preOrder(root)
//
//        val map = HuffmanCode.createHuffmanCode(root)
//
//        println(map)
//        HuffmanCode.zip(str.toByteArray(), map)


    }

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
    public fun heapSortTest() {
        // 创建数组
        var arr = arrayOf(4, 6, 8, 5, 9)
        var heapSort = HeapSort(arr)
        heapSort.heapSort()
        heapSort.arrToString()

    }

    @Test
    public fun huffmanTreeTest() {
        var arr = arrayOf(13, 7, 8, 3, 29, 6, 1)
        var huffmanTree = HuffmanTree(arr)
        huffmanTree.createHuffManTree()
        val nodes = huffmanTree.getNodes()
        if (nodes.size == 1) {
            nodes[0].preOrder()
        }

    }
}