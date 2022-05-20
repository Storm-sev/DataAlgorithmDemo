package com.storm.dataalgorithmdemo.utils;

import java.security.Key;

public class Custom {

    public static void adjustHeap(int[] array, int i, int length) {

        int temp = array[i];

        for (int k = 2 * i + 1; k < length; k = 2 * i + 1) {

            k++;
        }
    }
}
