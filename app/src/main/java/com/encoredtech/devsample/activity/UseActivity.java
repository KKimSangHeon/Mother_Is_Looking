package com.encoredtech.devsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.encoredtech.devsample.R;
import com.encoredtech.devsample.listener.DetailWebViewListener;

/**
 * Created by 상헌 on 2016-03-25.
 */
public class UseActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_use);



        WebView webView1 = (WebView) findViewById(R.id.todaymain);
        webView1.getSettings().setAllowContentAccess(true);
        webView1.getSettings().setBlockNetworkLoads(false);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new DetailWebViewListener("ui:h:todaymain:v1"));
        webView1.loadUrl("file:///android_asset/index.html");



        WebView webView2 = (WebView) findViewById(R.id.cardView);
        webView2.getSettings().setAllowContentAccess(true);
        webView2.getSettings().setBlockNetworkLoads(false);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebViewClient(new DetailWebViewListener("ui:h:todaydetail:v1"));

        webView2.loadUrl("file:///android_asset/index.html");



    }

}
