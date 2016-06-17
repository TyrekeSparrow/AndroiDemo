package com.example.michael.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.michael.TradePrice;
import com.example.michael.util.DateUtil;
import com.example.michael.util.ThreadUtil;

/**
 * Created by michael on 16-6-15.
 */
public class AskThread extends Thread {
    private static final String TAG = "askbid";
    // ask from 300 to 200
    private static final int MAX_PRICE = 300;
    private static final int MIN_PRICE = 150;
    private static final int STEP_PRICE = 15;
    private int currentPrice = MAX_PRICE;
    private Handler handler;
    private BidThread bidThread;


    @Override
    public void run() {
        // Log.i(TAG, "before ask thread prepare");
        Looper.prepare();
        // Log.i(TAG, "after ask thread prepare");

        instantiateHandler();


        // ask price
        askMaxPrice();
        Log.i(TAG, "before ask looper looper = " + Looper.myLooper());
        Looper.loop();
        Log.i(TAG, "after ask looper looper = " + Looper.myLooper());
    }

    private void dumpCurrentThreadInfo() {
        // thread id, thread name
        final Thread thread = Thread.currentThread();
        final long  id = thread.getId();
        final String name = thread.getName();
        Log.i(TAG, "thread id = " + id);
        Log.i(TAG, "thread name = " + name);





    }

    private void instantiateHandler() {
        // instantiate handler
        if (getHandler() == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    handleBidMessage(msg);
                }
            };
        }
    }

    private void handleBidMessage(Message msg) {
        // parse bid msg
        final int bidPrice = msg.arg2;
        if (bidPrice == currentPrice) {
            askPrice(bidPrice, bidPrice);
            Log.i(TAG, "ask thread done at price = " + bidPrice);
            return;
        }
//        Log.i(TAG, DateUtil.getDateLabel() + " --------- ask thread handle bid price = " + bidPrice);
        // compute ask price
        final int askPrice = getAskPrice(bidPrice);
        // reply ask price
        askPrice(bidPrice, askPrice);
    }

    private int getAskPrice(int bidPrice) {
        // think over a while
        ThreadUtil.sleepWhile(2000);
        // if bid >= min, return bid
        // if bid < min, try to decrease current price
        // if current price < min, current price = min
        // return current price
        if (bidPrice < MIN_PRICE) {
            return decreaseAskPrice(bidPrice);
        } else {
            return bidPrice;
        }
    }


    private void askMaxPrice() {
        // think over a while
        ThreadUtil.sleepWhile(3000);
        // ask max price
        askPrice(-1, currentPrice);

    }

    private void askPrice(int bidPrice, int askPrice) {
        // reply ask price
        if (bidThread != null) {
            final Handler bidHandler = bidThread.getHandler();
            if (bidHandler != null) {
                // what: 0
                // arg1: bidPrice
                // arg2: askPrice
                Log.i(TAG, DateUtil.getDateLabel() + " --------- ask thread ask price = " + askPrice);
                Log.i(TAG, "-------------------------------------------------------------------");
                final Message message = bidHandler.obtainMessage(TradePrice.ASK, bidPrice, askPrice);
                message.sendToTarget();
            }
        }
    }


    public Handler getHandler() {
        return handler;
    }

    public void setBidThread(BidThread bidThread) {
        this.bidThread = bidThread;
    }

    private int decreaseAskPrice(int bidPrice) {
        // decrease current price
        currentPrice = currentPrice - STEP_PRICE;
        if (currentPrice < bidPrice) {
            currentPrice = bidPrice;
        }
        // check current price
        if (currentPrice < MIN_PRICE) {
            currentPrice = MIN_PRICE;
        }
        return currentPrice;
    }
}
