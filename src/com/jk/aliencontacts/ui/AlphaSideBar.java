package com.jk.aliencontacts.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class AlphaSideBar extends View {

    public static String[] mAlpha = { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    public static int TEXT_SIZE = 20;

    private OnTouchingLetterChangedListener mListener;
    private Paint mPaint = new Paint();
    private int mChosen = -1;
    private boolean ifShowBkg = false;

    public AlphaSideBar(Context context) {
        super(context);
    }

    public AlphaSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (ifShowBkg) {
            canvas.drawColor(Color.parseColor("#40000000"));
        }

        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / mAlpha.length;
        for (int i = 0; i < mAlpha.length; i++) {
            mPaint.setColor(0xFF404040);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize((float) (singleHeight*0.7));
            float xPos = width / 2 - mPaint.measureText(mAlpha[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(mAlpha[i], xPos, yPos, mPaint);
            mPaint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int oldChosen = mChosen;
        int choose = (int) (event.getY() / getHeight() * mAlpha.length);

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (oldChosen != choose && mListener != null) {
                    if (choose > 0 && choose < mAlpha.length) {
                        mListener.onTouchingLetterChanged(mAlpha[choose]);
                        mChosen = choose;
                        invalidate();
                    }
                }
            case MotionEvent.ACTION_DOWN:
                ifShowBkg = true;
                break;

            case MotionEvent.ACTION_UP:
                ifShowBkg = false;
                mChosen = -1;
                invalidate();
                break;

            default:
                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener) {
        mListener = listener;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String chosenLetter);
    }
}
