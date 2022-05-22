package com.storm.dataalgorithmdemo.utils;


import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Custom {

    public static final String TAG = "Custom";
    public static byte[] huffmanUnzip(byte[] bytes, HashMap<Byte, String> huffmanMap) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            boolean flag = i == bytes.length - 1;
            str.append(HuffmanCode.Companion.bitToBitString(bytes[i], !flag));

        }

        LogUtils.d(TAG, "解压之后的 数据 __. " + str);
        HashMap<String, Byte> map = new HashMap<>();

        //编码表key 和value 调换
        for (Map.Entry<Byte, String> byteStringEntry : huffmanMap.entrySet()) {
            map.put(byteStringEntry.getValue(), byteStringEntry.getKey());

        }

        ArrayList<Byte> list = new ArrayList<Byte>();


        for (int i = 0; i < str.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                if (i + count >= str.length()) {
                    LogUtils.d(TAG,"超出界限");
                }
                String key = str.substring(i, i + count);
                b = map.get(key);
                if (null == b) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);

            i += count;
        }

        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


}
