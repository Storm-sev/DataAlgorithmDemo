package com.storm.dataalgorithmdemo.utils


/**
 * 克鲁斯卡尔算法 (解决公交问题)
 *
 */
class KruskalCase {

    companion object {
        const val INF = Int.MAX_VALUE

    }

    var edgeNum: Int = 0 // 边的个数
    var vertexs: CharArray //  顶点数组
    var matrix: Array<IntArray> // 邻接矩阵

    constructor(vertexs: CharArray, matrix: Array<IntArray>) {
        var len = vertexs.size

        this.vertexs = CharArray(len)
        for (i in vertexs.indices) {
            this.vertexs[i] = vertexs[i]

        }
        // 初始化 邻接矩阵

        this.matrix = Array(len) { IntArray(len) }

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                this.matrix[i][j] = matrix[i][j]

            }
        }
        // 处理边
        for (i in matrix.indices) {
            var j = i + 1
            while (j < matrix[i].size) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++
                }
                j++
            }
        }


    }


    // 算法开始
    public fun kruskal(): Array<EData?> {
        var index = 0 //表示记录结果的下标
        // 创建记录各个顶点的终点的下标的集合
        var ends = IntArray(edgeNum)
        // 创建结果集
        var result = arrayOfNulls<EData>(edgeNum)
        // 获取边的集合
        val edges = getEdges()
        // 进行排序
        sortEdge(edges)

        // 开始
        for (i in edges.indices) {
            // 获取第一条边的起始点
            /// 获取顶点的下标
            var p1 = getPosition(edges.get(i)!!.start)
            var p2 = getPosition(edges.get(i)!!.end)

            // 然后获取这两个点的 终点
            var m = getEnd(ends, p1)
            var n = getEnd(ends, p2)
            if (m != n) { // 没有构成回路
                ends[m] = n  // 设置m 的终点为n
                result[index++] = edges[i]
            }
        }

        return result

    }

    public fun showMatrix() {
        for (i in matrix.indices) {
            println()
            for (j in matrix.indices) {
                System.out.printf("%12d", matrix[i][j])
            }
        }

    }


    /**
     * 对边进行排序
     * @param arr 传入的资源数组 进行从大到小排序
     * 用冒泡排序
     */
    public fun sortEdge(arr: Array<EData?>) {
        for (i in arr.indices) {
            for (j in 0 until (arr.size - 1 - i)) {
                if (arr[j] != null && null != arr[j + 1]) {
                    if (arr[j]!!.weight > arr[j + 1]!!.weight) {
                        var temp = arr[j]
                        arr[j] = arr[j + 1]
                        arr[j + 1] = temp
                    }
                }

            }
        }

    }

    /**
     * 获取所有边的集合
     */
    public fun getEdges(): Array<EData?> {

        var index = 0


        val edges: Array<EData?> = arrayOfNulls<EData>(edgeNum)


        for (i in vertexs.indices) {
            var j = i + 1
            while (j < vertexs.size) {
                if (matrix[i][j] != INF) {
                    edges[index++] = EData(vertexs[i], vertexs[j], matrix[i][j])
                }
                j++
            }
        }

        return edges
    }


    /**
     * 获取当前 顶点的下标
     */
    public fun getPosition(ch: Char): Int {

        for (i in vertexs.indices) {
            if (vertexs[i] == ch) {
                return i
            }
        }
        return -1;

    }

    /**
     * 获取下标为i的顶点的终点  判断两个点的终点是否相同 .
     *@param ends 记录了各个顶点的终点是哪个  , 根据添加的时候是动态变化的
     * @param 表示传入的点的下标
     * 返回对应i 下标顶点的终点的下标
     */
    public fun getEnd(ends: IntArray, i: Int): Int {
        var index = i
        while (ends[index] != 0) { // 循环是为了找出顶点终点的终点
            index = ends[index]

        }
        return index
    }
}

/**
 * 表示一条边的class
 * @param start 起点
 * @param end 终点
 * @param weight 权重
 */
class EData(var start: Char, var end: Char, var weight: Int) {

    override fun toString(): String {
        return "EData(start=$start, end=$end, weight=$weight)"
    }
}
