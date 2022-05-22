package com.storm.dataalgorithmdemo

import android.Manifest
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ZipUtils
import com.storm.dataalgorithmdemo.databinding.ActivityMainBinding
import com.storm.dataalgorithmdemo.utils.HuffmanCode
import com.tbruyelle.rxpermissions3.Permission
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.functions.Consumer
import java.io.File
import java.util.zip.ZipFile
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()

        requestPermission()
    }

    val rxPermission: RxPermissions by lazy {
        val rxPermissions = RxPermissions(this)
        rxPermissions
    }

    private fun requestPermission() {

        rxPermission.request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe { granted ->

            if (granted) {

                Thread {

                    startZip()
                }.start()


            } else {

            }

        }

    }

    private val rootFolderPath =
        Environment.getExternalStorageDirectory().absolutePath + File.separator + "stormzhang"

    private fun startZip() {

        var rootFile = File(rootFolderPath)
        if (!rootFile.exists()) {
            rootFile.mkdirs()
        }
        var file = File(rootFile.absolutePath + File.separator + "yao.zip")
        var source = File(rootFile.absolutePath + File.separator + "yao.txt")

        HuffmanCode.zipFile(source, file)
        LogUtils.d(TAG, "压缩完成 ... ")
        var outFile = File(rootFile.absolutePath + File.separator + "yao.zip")
        var outSource = File(rootFile.absolutePath + File.separator + "res.txt")

        HuffmanCode.unzipFile(outFile,outSource )



    }
}