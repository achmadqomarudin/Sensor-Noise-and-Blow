package com.pondokit.achmad.sensornoise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Achmad SkyDev on 5/8/19.
 */
class SoundLevelView extends View {

    private Drawable mGreen;
    private Drawable mRed;
    private Paint mBackgroundPaint;

    private int mHeight;
    private int mWidth;

    private int mThreshold = 0;
    private int mVol = 0;


    public SoundLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mGreen  = context.getResources().getDrawable(
                android.R.drawable.alert_light_frame/*R.drawable.green*/);
        mRed    = context.getResources().getDrawable(
                android.R.drawable.alert_dark_frame/*R.drawable.red*/);

        mWidth  = mGreen.getIntrinsicWidth();
        setMinimumWidth(mWidth*10);

        mHeight = mGreen.getIntrinsicHeight();
        setMinimumHeight(mHeight);

        //Used to paint canvas background color
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.BLACK);

    }

    public void setLevel(int volume, int threshold) {
        if (volume == mVol && threshold == mThreshold) return;
        mVol = volume;
        mThreshold = threshold;

        // invalidate Call onDraw method and draw voice points
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawPaint(mBackgroundPaint);

        for (int i=0; i<= mVol; i++) {
            Drawable bar;
            if (i< mThreshold) {

                bar = mGreen;
            } else {

                bar = mRed;
            }
            bar.setBounds((10-i)*mWidth, 0, (10-i+1)*mWidth, mHeight);
            bar.draw(canvas);
        }
    }
}
