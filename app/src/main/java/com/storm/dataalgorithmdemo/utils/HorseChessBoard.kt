package com.storm.dataalgorithmdemo.utils

import android.graphics.Point


/**
 * Author:
 * Date : 2022/5/30
 * Class: com/storm/dataalgorithmdemo/utils/HorseChessBoard.kt
 * 马踏棋盘算法
 */
class HorseChessBoard {

    companion object {
        var X: Int = 0
        var Y: Int = 0

    }

    //记录棋盘中的访问位置是否被访问
    public var visited: BooleanArray
    public var finished: Boolean = false

    constructor(visited: BooleanArray) {

        this.visited = visited

    }


    /**
     * 最终算法 计算 走过的路程
     * @param chessboard Array<IntArray>
     * @param row Int
     * @param column Int
     * @param step Int
     */
    public fun traversalChessBoarder(
        chessboard: Array<IntArray>,
        row: Int, column: Int, step: Int,
    ) {
        //首先 标记当前走过的路程的
        chessboard[row][column] = step
        // 设置当前访问的点已经被访问
        visited[row * X + column] = true
        // 获取当前点的可以走的下一步点的集合
        var list: ArrayList<Point> = next(Point(column, row))

        sort(list)

        // 循环走
        while (list.isNotEmpty()) {
            // 取出当前的点
            var p = list.removeAt(0)
            // 判断是否被访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoarder(chessboard, p.y, p.x, step + 1)
            }

        }

        // 到目前为止 没有走完 ,
        // 棋盘回溯过程中
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0
            visited[row * X + column] = false
        } else {
            finished = true

        }


    }

    public fun sort(ps: ArrayList<Point>) {
        ps.sortWith(Comparator<Point> { p0, p1 ->
            val s0 = next(p0).size
            val s1 = next(p1).size
            if (s0 < s1) {
                return@Comparator -1
            }else if (s1 == s0) {
                return@Comparator  0
            }else{
                return@Comparator 1
            }
        })
    }

    /**
     *  获取下一步需要走的位置 最多一共有8 个位置  并且加入到位置集合中
     *
     * @param cur Point
     */
    public fun next(cur: Point): ArrayList<Point> {
        // 创建下一步位置的集合
        var result = arrayListOf<Point>()
        var p = Point()

        if (cur.x - 2 >= 0 && cur.y - 1 >= 0) {
            p.x = cur.x - 2
            p.y = cur.y - 1

            result.add(Point(p))

        }

        if (cur.x - 1 >= 0 && cur.y - 2 >= 0) {
            //满足条件 添加
            p.x = cur.x - 1
            p.y = cur.y - 2

            result.add(Point(p))
        }
        if (cur.x - 2 >= 0 && cur.y + 1 < Y) {
            //满足条件 添加
            p.x = cur.x - 2
            p.y = cur.y + 1
            result.add(Point(p))
        }

        if (cur.x - 1 >= 0 &&
            cur.y + 2 < Y
        ) {
            //满足条件 添加
            p.x = cur.x - 1
            p.y = cur.y + 2
            result.add(Point(p))
        }

        if (cur.x + 2 < X &&
            cur.y - 1 >= 0
        ) {
            p.x = cur.x + 2
            p.y = cur.y - 1
            //满足条件 添加
            result.add(Point(p))
        }

        if (cur.x + 1 < X &&
            cur.y - 2 >= 0
        ) {
            p.x = cur.x + 1
            p.y = cur.y - 2
            //满足条件 添加
            result.add(Point(p))
        }

        if (cur.x + 2 < X &&
            cur.y + 1 < Y
        ) {
            p.x = cur.x + 2
            p.y = cur.y + 1
            //满足条件 添加
            result.add(Point(p))
        }
        if (cur.x + 1 < X &&
            cur.y + 2 < Y
        ) {
            //满足条件 添加
            p.x = cur.x + 1
            p.y = cur.y + 2
            result.add(Point(p))
        }

        return result

    }


    class Point {
        var x: Int = 0
        var y: Int = 0

        constructor()
        constructor(x: Int, y: Int) {
            this.x = x
            this.y = y

        }

        constructor(src: Point) {
            this.x = src.x
            this.y = src.y

        }
    }


}