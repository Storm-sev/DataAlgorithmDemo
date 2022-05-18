package com.storm.dataalgorithmdemo.utils

// 顺序二叉树(完全二叉树 )
class ArrBinaryThree {

    private var arr: IntArray? = null

    constructor(array: IntArray?) {
        this.arr = array
    }

    /**
     * @param index 数组下标
     */
    fun preOrder(index: Int) {
        if (arr == null || arr!!.isEmpty()) {
            System.out.println("当前数组为空 不能按照二叉树顺序遍历")
            return
        }
        System.out.println("当前数据 --> ${arr!![index]}")
        if ((index * 2 + 1) < arr!!.size) {
            preOrder(index * 2 + 1)
        }
        if ((index * 2 + 2) < arr!!.size) {
            preOrder(index * 2 + 2)
        }

    }

    /**
     * 顺序存储的 中序排列
     */
    fun inOrder(index: Int) {
        if (arr == null || arr!!.isEmpty()) {
            System.out.println("当前数组为空 不能按照二叉树顺序遍历")
            return
        }

        if ((index * 2 + 1) < arr!!.size) {
            inOrder(index * 2 + 1)

        }
        System.out.println("当前数据 --> ${arr!![index]}")


        if ((index * 2 + 2) < arr!!.size) {
            inOrder(index * 2 + 2)

        }
    }

    fun posOrder(index: Int) {
        if (arr == null || arr!!.isEmpty()) {
            System.out.println("当前数组为空 不能按照二叉树顺序遍历")
            return
        }

        if ((index * 2 + 1) < arr!!.size) {
            posOrder(index * 2 + 1)

        }

        if ((index * 2 + 2) < arr!!.size) {
            posOrder(index * 2 + 2)

        }
        System.out.println("当前数据 --> ${arr!![index]}")

    }


}