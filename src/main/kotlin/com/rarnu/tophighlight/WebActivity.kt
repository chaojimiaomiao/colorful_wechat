package com.rarnu.tophighlight

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView

class WebActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val myWebView = findViewById(R.id.webview) as WebView
        myWebView.loadUrl("http://www.jianshu.com/p/6fa82e3cfe00")
    }
}
