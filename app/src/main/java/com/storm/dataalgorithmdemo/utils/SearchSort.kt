package com.storm.dataalgorithmdemo.utils

import java.security.Key
import java.util.*
import kotlin.collections.ArrayList

/**
 *  查找算法
 */
object SearchSort {


    /**
     * 线性查找 有序无需都可以使用
     * @param array IntArray
     * @param value Int
     * @return Int
     */
    public fun seqSearch(array: IntArray, value: Int): Int {

        for (i in array.indices) {
            if (value == array[i]) {
                return i
            }

        }
        return -1

    }

    /**
     * 递归方式 二分查找
     * @param array IntArray
     * @param left Int
     * @param right Int
     * @param findValue Int 寻找的值
     */
    public fun binarySearch(array: IntArray, left: Int, right: Int, findValue: Int): Int {


        if (left > right) {

            return -1 // 没有查到
        }
        var mid = (left + right) / 2
        var midValue = array[mid]
        if (findValue < midValue) {
            return binarySearch(array, left, mid - 1, findValue)
        } else if (findValue > midValue) {
            return binarySearch(array, mid + 1, right, findValue)
        } else {
            return mid

        }
    }

    /**
     * 二分查找的非递归 方式
     * @param array IntArray
     */
    public fun binarySearch2(array: IntArray, findValue: Int): Int {


        var left = 0
        var end = array.size - 1


        while (left <= end) {
            var mid = (left + end) / 2
            if (array[mid] < findValue) {
                left = mid + 1

            } else if (array[mid] > findValue) {
                end = mid - 1
            } else {
                return mid
            }
        }
        return -1
    }

    /**
     * 返回所有符合条件的数的下标
     * 一个集合中可能存在多个相同的值
     * @param array IntArray
     * @param left Int
     * @param right Int
     * @param findValue Int
     */
    public fun binarySearchList(
        array: IntArray,
        left: Int,
        right: Int,
        findValue: Int
    ): ArrayList<Int>? {

        if (left > right) {
            return arrayListOf()
        }
        var mid = (left + right) / 2
        var midValue = array[mid]
        if (midValue < findValue) {
            // 右递归
            return binarySearchList(array, mid + 1, right, findValue)
        } else if (midValue > findValue) {
            return binarySearchList(array, left, mid - 1, findValue)
        } else {
            //说明找到
            var temp = mid - 1
            var indexList = arrayListOf<Int>()


            // 向左寻找
            while (true) {
                if (temp > 0 || array[temp] != findValue) {
                    break
                }
                indexList.add(temp)
                temp -= 1
            }

            indexList.add(mid)

            temp = mid + 1
            while (true) {
                if (temp > right && array[temp] != findValue) {
                    break
                }
                indexList.add(temp)
                temp += 1
            }

            return indexList


        }
    }


    /**
     * 插值查找算法
     * @param array IntArray
     * @param left Int
     * @param right Int
     * @param findValue Int
     */
    public fun insertSearch(array: IntArray, left: Int, right: Int, findValue: Int): Int {

        // 如果查找的数比较大的话 会造成 数据溢出
        if (left > right || findValue < array[0] || findValue > array[array.size - 1]) {
            return -1
        }


        var mid = left + (right + left) * (findValue - array[left]) / (array[right] - array[left])

        var midValue = array[mid]
        if (midValue < findValue) {
            return insertSearch(array, mid + 1, right, findValue)
        } else if (midValue > findValue) {
            return insertSearch(array, left, mid - 1, findValue)
        } else {
            return mid
        }

    }


    /**
     * 获取一个斐波那契数列
     * @param maxSize Int
     */
    public fun fib(maxSize: Int): IntArray {
        var arr = IntArray(maxSize)

        arr[0] = 1
        arr[1] = 1
        for (i in 2 until maxSize) {
            arr[i] = arr[i - 1] + arr[i - 2]

        }
        return arr

    }

    public fun bifSearch(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.size - 1
        //
        var f = fib(array.size + 1) // 根据数组的大小 创建出同等大小的菲波那切数列 下标的数组
        var mid = 0 // 中间的值
        var k = 0
        // 找到 表示当前数组的个数的下标
        while (high > f[k] - 1) {
            k++
        }

        // 临时数组 让 array 的大小符合 斐波那契数列的分割
        var temp = Arrays.copyOf(array, f[k])

        // 补齐值
        for (i in high + 1 until temp.size) {
            temp[i] = temp[high]

        }

        while (low <= high) {
            mid = low + f[k - 1] - 1

            if (key < temp[mid]) {
                high = mid - 1
                // 前面查找

                k--
            } else if (key > temp[mid]) {
                //  后面查找
                low = mid + 1
                k -= 2


            } else {
                return mid
            }
        }
        return -1
    }


}