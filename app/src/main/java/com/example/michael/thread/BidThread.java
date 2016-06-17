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
public class BidThread extends Thread {
    private static final String TAG = "askbid";
    private static final int MIN_PRICE = 100;
    private static final int MAX_PRICE = 250;
    private static final int STEP_PRICE = 10;
    private int currentPrice = MIN_PRICE;
    private Handler handler;
    private AskThread askThread;

    @Override
    public void run() {
        Looper.prepare();
        // instantiate handler
        instantiateHandler();
        Looper.loop();
    }

    private void instantiateHandler() {
        if (getHandler() == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    handleAskMessage(msg);
                }
            };
        }
    }

    private void handleAskMessage(Message msg) {
        // parse ask price
        final int askPrice = msg.arg2;
        if (askPrice == currentPrice) {
            Log.i(TAG, "bid thread done at price = " + askPrice);

            bidPrice(askPrice, askPrice);
            return;
        }
//        Log.i(TAG, DateUtil.getDateLabel() + " bid thread handle ask price = " + askPrice);
        // compute bid price
        final int bidPrice = getBidPrice(askPrice);
        // reply bid
        bidPrice(askPrice, bidPrice);
    }

    private int getBidPrice(int askPrice) {
        // think over a while
        ThreadUtil.sleepWhile(2000);
        if (askPrice <= MAX_PRICE) {
            return askPrice;
        } else {
            final int bidPrice = increaseBidPrice(askPrice);
            return bidPrice;
        }
    }

    private int increaseBidPrice(int askPrice) {
        // increase price
        currentPrice = currentPrice + STEP_PRICE;
        if (currentPrice > askPrice) {
            currentPrice = askPrice;
        }
        // check price if exceed max price
        if (currentPrice > MAX_PRICE) {
            currentPrice = MAX_PRICE;
        }
        return currentPrice;
    }

    private void bidPrice(int askPrice, int bidPrice) {
        if (askThread != null) {
            final Handler askHandler = askThread.getHandler();
            if (askHandler != null) {
                // send bid message
                // what: 1
                // arg1: askPrice
                // arg2: bidPrice
                Log.i(TAG, DateUtil.getDateLabel() + " bid thread bid price = " + bidPrice);
                Log.i(TAG, "-------------------------------------------------------------------");
                final Message message = askHandler.obtainMessage(TradePrice.BID, askPrice, bidPrice);
                message.sendToTarget();
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setAskThread(AskThread askThread) {
        this.askThread = askThread;
    }
}
