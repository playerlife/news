package net.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import net.fqjj.www.MainActivity;
import net.fqjj.www.R;

/**
 * Created by joejoe on 2014/7/19.
 */
public class WebViewLayout extends RelativeLayout {


    public Animation getAnimNormal() {
        return animNormal;
    }

    public Animation getAnimLeft() {
        return animLeft;
    }

    public Animation getAnimRight() {
        return animRight;
    }

    /**
     * 右滑动画
     */
    public static Animation animRight;

    /**
     * 左滑动画
     */
    public static Animation animLeft;


    /**
     * 还原动画
     */
    public static Animation animNormal;


    public WebViewLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public WebViewLayout(final Context context, AttributeSet attrs) {

        super(context, attrs);

        animRight = AnimationUtils.loadAnimation(context, R.anim.fade_right);

        animLeft = AnimationUtils.loadAnimation(context, R.anim.fade_left);

        animRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animNormal = AnimationUtils.loadAnimation(context, R.anim.right_normal);

                int wid = WebViewLayout.this.getMeasuredWidth();
                int hei = WebViewLayout.this.getMeasuredHeight();
                WebViewLayout.this.clearAnimation();
                WebViewLayout.this.layout((int) (wid * 0.75f), (int) (hei * 0.0f), (int) (wid * 0.75f) + WebViewLayout.this.getMeasuredWidth(), (int) (hei * 0.0f) + WebViewLayout.this.getMeasuredHeight());
                WebViewLayout.this.setScaleX(0.8f);
                WebViewLayout.this.setScaleY(0.8f);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        animLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animNormal = AnimationUtils.loadAnimation(context, R.anim.left_normal);

                int wid = WebViewLayout.this.getMeasuredWidth();
                int hei = WebViewLayout.this.getMeasuredHeight();
                WebViewLayout.this.clearAnimation();
                WebViewLayout.this.layout((int) (wid * -0.75f), (int) (hei * 0.0f), (int) (wid * -0.75f) + WebViewLayout.this.getMeasuredWidth(), (int) (hei * 0.0f) + WebViewLayout.this.getMeasuredHeight());
                WebViewLayout.this.setScaleX(0.8f);
                WebViewLayout.this.setScaleY(0.8f);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    public WebViewLayout(Context context) {
        super(context);

    }


    /**默认不拦截 true 拦截 false不拦截*/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (MainActivity.isContentShow == false) {
            return true;
        } else {
            return false;
        }

    }

    /** 默认分发 true不处理 false交给上一级的onTouchEvent处理 */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        /***
         * 禁止下面左右侧导航
         */
        MainActivity.leftMenuLayout.setEnabled(false);
        MainActivity.rightMenuLayout.setEnabled(false);

        return super.dispatchTouchEvent(ev);
    }

    /**默认冒泡 true不冒泡 false 冒泡*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (MainActivity.isContentShow == false) {
            this.startAnimation(animNormal);
            MainActivity.isContentShow = true;
        } else {
            return true;
        }





        animNormal.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                MainActivity.leftMenuLayout.setEnabled(true);
                MainActivity.rightMenuLayout.setEnabled(true);


                int wid = WebViewLayout.this.getMeasuredWidth();
                int hei = WebViewLayout.this.getMeasuredHeight();
                WebViewLayout.this.clearAnimation();
                WebViewLayout.this.layout(0, 0, wid, hei);
                WebViewLayout.this.setScaleX(1.0f);
                WebViewLayout.this.setScaleY(1.0f);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        return super.onTouchEvent(event);
    }


}
