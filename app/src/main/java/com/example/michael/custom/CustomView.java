package com.example.michael.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.michael.androiddemo.R;

/**
 * Created by michael on 16-6-6.
 */
public class CustomView extends View {
    private static final String TAG = "customView";
    private boolean mShowImageText;

    public CustomView(Context context) {
        super(context);
        Log.i(TAG, "CustomView(Context context)");
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "CustomView(Context context, AttributeSet attrs)");
        final TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        final int length = R.styleable.CustomView.length;
        Log.i(TAG, "styleable customView length = " + length);
        try {
            setShowImageText(array.getBoolean(R.styleable.CustomView_showImageText, false));
            final int position = array.getInteger(R.styleable.CustomView_position, 0);
            final String text = array.getString(R.styleable.CustomView_text);
            Log.i(TAG, "showImageText = " + isShowImageText());
            Log.i(TAG, "position = " + position);
            Log.i(TAG, "text = " + text);
        } finally {
            array.recycle();
        }
    }

    /*
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    public boolean isShowImageText() {
        return mShowImageText;
    }

    public void setShowImageText(boolean showImageText) {
        this.mShowImageText = showImageText;
        invalidate();
        requestLayout();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Log.i(TAG, "onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i(TAG, "onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.i(TAG, "onDetachedFromWindow");
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);
    }

    @Override
    protected void onFinishInflate() {
        Log.i(TAG, "onFinishInflate");
        super.onFinishInflate();
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        Log.i(TAG, "onFocusChanged");
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        Log.i(TAG, "onVisibilityChanged");
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        Log.i(TAG, "onWindowVisibilityChanged");
        super.onWindowVisibilityChanged(visibility);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        Log.i(TAG, "onDrawForeground");
        super.onDrawForeground(canvas);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.i(TAG, "onWindowFocusChanged");
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }


}
