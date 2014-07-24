package net.fqjj.www;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;


import net.adapter.NavAdapter;

import net.astuetz.PagerSlidingTabStrip;
import net.fragment.MyFragment;
import net.http.NetworkConnect;
import net.widget.WebViewLayout;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {


    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;

    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;


    /**
     * 左侧层
     */
    public static RelativeLayout leftMenuLayout;

    /**
     * 右侧层
     */
    public static RelativeLayout rightMenuLayout;

    /**
     * 内容层
     */
    RelativeLayout content;

    /**
     * 是否内容已隐藏
     */
    public static boolean isContentShow = true;

    /**
     * 左侧导航列表
     */
    ListView navListView;



    /**
     * @param savedInstanceState
     * <p/>
     * WebView
     */

    ViewPager viewPager;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();

        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabs.setViewPager(pager);
        setTabsValue();




    }





    public void checkNetwork(View v ) {
        NetworkConnect.autoCheckNetwork(MainActivity.this);
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void findViews() {

        leftMenuLayout = (RelativeLayout) findViewById(R.id.left_action);
        rightMenuLayout = (RelativeLayout) findViewById(R.id.right_action);
        content = (RelativeLayout) findViewById(R.id.content);
        viewPager = (ViewPager) findViewById(R.id.pager);


        leftMenuLayout.setEnabled(false);
        rightMenuLayout.setEnabled(false);

        ImageButton leftButton = (ImageButton) findViewById(R.id.leftButton);
        ImageButton rightButton = (ImageButton) findViewById(R.id.rightButton);
        leftButton.setOnClickListener(listener);
        rightButton.setOnClickListener(listener);


        /***
         * 左侧网站导航
         */
        navListView = (ListView) findViewById(R.id.nav_list_view);
        navListView.setAdapter(new NavAdapter(this));

        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.leftButton) {

                leftMenuLayout.setVisibility(View.VISIBLE);
                rightMenuLayout.setVisibility(View.INVISIBLE);
                leftMenuLayout.setEnabled(true);

                content.startAnimation(WebViewLayout.animRight);
                isContentShow = false;
                return;
            }
            if (v.getId() == R.id.rightButton) {

                rightMenuLayout.setVisibility(View.VISIBLE);
                leftMenuLayout.setVisibility(View.INVISIBLE);
                rightMenuLayout.setEnabled(true);
                content.startAnimation(WebViewLayout.animLeft);
                isContentShow = false;
                return;

            }
        }
    };

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(false);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#45c01a"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = {"运动健身", "娱乐", "图片", "财经"};
        private final String[] urls = {
                "http://3g.163.com/sports/",
                "http://3g.163.com/ent/",
                "http://3g.163.com/pic/special/003403CR/photoindex.html",
                "http://3g.163.com/money/"
        };


        ArrayList<MyFragment> list = new ArrayList<MyFragment>();


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {

            for ( int i = 0; i < titles.length; i++) {
                list.add(null);
            }


            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {


            if (list.get(position) != null) {
                return list.get(position);
            }

            MyFragment fragment =  new MyFragment(urls[position]);
            list.add(position, fragment);

            return fragment;

        }
    }


    /**
     * 点击回退的处理
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_BACK:
                exitApp();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("是否退出应用?")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       MainActivity.this.finish();
                    }
                })
                .setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}