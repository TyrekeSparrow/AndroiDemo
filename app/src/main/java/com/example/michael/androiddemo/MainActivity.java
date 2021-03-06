package com.example.michael.androiddemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.example.michael.thread.AskThread;
import com.example.michael.thread.BidThread;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
//    private static final String TAG = "testSurfaceTexture";
    private static final String TAG = "customView";
    private Surface mSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        TextureView textureView = (TextureView) findViewById(R.id.image_texture);
        if (textureView != null) {
            textureView.setSurfaceTextureListener(this);
        }
    }

    public void setImageBitmap(View view) {
        Log.i(TAG, "setImageBitmap");
        if (mSurface != null) {
            final Canvas canvas = mSurface.lockCanvas(null);
            // draw bitmap
//            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thumbnail1);
//            canvas.clipRect(0, 0, 360, 640);
//            canvas.drawBitmap(bitmap, new Matrix(), new Paint());
//            canvas.drawBitmap(bitmap, null, new Rect(0, 0, 360, 640), new Paint());
            final Paint linePaint = new Paint();
            linePaint.setColor(Color.WHITE);
            canvas.drawLine(100, 200, 360, 340, linePaint);
            final Paint rectPaint = new Paint();
            rectPaint.setStyle(Paint.Style.FILL);
            rectPaint.setColor(Color.MAGENTA);
            rectPaint.setARGB(0Xff, 0X0f, 0Xff, 0Xf0);
            canvas.drawRect(20, 30, 200, 300, rectPaint);
            canvas.save();
            mSurface.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Log.i(TAG, "onSurfaceTextureAvailable");
        mSurface = new Surface(surfaceTexture);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.i(TAG, "onSurfaceTextureDestroyed");
        // TODO
        return true;
    }

    // ====================================================================================
    // will be never invoked
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        Log.i(TAG, "onSurfaceTextureSizeChanged");
        // do nothing
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.i(TAG, "onSurfaceTextureUpdated");
        // do nothing
    }
    // ====================================================================================


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "activity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


    public void beginTrading(View view) {
        trade();

    }

    private void trade() {
        // ask bid
        // communicate by handler
        // handler instance in run method
        // handler construct by looper
        // looper get in thread

        // instantiate sell thread
        // instantiate buy thread
        // set communicate handler
        // when receive a message
        // think over a little time reply message

        // start ask thread
        final AskThread askThread = new AskThread();
        askThread.start();
        // start bid thread
        final BidThread bidThread = new BidThread();
        bidThread.start();
        // set reply thread
        askThread.setBidThread(bidThread);
        bidThread.setAskThread(askThread);









    }
}
