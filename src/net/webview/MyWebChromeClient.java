package net.webview;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


/**
 * Created by joejoe on 2014/7/19.
 */
public class MyWebChromeClient extends WebChromeClient {

    private ProgressDialog dialog;
    private WebView webView;
    private Context context;

    public MyWebChromeClient(WebView webView, Context context) {


        this.webView = webView;
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setMax(100);
        dialog.setMessage("加载中 请稍等...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        //  dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

        webViewGoBack(webView);

        forbiddenLongPress(webView);
    }



    /**
     * 屏蔽掉长按事件 因为webview长按时将会调用系统的复制控件
     * @param webView
     */
    private void forbiddenLongPress(WebView webView) {
        webView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    /**
     * webview回退操作
     *
     * @param webview
     */
    private void webViewGoBack(final WebView webview) {

        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {  //表示按返回键
                        webview.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });


    }


    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        dialog.setProgress(newProgress);
        if (newProgress == 100)
            dialog.dismiss();

    }
}
