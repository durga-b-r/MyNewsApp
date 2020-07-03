package com.example.blogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetailsActivity extends AppCompatActivity {
      WebView newsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        newsWebView=findViewById(R.id.newsweb);
        String URL = getIntent().getStringExtra("URL");
        newsWebView.loadUrl(URL);

    }
}