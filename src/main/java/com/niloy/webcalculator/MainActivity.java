package com.niloy.webcalculator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.webView=(WebView)findViewById(R.id.webview1);

        webView.getSettings().setJavaScriptEnabled(true); //ENABLING JAVASCRIPT in WEBVIEW
        this.webView.addJavascriptInterface(new WebAppClass(this,webView),"Andro"); //LINKING WITH JAVASCRIPT with Mecroname Andro
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.webView.loadUrl("file:///android_asset/index.html");
    }

}
