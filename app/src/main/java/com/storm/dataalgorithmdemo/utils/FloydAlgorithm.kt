package com.storm.dataalgorithmdemo.utils

import java.util.*

/**
 * Author:
 * Date : 2022/5/30
 * Class: com/storm/dataalgorithmdemo/utils/FloydAlgorithm.kt
 *
 * 弗洛伊德算法 求最短路径
 */
class FloydAlgorithm {

    companion object {
        const val N: Int = 65535 //

        var vertex: CharArray = charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G')

        // 邻接矩阵
        var matrix: Array<IntArray> = arrayOf(
            intArrayOf(0, 5, 7, N, N, N, 2),
            intArrayOf(5, 0, N, 9, N, N, 3),
            intArrayOf(7, N, 0, N, 8, N, N),
            intArrayOf(N, 9, N, 0, N, 4, N),
            intArrayOf(N, N, 8, N, 0, 5, 4),
            intArrayOf(N, N, N, 4, 5, 0, 6),
            intArrayOf(2, 3, N, N, 4, 6, 0)
        )


    }



    public fun floyd(){
        var graph = Graph(vertex, matrix)
        graph.floyd()
        graph.show()

    }


    /**
     *  创建图
     */
    class Graph {
        var vertex: CharArray // 顶点
        var dis: Array<IntArray> // 表示各个顶点和顶点之间的距离
        var pre: Array<IntArray> // 表示各个顶点的前驱结点

        constructor(vertex: CharArray, dis: Array<IntArray>) {
            this.vertex = vertex
            this.dis = dis
            this.pre = Array<IntArray>(vertex.size) { IntArray(vertex.size) }
            // 初始化 前驱结点 他们的前驱结点等于他们本省
            for (n in pre.indices) {
                Arrays.fill(pre[n], n)
            }
        }



        fun show() {
            println("---------")
            for (i in dis.indices) {
                println(dis[i].toList())
            }
            println("---------")
            for (i in pre.indices) {
                println(pre[i].toList())
            }

        }


        /**
         * 弗洛伊德算法
         */
        public fun  floyd(){
            var len= 0 //变量保存距离
            // 中间顶点的遍历
            for (k in dis.indices) {
                // 开始顶点的遍历
                for (s in dis.indices) {
                    // 结束顶点的遍历
                    for (e in dis.indices) {
                        // 增加判断
                        // 获取以k 为中间点 的距离
                        len = dis[s][k] + dis[k][e] // 找出的点 为 s -> k -> e 的距离
                        if (len < dis[s][e]) { //如果通多中间点计算出的距离小于  直连的距离
                            dis[s][e] = len // 更新距离
                            pre[s][e] = pre[k][e] // 更新此次距离过后 前驱结点
                        }
                    }
                }
            }
        }


    }


}