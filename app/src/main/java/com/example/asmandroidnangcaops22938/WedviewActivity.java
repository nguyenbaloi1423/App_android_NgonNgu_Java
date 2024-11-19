package com.example.asmandroidnangcaops22938;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WedviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedview);

        WebView webView = findViewById(R.id.wedView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();

        String link = intent.getStringExtra("linkNews");

        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}