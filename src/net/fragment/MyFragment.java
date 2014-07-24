package net.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import net.fqjj.www.R;
import net.http.NetworkConnect;
import net.webview.MyWebChromeClient;
import net.webview.MyWebViewClient;


public class MyFragment extends Fragment  {


    private String url;
    private WebView webView;


    public MyFragment(String url) {

        this.url = url;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        RelativeLayout layout = (RelativeLayout) getActivity()
                .getLayoutInflater().inflate(R.layout.fragment_main, null);





        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        /**
         * 如果网络不可用 则不加载webview 并显示错误页面
         */
        if(!NetworkConnect.isNetworkAvaible(getActivity())) {
            view.findViewById(R.id.error_image).setVisibility(View.VISIBLE);
            return;
        }



        webView = (WebView) view.findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebViewClient(webView, getActivity()));
        webView.setWebChromeClient(new MyWebChromeClient(webView,getActivity()));
        webView.loadUrl(url);


    }



}
