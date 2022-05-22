package com.storm.dataalgorithmdemo.app

import android.app.Application
import com.blankj.utilcode.BuildConfig
import com.blankj.utilcode.util.LogUtils
import java.io.InputStream

class MApplication : Application() {
    companion object {
        var  mLogConfig : LogUtils.Config? = null

    }

    override fun onCreate() {
        super.onCreate()

        setLogConfig()
    }

    private fun setLogConfig() {

        mLogConfig = LogUtils.getConfig()
            .setLogSwitch(true)
            .setConsoleSwitch(BuildConfig.DEBUG)
            .setGlobalTag("storm_log")
            .setLog2FileSwitch(false)
            .setSingleTagSwitch(true)
            .setLogHeadSwitch(true)
            .setBorderSwitch(true)
            .setConsoleFilter(LogUtils.V)
            .setFileFilter(LogUtils.V)
    }


   public fun getAssert(): InputStream {

        return assets.open("yao.jpg")
    }

}
