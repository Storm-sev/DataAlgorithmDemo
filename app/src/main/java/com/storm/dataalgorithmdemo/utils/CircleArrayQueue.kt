package com.storm.dataalgorithmdemo.utils

import kotlin.math.max


class CircleArrayQueue(size: Int) {
    companion object {
        val TAG = "CircleArrayQueue"
    }


    var maxSize: Int = size;
    var front: Int = 0;
    var near: Int = 0;
    var queue: IntArray


    init {
        front = 0
        near = 0
        queue = IntArray(maxSize)

    }

    /**
     * 队列是否已经满了
     */
    public fun isFull(): Boolean = (near + 1 )% maxSize == front

    public  fun  isEmpty(): Boolean = near == front


    /**
     * 添加到队列
     */
    public fun addQueue(number: Int) {
        if (isFull()) {
            System.out.println("当前队列已满 .....")
            return
        }
        queue[near] = number
        near = (near + 1) % maxSize

    }

    /**
     * 队列获取数据
     */
    public fun getQueue(): Int {
        if (isEmpty()) {

            System.out.println("当前队列内没有数据")
            throw  Exception("no data of queue")

        }

        var temp = queue[front]
        front = (front + 1) % maxSize
        return temp

    }

    /**
     * 显示队列内所有的有效数据
     */
    public fun showQueue() {
        if (isEmpty()) {
            System.out.println("没有数据")
            return
        }


        for (i in front until  (front + size())) {
            System.out.println("    ${queue[(i % maxSize)]}    ")
        }
    }

    // 队列内有多少个数据
    public fun size() = (near + maxSize - front) % maxSize


}