package com.example.michael.util;

/**
 * Created by michael on 16-6-16.
 */
public class ThreadUtil {
    public static void sleepWhile(long interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
