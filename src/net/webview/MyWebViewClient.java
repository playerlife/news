package net.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by joejoe on 2014/7/19.
 */
public class MyWebViewClient extends WebViewClient {




    public MyWebViewClient(WebView webView, Context context) {

        initwebViewSetting(webView, context);
    }


    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }


    public void initwebViewSetting(WebView webView, Context context) {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(20);
        webSettings.setAppCacheEnabled(true);
        webView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(0);
        webSettings.setAppCachePath(String.valueOf(Environment.getExternalStorageDirectory()));
        String path = context.getFilesDir().getPath();
        webSettings.setAppCachePath(path + "app_ui");
    }

}
