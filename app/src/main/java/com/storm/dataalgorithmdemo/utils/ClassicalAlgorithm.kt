package com.storm.dataalgorithmdemo.utils

import android.text.BoringLayout
import android.text.TextPaint
import java.security.CodeSource
import kotlin.math.max


/*
*   经典算法
 */
object ClassicalAlgorithm {


    /**
     * 二分查找算法 (非递归) 分治算法  .
     */
    fun binarySearch(array: Array<Int>, target: Int): Int {

        var left = 0
        var end = array.size - 1


        println("测试 获取的值 --> $end")

        while (left <= end) {
            // 获取中间值
            var mid = (left + end) / 2
            if (array[mid] == target) {
                return mid
            } else if (array[mid] < target) {
                left = mid + 1

            } else {
                end = mid - 1

            }
        }

        return -1;
    }

    /**
     * 汉诺塔经典算法
     */
    public fun hanoiTower(num: Int, a: String, b: String, c: String) {
        if (num == 1) {
            // 当只有一个的时候
            println("步骤 : 把 $a -> $c ")
        } else {
            // 1. 分成两个部分 底下的最后一个不动 上面一个部分为另一个 进行移动到b 的位置
            hanoiTower(num - 1, a, c, b)
            // 移动完成之后 a 移动到 c
            println("步骤 : 把 $a -> $c ")
            //把b 的东西移动到 c
            hanoiTower(num - 1, b, a, c)
        }
    }

    /**
     * 背包问题
     */
    fun knapsackProgrem() {
        // 创建表示物品的重量
        var w = arrayOf(1, 4, 3) // 不同物品的重量
        var value = arrayOf(1500, 3000, 2000) // 不同物品的价值
        var capacity = 4; // 背包的容量
        var num = value.size // 物品的数量

        // 创建一个数组集合 来存放其最大价值
        // 表示 在前i 个物品中 存放容量capacity 的最大价值
        var maxValue = Array(num + 1) { IntArray(capacity + 1) }
        // 用来记录最优解的时候存放的物品
        var path = Array(num + 1) { IntArray(capacity + 1) }

        // 初始化num 为0 和 物品容量为0 的时候的数据
        for (i in maxValue.indices) {
            maxValue[i][0] = 0 // 表示 当前所有物品个数num ,  背包容量为0 时候最大价值为0

        }
        for (i in maxValue[0].indices) {
            maxValue[0][i] = 0  // 表示当前物品为为0 不同容量的最大价值  为0
        }

        // 开始动态规划处理
        for (i in 1 until maxValue.size) { // 处理背包中物品的数据
            for (j in 1 until maxValue[i].size) {
                // j 代表重量
                // 程序是从1开始 如果 当前i个物品的重量大于当前背包的重量
                if (w[i - 1] > j) { // w[i-1] w 是从0开始的
                    maxValue[i][j] = maxValue[i - 1][j]
                } else {
                    // 如果 当前i 的重量 小于等于 j 那么就需要重新规划
                    // 计算当前的价值和  加入新的物品的价值 和剩余容量的最优价值组合成的价格哪个更大
                    // value[i-1] 表示的是当前i 的价值
                    // maxValue[i-1]{j-w[i-1]} 表示的上一个 是剩下容量的最优价值
//                    maxValue[i][j] = Math.max(
//                        maxValue[i - 1][j],
//                        value[i - 1] + maxValue[i - 1][j - w[i - 1]]
//                    )
                    // 为了方便存放最优解的时候东西
                    if (maxValue[i - 1][j] < value[i - 1] + maxValue[i - 1][j - w[i - 1]]) {
                        maxValue[i][j] = value[i - 1] + maxValue[i - 1][j - w[i - 1]]
                        path[i][j] = 1
                    } else {
                        maxValue[i][j] = maxValue[i - 1][j]
                    }
                }
            }

        }


        // 打印展示
        for (i in maxValue.indices) {
            for (j in maxValue[i].indices) {
                print("${maxValue[i][j].toString()}  ")

            }
            println()
        }

        println("获取存放的最优 ")

        var i = path.size - 1 // 和 w 的size 不一样
        var j = path[0].size - 1

        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                println("把第${i}个物品放入背包")
                j -= w[i - 1]   // 因为新增了一个0 所以需要减1
            }
            i--
        }

    }


    // 字符串暴力匹配算法
    fun violenceMath(source: String, target: String): Int {
        val s1 = source.toCharArray()
        val s2 = target.toCharArray()
        // 获取长度
        val len1 = source.length
        val len2 = target.length
        var i = 0
        var j = 0
        while (i < len1 && j < len2) {
            if (s1[i] == s2[j]) {
                i++
                j++
            } else {
                //  如果不相等 做一个i 前进1 的操作
                // j 置为0
                i -= (j - 1)
                j = 0
            }
        }
        // 循环结束后 是否匹配成功的条件
        return if (j == s2.size) {
            i - j
        } else -1


    }

    // kmp 字符串匹配算法
    public fun kmpSearch(src: String, dest: String, next: IntArray): Int {

        var j = 0
        var i = 0

        while (i < src.length) {
            while (j > 0 && src[i] != dest[j]) {
                j = next[j - 1]
            }

            if (src[i] == dest[j]) {
                j++
            }
            if (j == dest.length) {
                return i - j + 1
            }

            i++
        }
        return -1

    }

    /**
     * 部分字符 表
     */
    public fun kmpNext(dest: String): IntArray {


        var next = IntArray(dest.length)
        var j = 0
        next[0] = 0

        for (i in 1 until dest.length) {


            while (j > 0 && dest[i] != dest[j]) {
                j = next[j - 1]

            }
            if (dest[i] == dest[j]) {
                // 如果相同
                j++
            }

            next[i] = j
        }
        return next
    }

    //贪心算法
    public fun greedyAlgorithm() {
        // 创建广播他
        var brocast = hashMapOf<String, HashSet<String>>()
        var k1 = hashSetOf<String>()
        k1.add("北京")
        k1.add("上海")
        k1.add("天津")
        brocast.put("k1", k1)

        var k2 = hashSetOf<String>()
        k2.add("广州")
        k2.add("北京")
        k2.add("深圳")
        brocast.put("k2", k2)


        var k3 = hashSetOf<String>()
        k3.add("成都")
        k3.add("上海")
        k3.add("杭州")
        brocast.put("k3", k3)


        var k4 = hashSetOf<String>()
        k4.add("上海")
        k4.add("天津")
        brocast.put("k4", k4)

        var k5 = hashSetOf<String>()
        k5.add("杭州")
        k5.add("大连")
        brocast.put("k5", k5)

        // 创建一个包含所有的集合

        var allSet = hashSetOf<String>()

        // 初始化 allSet 里面装了所有的数据
        for (key in brocast.keys) {

            val set = brocast[key]
            set!!.forEach { s ->

                if (!allSet.contains(s)) {
                    allSet.add(s)
                }
            }

        }

        println(allSet.toList())

        // 设置一个选中的集合
        val selects = arrayListOf<String>()

        var maxKey: String? = null
        var tempSet = hashSetOf<String>()


        // 开始计算
        while (allSet.size != 0) { // 不断的从总集合中取出数据

            maxKey = null
            for (key in brocast.keys) {
                tempSet.clear()
                var set  = brocast[key]!!
                tempSet.addAll(set)
                tempSet.retainAll(allSet)

                if (tempSet.size > 0 &&(  null == maxKey ||  tempSet.size > brocast[maxKey]!!.size)){
                    maxKey = key

                }

            }
            //循环结束后找出了maxkey
            if (null != maxKey) {
                selects.add(maxKey)
                //要在allset 删除掉tempSet
                allSet.removeAll(brocast.get(maxKey)!!)
            }
        }

        println(selects.toList())
    }


    /**
     * 普利姆算法
     */
    public fun primAlgorithm() {

    }
}



// 创建最小生成树
class MinTree{

    /**
     * @param graph 传入图的参数
     * @param v 从哪个顶点开始 (无论哪个图 最后的结果都是 最小的路径)
     */
    public fun primAlgorithm(graph: MGraph, v: Int) {
        // 创建存放访问的集合 标记某个顶点是否被访问
        var visited = IntArray(graph.verxs)
        visited[v] = 1 // 当前点已经被访问
        var minPath = 10000 // 先放置一个最大值 表示的是两个顶点的最小距离
        var h1= -1;
        var h2 = -1;
        // 循环取每个结点(顶点) 普利姆算法 表示取的是 n-1 条边 那么循环次数k 需要-1
        for (k in 1 until graph.verxs) {
            // 判断 已经访问的顶点的集合 和他们当中未访问的集合
            // 这一个层集合循环表示的是 已经访问的顶点 也就是说从这些
            //顶点中去找出下一个邻近的未访问的点的
            for (i in 0 until graph.verxs) {
                for (j in 0 until graph.verxs) {
                    // j 标记没有访问的点
                    // i 层循环代表所有访问的点 每个和临近的点的未访问的点的距离
                    // visited[i] 已经访问的点
                    // visited[j] 未访问的点
                    // 找出这点所有访问的点和未访问点的最小距离 .
                    // 然后加入到访问的点中 有点每一个都走一个最优的路径.
                    if (visited[i] == 1 && visited[j] == 0 &&
                        graph.weight[i][j] < minPath
                    ) {
                        minPath = graph.weight[i][j]
                        h1 = i
                        h2 = j
                    }
                }
            }

            println("输出 顶点 ${graph.data[h1]} --> ${graph.data[h2]} , 权重 --> $minPath")


            // 循环完成之后 已经找出了最小的值
            visited[h2] = 1
            minPath= 10000 // 重置最小值path

        }

    }

    var  graph: MGraph? = null
    // 创建图的邻接矩阵
    /**
     * @param graph 图对象
     * @param 顶点的个数
     * @param 图的各个点的值
     * @param 图的邻接矩阵
     */
    public fun createGraph(
        graph: MGraph, verx: Int,
        data: CharArray, weight: Array<IntArray>
    ) {
        this.graph = graph
        var i: Int
        var j: Int

        for (i in 0 until verx) {
            graph.data[i] = data[i]
            for (j in 0 until verx) {
                graph.weight[i][j] = weight[i][j]
            }
        }
    }

    fun showGraph(graph: MGraph) {
        for (ints in graph.weight) {
            println(ints.toList())

        }

    }
}

// 普利姆算法的
class MGraph{

    var verxs :Int // 表示图的节点个数
    var data: CharArray // 存放点的数据
    var weight : Array<IntArray>  // 邻接矩阵来存放 不同点之间对应的权重 .

    //
    constructor(verx: Int){
        this.verxs = verx
        data = CharArray(verx)
        weight = Array(verx){ IntArray(verx) }

    }

}


