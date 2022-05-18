package com.storm.dataalgorithmdemo.utils

import java.lang.RuntimeException


class Stack(size: Int) {

    var top = -1
    private var stack: IntArray
    var maxSize = size


    init {
        stack = IntArray(maxSize)
    }

    public fun isFull() = top == maxSize - 1

    public fun isEmpty() = top == -1

    public fun addStack(value: Int){
        if (isFull()) {
            System.out.println("栈满")
            return
        }
        top++
        stack[top] = value
    }

    public fun pop(): Int{
        if (isEmpty()) {
            throw RuntimeException("站内没有数据....")
        }

        var value = stack[top]
        top--
        return value
    }

    public fun showStack() {
        if (isEmpty()) {
            System.out.println("栈内没有数据")
            return
        }
        for (temp in top downTo 0) {
            System.out.println("${stack[temp]}")

        }
    }
}