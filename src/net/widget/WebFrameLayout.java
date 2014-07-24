package net.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by joejoe on 2014/7/22.
 */
public class WebFrameLayout extends FrameLayout implements View.OnTouchListener {

    public WebFrameLayout(Context context) {
        super(context);
    }

    public WebFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    //    View v = getChildAt(1);
      //  v.setOnTouchListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("aaa", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("aaa", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("aaa", "ACTION_UP");
                break;


        }
        return false;
    }
}
