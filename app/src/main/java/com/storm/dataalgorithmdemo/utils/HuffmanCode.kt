package com.storm.dataalgorithmdemo.utils

import android.animation.TypeEvaluator
import android.content.ContentUris
import android.content.Context
import android.nfc.Tag
import androidx.annotation.IntegerRes
import com.blankj.utilcode.util.LogUtils
import com.storm.dataalgorithmdemo.app.MApplication
import io.reactivex.rxjava3.core.Maybe
import java.io.*
import java.lang.Exception
import kotlin.math.log


/**
 * 赫夫曼编码
 */
class HuffmanCode {

    //内容 字符串
    var content: ByteArray
    var nodes: ArrayList<Node>

    constructor(content: ByteArray) {
        this.content = content
        nodes = createNodes(this.content)
    }

    /**
     * 构建 结点集合 根据字符 以及字符出现的次数构建一个结点
     */
    private fun createNodes(content: ByteArray): ArrayList<Node> {
        var list = arrayListOf<Node>()
        // 创建键值对来处理 字符与之相对应出现的个数
        LogUtils.d(TAG, "${content.size}")

        var countMap = hashMapOf<Byte, Int>()
        for (byte in content) {
            //当前map 中存入byte 的个数
            var count: Int? = countMap[byte]
            if (null == count) { // 未存入
                countMap[byte] = 1
            } else { // 存入时在原有的基础上+ 1
                countMap[byte] = count + 1
            }
        }

        for (mutableEntry in countMap) {
            list.add(Node(mutableEntry.key, mutableEntry.value))
        }

        list.sort() // 排序 从小到大
        return list
    }


    /**
     * 使用huffman对list 进行树化
     */
    public fun huffManCodeTree(): Node {
        nodes.sort()
        while (nodes.size > 1) {

            // 获取前两个
            var left = nodes.get(0)
            var right = nodes.get(1)
            var parent = Node(null, left.weight + right.weight)
            parent.left = left
            parent.right = right
            // 删除 left right 结点
            nodes.remove(left); nodes.remove(right)
            nodes.add(parent)

            nodes.sort()
        }
        return nodes[0]
    }

    /**
     * 前序遍历
     */
    public fun preOrder(root: Node?) {
        root?.let {
            it.preOrder()
        }
    }

    //静态内部类
    class Node(var data: Byte?, var weight: Int) :

        Comparable<Node> {
        var left: Node? = null
        var right: Node? = null
        override fun compareTo(other: Node): Int {
            return this.weight - other.weight

        }

        override fun toString(): String {
            return "Node(data=$data, weight=$weight)"
        }


        // 前序遍历
        fun preOrder() {
            println(this)
            this.left?.preOrder()
            this.right?.preOrder()

        }

    }


    companion object {
        // 创建huffman 编码表  某个字符对应的节点路径
        val huffmanCode = hashMapOf<Byte, String>()

        val TAG = "huffman CODE"

        // 生成的路径
        public var path = StringBuilder()


        fun unzipFile(source: File?, desFile: File?) {
            if (null == source || null == desFile) {
                return
            }
            var fis: InputStream? = null
            var ois: ObjectInputStream? = null
            var os: FileOutputStream? = null

            try {
                fis = FileInputStream(source)
                //获取文件的
                ois = ObjectInputStream(fis)
                val byteArray = ois.readObject() as ByteArray
                val huffmanMap = ois.readObject() as HashMap<Byte, String>


                var bytes : ByteArray= huffmanUnzip(byteArray, huffmanMap)
                os = FileOutputStream(desFile)
                os.write(bytes)


                LogUtils.d("storm", "已经完成")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                os?.close()
                ois?.close()
                fis?.close()
            }
        }

        /**
         * 进行文件压缩
         */
        fun zipFile(source: File?, desFile: File) {
//            if (null == source) {
//                return
//
//            }
            // 1 将文件转化为bytes
            var fis: FileInputStream? = null
            var os: FileOutputStream? = null
            var oos: ObjectOutputStream? = null

            try {
                fis = FileInputStream(source)
                // 获取文件的长度
                var bytes = ByteArray(fis.available())
                fis.read(bytes)
                // 对文件进行压缩
                var zipBytes = huffmanZip(bytes)
                // 创建文件写入流
                os = FileOutputStream(desFile)
                //对象输出流
                oos = ObjectOutputStream(os)
                // 写入压缩文件
                oos.writeObject(zipBytes)
                // 写入huffman表
                oos.writeObject(huffmanCode)

            } catch (e: Exception) {

            } finally {
                oos?.close()
                os?.close()
                fis?.close()
            }


        }

        /**
         * 数据解压
         */
        fun huffmanUnzip(bytes: ByteArray, huffmanCode: HashMap<Byte, String>): ByteArray {
            // 1. 先把bytes 数组转换成 string

            var str = StringBuilder()
            for (i in bytes.indices) {
                var flag = i == bytes.size - 1
                str.append(bitToBitString(bytes[i], !flag))
            }
            // 然后
            println(str)

            // 将索引编码进行key value 调换

            var map = hashMapOf<String, Byte>()
            // 字符对照表反向输出
            for (mutableEntry in huffmanCode) {
                map[mutableEntry.value] = mutableEntry.key
            }

            // 查找
            var count = 1
            var list = arrayListOf<Byte>()

            var i = 0
            while (i < str.length) {
                var b: Byte? = null
                count = 1;
                var flag = true
                while (flag) {

                    var key: String? = null

                    key = str.substring(i, i + count)

                    b = map[key]
                    if (null == b) {
                        // 如果没有获取到值
                        count +=1
                    } else {
                        // 获取到了
                        flag = false
                    }
                }
                // 存入 list 中
                list.add(b!!)
                i += count
            }


            var sourceArray: ByteArray = list.toByteArray()

            println(String(sourceArray))
            return sourceArray

        }


        fun bitToBitString(b: Byte, flag: Boolean): String? {
            var num = b.toInt()
            if (flag) {
                num = num or 256
            }
            val str = Integer.toBinaryString(num)
            return if (flag) {
                str.substring(str.length - 8)
            } else {
                str
            }
        }


        //封装 压缩数据
        fun huffmanZip(bytes: ByteArray): ByteArray {
            // 1 创建
            var huffmanCode = HuffmanCode(bytes)
            val root = huffmanCode.huffManCodeTree()
            var codeMap = createHuffmanCode(root)
            return zip(bytes, codeMap)

        }

        /**
         * 1. 要将字符或者数据转化为 100这种路径拼接的码
         * \字符串编码表
         */
        fun zip(bytes: ByteArray, huffmanCode: HashMap<Byte, String>): ByteArray {
            // 创建字符串拼接
            var str = StringBuilder()

            // 根据相应的字符或者数据 获取其结点路径 拼接 最后形成的该数据所对应的huffman 数据

            for (byte in bytes) {
                str.append(huffmanCode[byte])
            }
            LogUtils.d(TAG,"压缩之前 --> str")

            // 转成byte 数组 8位
            // len = str.length + 7 % 8
            var len: Long
            if (str.length % 8 == 0) {
                len = (str.length / 8).toLong()
            } else {
                len = (str.length / 8 + 1).toLong()
            }

            //创建一个byte 数组
            var zipBytes = ByteArray(len.toInt())
            var index = 0

            var i = 0
            while (i < str.length) {

                var strByte: String? = null

                if ((i + 8) > str.length) {
                    strByte = str.substring(i)
                } else {
                    strByte = str.substring(i, i + 8)
                }
                // 八位转成byte

                // 反码  radix 表示的是进制数 转成二进制
                val intByte: Int = Integer.parseInt(strByte,2)
                var toByte: Byte = intByte.toByte()

                zipBytes[index] = toByte
                index++
                i += 8
            }

            return zipBytes
        }


        /**
         * 创建huffman编码表
         */
        fun createHuffmanCode(root: Node?): HashMap<Byte, String> {
            root?.let {
                createHuffmanCode(root.left, "0", path)
                createHuffmanCode(root.right, "1", path)
            }
            return huffmanCode
        }

        /**
         * 创建赫夫曼编码 根据路径指向叶子节点 path拼接之后就是 当前字符或者数据的一个编码方式
         */
        fun createHuffmanCode(node: Node?, code: String, path: StringBuilder) {
            var path2 = StringBuilder(path) // 递归拼接的路径
            path2.append(code) // 添加这一路径
            node?.let {
                if (it.data == null) { // 非叶子结点 继续向下递归
                    createHuffmanCode(it.left, "0", path2)
                    createHuffmanCode(it.right, "1", path2)
                } else { //叶子节点 说明已经到了后面不需要递归了
                    huffmanCode[it.data!!] = path2.toString()
                }
            }
        }
    }
}



