package com.storm.dataalgorithmdemo.utils


import java.util.*


open class DijkstraAlgorithm {


    companion object {
        const val N: Int = 65535 // 表示一个最大的值 表示两个顶点不链接


    }


    var vv: visitedVertex? = null
    var vertex: CharArray
    var matrix: Array<IntArray>

    constructor(vertex: CharArray, matrix: Array<IntArray>) {
        this.vertex = vertex
        this.matrix = matrix
    }


    public fun show() {
        vv?.show()

    }

    /**
     * 迪杰斯特拉算法
     * @param index Int 表示出发顶点对应的下标
     */
    public fun dijkstra(index: Int) {
        var index = index
        // 创建 visitedVertex
        vv = visitedVertex(vertex.size, index)
        //更新index 顶点到周围顶点的距离和前驱结点
        update(index)
        for (i in 1 until vertex.size) {
            index = vv!!.updateArr()
            update(index)
        }

    }


    /**
     * 更新 index 顶点到周围顶点的距离和前置结点
     * @param index Int
     *
     * 更新index 传入不一定是起始点 ,也有可能是下一个需要访问的节点
     * 比如A 点 传入 , 计算len 距离G点的总距离
     * len = A点到起始点的距离 +  当前A点到其他各个点的距离
     * 如果 j点没被访问 并且len 计算出的距离小于 j 点距离起始点的距离 (初始化的时候 把这个点的距离设置为N 也就是最大
     *  加上 当前点到起始点的距离 如果路不通的话 就肯定会出现 len > vv.getdDis(j)  所以 此处判断是否路通)
     *  然后去更新j的前驱 对于循环来说前驱都是一样的,
     * 然后动态更新 未访问的点到起始点的距离 已经访问过的就不动了
     * 更新之后 在通过updateArr 选出到起始点最短的哪一个点
     *
     *
     */
    private fun update(index: Int) {
        if (null == vv) return
        var len: Int = 0

        // 计算当前点到各个可到达的点的距离
        for (j in matrix[index].indices) {
            // 计算初始点到 当前j点的距离
            len = vv!!.getDis(index) + matrix[index][j] //

            // j 没有被访问 并且 len小于
            if (!vv!!.visited(j) && len < vv!!.getDis(j)) {
                vv!!.update(j, index) // 更新 前驱点 he现在的点
                vv!!.updateDis(j, len)
            }
        }

    }


    /**
     * 已访问点的集合
     */
    class visitedVertex {
        // 用来记录各个顶点是否访问过 1. 访问过 0 未访问
        var already_arr: IntArray

        //当前顶点的前驱结点的下标 会动态更新
        var pre_visited: IntArray

        // 记录起始点到其他所有顶点的距离,  会动态更新
        var dis: IntArray

        /**
         * @param length   顶点个数
         * @param 出发顶点下标
         */
        constructor(length: Int, index: Int) {
            this.already_arr = IntArray(length)
            this.pre_visited = IntArray(length)
            this.dis = IntArray(length)

            //初始化访问
            already_arr[index] = 1
            // 初始化 dis
            Arrays.fill(dis, N)
            dis[index] = 0 // 设置出发顶点的访问距离是 0

        }

        /**
         * @param index 根据下标判断当前是否被访问
         *
         */
        public fun visited(index: Int): Boolean = already_arr[index] == 1

        /**
         *  更新出发顶点的访问距离
         *  @param index 要更新的点
         *  @param len 更新的值 距离
         */

        public fun updateDis(index: Int, len: Int) {
            dis[index] = len
        }


        /**
         * 更新 pre 这个顶点的的前驱顶点的下标
         * @param pre Int   当前顶点
         * @param index Int 当前顶点前驱顶点的下标
         */
        public fun update(pre: Int, index: Int) {
            pre_visited[pre] = index

        }

        /**
         *  返回 dis 中出发点到当前i 的距离
         * @param i Int
         */
        public fun getDis(i: Int): Int {
            return dis[i]
        }


        /**
         * 继续选择返回下一个新的访问结点 , 是不是距离最小
         * 通过对比当前未访问的节点 以及 条件找到到起始点到当前点距离最小的点 来作为下一个访问的点
         * 比如 G 点到 A 点 B点 的距离为 2 , 3  那么就选择A 点 当然 A B点都是未访问过得
         */
        fun updateArr(): Int {
            var min = N
            var index = 0

            for (i in already_arr.indices) {
                if (already_arr[i] == 0 && dis[i] < min) {
                    min = dis[i]
                    index = i
                }
            }

            already_arr[index] = 1

            return index
        }


        fun show() {
            println()
            println(already_arr.toList())
            println("------------")
            println(pre_visited.toList())
            println("------------")
            println(dis.toList())
        }

    }


    // 用来表示两个顶点之间的权值
    class Graph(
        var vertex: CharArray,
        var matrix: Array<IntArray>,
    ) {


        /**
         * 显示图
         */
        public fun showGraph() {
            for (i in matrix.indices) {
                println()
                for (j in matrix[i].indices)
                    System.out.printf("%12d", matrix[i][j])


            }
        }

    }
}

