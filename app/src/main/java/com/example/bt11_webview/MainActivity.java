package com.example.bt11_webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private EditText edUrl;
    private Button btnSearch;
    private WebView webView;
    private ContentLoadingProgressBar progressBar;
    private String url = "https://www.youtube.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initWebView();
        initListener();
        loadHTTP();
    }

    private void loadHTTP() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://picture.vn/wp-content/uploads/2019/08/Ch%E1%BB%A5p-%E1%BA%A3nh-phong-c%C3%A1ch-h%C3%A0n-qu%E1%BB%91c-4.jpg");
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    Log.d("thinhvh", "run: ");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("thinhvh", "loadHTTP: " + e.getMessage());
                }
            }
        });
        thread.start();
    }

    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                edUrl.setText(url);

            }
        });
        webView.loadUrl(edUrl.getText().toString());
    }

    private void initListener() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(edUrl.getText().toString());
            }
        });
    }

    private void initUI() {
        edUrl = findViewById(R.id.ed_url);
        btnSearch = findViewById(R.id.btn_search);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress_loading);

        edUrl.setText(url);
    }
}
