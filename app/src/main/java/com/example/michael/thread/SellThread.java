package com.example.michael.thread;

import android.os.Looper;

/**
 * Created by michael on 16-6-15.
 */
public class SellThread extends Thread {
    @Override
    public void run() {
        Looper.prepare();




        Looper.loop();




    }
}
