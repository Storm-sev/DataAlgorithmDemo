package com.storm.dataalgorithmdemo.utils

import java.util.*

/**
 * 堆排序
 *
 */
class HeapSort {


    private var arr: Array<Int>

    constructor(arr: Array<Int>) {
        this.arr = arr

    }

    public fun heapSort() {
//        ajustheap(arr, arr.size / 2 - 1, arr.size)
        // 首先把无需数据构建成一个有序的数据

        var temp: Int
        for (i in arr.size / 2 - 1 downTo 0) {
            ajustheap(arr, i, arr.size)
        }

        //首次首尾的位置 转换成小顶堆

        for (i in arr.size - 1 downTo 0) {

            temp = arr[i]
            arr[i] = arr[0]
            arr[0] = temp
            ajustheap(arr, 0, i)
        }


    }


    fun arrToString() {
        System.out.println("数组 --> ${Arrays.toString(arr)}")

    }


    companion object {

        /**
         * 数组挪动到大顶堆
         *
         */
        public fun ajustheap(arr: Array<Int>, i: Int, length: Int) {

            var temp = arr[i] // 取出当前节点的值

            var i = i;

            var k = 2 * i + 1
            while (k < length) {
                if (k + 1 < length && arr[k] < arr[k + 1]) {
                    // 当前节点的左子节点 小于当前节点的右子节点 那么将k定位到右子节点
                    k++
                }

                // 右子结点 大于当前节点
                if (arr[k] > temp) {
                    arr[i] = arr[k]
                    i = k
                } else {
                    break
                }


                k = k * 2 + 1
            }
            arr[i] = temp

        }
    }
}