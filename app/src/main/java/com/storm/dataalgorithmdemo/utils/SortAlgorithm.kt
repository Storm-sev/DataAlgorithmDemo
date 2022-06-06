package com.storm.dataalgorithmdemo.utils

import org.w3c.dom.Text
import kotlin.math.max

/**
 * Author:
 * Date : 2022/5/30
 * Class: com/storm/dataalgorithmdemo/utils/SortAlgorthm.kt
 * 排序算法
 */
object SortAlgorithm {


    /**
     * 冒泡排序
     *
     * @param array IntArray
     */
    public fun bubbleSort(array: IntArray): IntArray {

        var flag = false
        for (i in array.indices) {
            for (j in 0 until array.size - i - 1) {
                if (array[j] > array[j + 1]) {
                    flag = true
                    var temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }

            if (!flag) { // 没有发生交换
                break
            } else { // 发生了交换
                flag = false // 进行重置
            }
        }
        return array

    }


    /**
     * 选择排序
     * @param array IntArray
     */
    public fun selectSort(array: IntArray): IntArray {

//        for (i in 0 until array.size - 1) {
//           var  index = i
//            for (j in i until array.size) {
//
//                if (array[j] < array[index]) {
//                   index = j
//                }
//            }
//
//            if (index != i) {
//                var temp = array[i]
//                array[i] = array[index]
//                array[index] = temp
//            }
//
//        }
//

        for (i in 0 until array.size - 1) {
            // 得到最小值 h和最小值的下标
            var min = array[i]
            var minIndex = i
            for (j in i + 1 until array.size) {
                if (min > array[j]) {
                    min = array[j]
                    minIndex = j
                }
            }

            if (minIndex != i) {
                array[minIndex] = array[i]
                array[i] = min
            }
        }
        return array
    }


    /**
     * 插入排序
     * @param arr IntArray
     *
     */
    public fun insertSort(arr: IntArray): IntArray {

        for (i in 1 until arr.size) { // 当前第一个 相当于一个独立的数组
            var insertVal = arr[i]
            var insertIndex = i - 1 // 找出i 之前的下标
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // 第一次的时候就把他覆盖掉了
                // 如果 要插入的数据 小于当前insertindex 整体后面移动
                arr[insertIndex + 1] = arr[insertIndex]
                insertIndex--
            }

            arr[insertIndex + 1] = insertVal

        }

        return arr

    }


    /**
     * 希尔排序
     * @param arr IntArray
     * 是一种改进之后的插入排序法 简单插入排序进行缩小增量排序
     * 1. 交换法
     * 通过数组的大小来吧数组/2 分成几个更小的集合
     * 然后根据步长位置上的数据来进行交换 然后再次缩小步长 再次比较
     *
     */
    public fun shellSort(arr: IntArray): IntArray {

        var temp = 0
        var gap = arr.size / 2
        while (gap > 0) {

            for (i in gap until arr.size - 1) {
                var j = i - gap

                while (j >= 0) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j]
                        arr[j] = arr[j + gap]
                        arr[j + gap] = temp
                    }
                    j -= gap

                }
            }

            gap /= 2

        }
        return arr
    }


    /**
     * 希尔排序
     * 是一种改进之后的插入排序法 简单插入排序进行缩小增量排序
     * 1. 交换法
     * 通过数组的大小来吧数组/2 分成几个更小的集合
     * 然后根据步长位置上的数据来进行交换 然后再次缩小步长 再次比较
     * 交换的进行改进
     * 移位法
     * @param array IntArray
     */
    public fun shellSort2(arr: IntArray): IntArray {

        val gap = arr.size / 2

        for (gap in arr.size / 2 downTo 0 step gap / 2) {
            // 从gap 所在的组 直接进行插入排序
            for (i in gap until arr.size) { // 增加
                // 然后用插入排序的方法
                var j = i // 找到当前值
                var insertVal = arr[j] // 假设在这一个分组里面最小
                if (arr[j] < arr[j - gap]) {
                    // 需要进行插入排序
                    while (j - gap >= 0 && insertVal < arr[j - gap]) {
                        arr[j] = arr[j - gap]
                        j -= gap
                    }

                    // 退出while 循环 min 找到位置
                    arr[j] = insertVal

                }

            }
        }

        return arr

    }


    /**
     * 快速排序法
     * @param arr IntArray
     */
    public fun quickSort(arr: IntArray, left: Int, right: Int) {

        var l = left
        var r = right

        // 找出中间的点
        var temp = 0
        var pirvot = arr[(l + r) / 2]
        // pirvot 小的放在左边 大的放在右边
        while (l < r) {
            // 左边 过滤 比pirvot 大于等于 的值
            while (arr[l] < pirvot) {
                l++
            }

            //
            while (arr[r] > pirvot) {
                r--
            }

            // 说明左右两边的值 都符合pirvot的中间值
            if (l >= r) {
                break
            }

            // 不满足说明找到了 交换当前left  right 的值
            temp = arr[l]
            arr[l] = arr[r]
            arr[r] = temp

            //  交换完成之后 发现pivot
            // l 这时候是代表之前r 位置的值 ,
            // r 的下标刚好卡到了中间值pirvot 上 r 跳过交换的值
            if (pirvot == arr[l]) {
                r--
            }

            // 同理
            if (pirvot == arr[r]) {
                l++
            }

        }

        // 如果跳出循环
        if (l == r) {
            l += 1
            r -= 1
        }

        //其实是l r 交换了
        if (left < r) {
            quickSort(arr, left, r)

        }

        if (l < right) {
            quickSort(arr, l, right)

        }


    }

    public fun testSort(arr: IntArray, left: Int, right: Int, temp: IntArray) {
        if (left < right) {
            var mid = (left + right) / 2
            testSort(arr, left, mid, temp)
            testSort(arr, mid + 1, right, temp)
            testMerge(arr, left, mid, right, temp)
        }
    }

    //归并排序合并
    public fun testMerge(arr: IntArray, left: Int, mid: Int, right: Int, temp: IntArray) {
        var i = left
        var j = mid + 1
        var t = 0

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i]
                i += 1
                t += 1

            } else {
                temp[t] = arr[j]
                j += 1
                t += 1

            }
        }

        while (i <= mid) {
            temp[t] = arr[i]
            i += 1
            t += 1
        }
        while (j <= right) {
            temp[t] = arr[j]
            j += 1
            t += 1

        }

        t = 0
        var index = left
        while (index <= right) {
            arr[index] = temp[t]
            t += 1
            index += 1
        }


    }

    /**
     * 归并排序的方法
     * @param arr IntArray
     * @param left Int
     * @param right Int
     * @param temp IntArray
     */
    public fun mergeSort(arr: IntArray, left: Int, right: Int, temp: IntArray) {
        if (left < right) {
            //   获取中间的值
            var mid = (left + right) / 2
            // 分  左边
            mergeSort(arr, left, mid, temp)

            mergeSort(arr, mid + 1, right, temp)
            merge(arr, left, mid, right, temp)
        }
    }


    /**
     * 归并排序 合并方法
     * @param arr IntArray 排序的原始数组
     * @param left Int 左边有序序列的初始索引
     * @param mid Int  中间索引
     * @param right Int 右边索引
     * @param temp IntArray  中转的数组
     *
     */
    public fun merge(arr: IntArray, left: Int, mid: Int, right: Int, temp: IntArray) {

        var i: Int = left // 左边的初始索引
        var j: Int = mid + 1 // 右边的初始索引
        var t = 0 // 临时数组

        // 先把左右两边的数据通过比较 添加到临时数组中,
        // 保证两边的数据有一边都完全添加了  // 有序 不是左右分为小数和大数
        while (i <= mid && j <= right) { //设置范围
            if (arr[i] < arr[j]) { // 如果左边的数小于右边的数字
                temp[t] = arr[i]
                t += 1
                i += 1
            } else {
                temp[t] = arr[j]
                t += 1
                j += 1
            }
        }

        // 左右两边会有没有全部添加的情况
        while (i <= mid) {
            temp[t] = arr[i]
            t += 1
            i += 1

        }
        // 同理右边
        while (j <= right) {
            temp[t] = arr[j]
            t += 1
            j += 1
        }

        // temp 数组元素拷贝到arr
        // 并不是每次拷贝所有 这个方法只是切割了数组 可能是数组的合并
        t = 0
        var tempIndex = left
        while (tempIndex <= right) {
            arr[tempIndex] = temp[t]
            t += 1
            tempIndex += 1
        }

    }


    /**
     * 基数排序方法
     * @param arr IntArray
     */
    public fun radixSort(arr: IntArray) {
        // 第一轮 放数据 取数据
        // 元素的个位进行排序处理
        // 定义一个二维数组表示 每一个桶就是一个一维数组
        // 为了防止 数据溢出, 每一个桶大小都是arr.size
        // 空间换时间的算法
        var bucket = Array(10) { IntArray(arr.size) }
        // 记录 每一个桶的保存数据的index 放入数据的个数
        var bucketEleCounts: IntArray = IntArray(10)

        // 获取到了最大值
        var max = arr[0]
        for (i in arr.indices) {
            if (arr[i] > max) {
                max = arr[i]
            }
        }

        //获取到最大值的长度
        var maxLength = "$max".length
        var n = 1
        for (i in 0 until maxLength) {
            // 放入数据
            for (j in 0 until arr.size - 1) {
                // 取出位数上的数据
                var digitOfElement = (arr[j] / n) % 10
                // 放入数据
                bucket[digitOfElement][bucketEleCounts[digitOfElement]] = arr[j]
                bucketEleCounts[digitOfElement]++
            }

            //临时变量
            var index = 0
            // 遍历桶
            for (k in bucket.indices) {
                // 如果 bucketEleCounts 中的值不为0 说明有数据
                if (bucketEleCounts[k] != 0) {
                    // 遍历桶 取出数据 放入arr  根据
                    for (l in 0 until bucketEleCounts[k]) {
                        arr[index++] = bucket[k][l]
                    }
                }
                bucketEleCounts[k] = 0
            }

            n *= 10

        }

    }


    /**
     * 基数排序测试
     */
    public fun radixTest(arr: IntArray) {
        //创建捅

        var bucket = Array(10){ IntArray(arr.size)}
        var bucketCount = IntArray(10)


        var maxLength = arr[0]
        for (i in arr.indices) {
            if (maxLength < arr[i]) {
                maxLength = arr[i]
            }
        }

        maxLength = "$maxLength".length
        var n = 1;
        for (i in 0 until maxLength) {
            // 放数据
            for (j in arr.indices) {
                var digitlement = (arr[j] / n) % 10
                bucket[digitlement][bucketCount[digitlement]] = arr[j]
                bucketCount[digitlement]++

            }

            //取数据
            var index = 0
            for (k in bucket.indices) {
                if (bucketCount[k] != 0) {
                    for (j in 0 until bucketCount[k]) {
                        arr[index++] = bucket[k][j]

                    }
                }
                bucketCount[k] = 0
            }

            n *= 10
        }

    }

}