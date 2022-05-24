package com.storm.dataalgorithmdemo.utils

import java.util.*
import kotlin.collections.ArrayList

/**
 * 图相关
 */
class Graph {

    private var vertexList: ArrayList<String> //存储定顶点点集合的点
    private var edges: Array<IntArray> //  存储图的邻接矩阵
    private var numOfEdge: Int

    // 创建一个是否被访问的集合
    private var isVisited: BooleanArray

    constructor(n: Int) {
        edges = Array(n) { IntArray(n) }
        vertexList = ArrayList(n)
        numOfEdge = 0
        isVisited = BooleanArray(n)


    }

    public fun bfs() {
        isVisited = BooleanArray(vertexList.size)


        for (i in vertexList.indices) {
            if (!isVisited[i]) {
                bfs(isVisited, i)
            }
        }
    }

    /**
     * 针对一个结点的广度优先遍历
     */
    public fun bfs(isVisited: BooleanArray, index: Int) {
        var u: Int //定义一个标志位 标记队列中的头结点
        var w: Int // 首个邻接节点
        var queue: LinkedList<Int> = LinkedList<Int>() // 队列用来存放保存访访问的结点
        // 输出当前访问的节点
        println("${getValueInIndex(index)} -> ")
        //标记已经被访问
        isVisited[index] = true
        // 加入到访问的队列中
        queue.addLast(index)
        while (!queue.isEmpty()) { // 当前队列不为空
            // 出队列 找到u
            u = queue.removeFirst()
            // 找到 u 的下一个邻接节点w
            w = getFirstNeighbor(u)
            while (w != -1) { // 如果邻接节点 已经存在 循环在一个以u 为基础 去找 一行的
                // 判断是否已经被访问
                if (!isVisited[w]) {
                    // 没有被访问
                    println("${getValueInIndex(w)} -> ")
                    //标记被访问
                    isVisited[w] = true
                    // 加入队列
                    queue.addLast(w)

                }
                //操作当前邻接节点之后  以u 为结点 找w之后下一个邻接节点
                w = getNextNeighbor(u, w)
            }
        }


    }

    public fun dfs() {
        isVisited = BooleanArray(vertexList.size)

        for (i in vertexList.indices) {
            if (!isVisited[i]) {
                dfs(isVisited, i)
            }
        }
    }

    // 深度遍历
    // 此方法只是对其一个结点进行了访问  如果当前这个节点为null 或者访问过了 就停止了

    public fun dfs(isVisited: BooleanArray, index: Int) {
        //  首先访问当前结点
        print("${getValueInIndex(index)} --> ")
        // 标记当前已访问
        isVisited[index] = true

        // 查找当前结点的第一个邻接节点
        var f = getFirstNeighbor(index)
        // 循环去找当前结点的所有邻接节点
        while (f != -1) { // 如果有第一邻接节点
            if (!isVisited[f]) { // 没有被访问
                // 那么以当前f 为初始结点进行递归访问
                dfs(isVisited, f)
            }
            // 被访问 找出当前第一邻接节点的下一个节点
            f = getNextNeighbor(index, f)
        }

    }

    // 返回第一邻接点的下标
    // 通过邻接矩阵来表示.
    public fun getFirstNeighbor(index: Int): Int {
        for (j in vertexList.indices) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据当前邻接点获取下一个邻接点的下标
    fun getNextNeighbor(v1: Int, v2: Int): Int {

        for (j in v2 + 1 until vertexList.size) {
            if (edges[v1][j] > 0) {
                return j
            }
        }
        return -1

    }

    public fun showEdge() {
        for (edge in edges) {
            println(edge.contentToString())
        }
    }

    public fun getNumOfEdge(): Int = numOfEdge


    /**
     * 返回两个结点的权值 .
     */
    fun getWeight(v1: Int, v2: Int): Int {
        return edges[v1][v2]
    }

    /**
     * 返回结点的个数
     *
     */
    fun getVertexSize(): Int {

        return vertexList.size

    }

    fun getValueInIndex(index: Int): String {
        return vertexList.get(index)

    }

    /**
     * 添加一个顶点
     */
    public fun addVertex(verter: String) {
        vertexList.add(verter)
    }

    /**
     * 插入一个顶点
     */
    public fun insertVertex(vertex: String) {
        vertexList.add(vertex)
    }

    /**
     * 插入一条边
     */
    public fun insetEdges(v1: Int, v2: Int, weight: Int) {
        edges[v1][v2] = weight
        edges[v2][v1] = weight
        numOfEdge++
    }


}