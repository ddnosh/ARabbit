package com.github.ddnosh.arabbit.ui.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.ui.base.QuickActivity
import com.github.ddnosh.arabbit.util.StringUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class WebViewActivity : QuickActivity() {

    private lateinit var wvWebView: WebView
    private lateinit var pbWebView: ProgressBar

    protected val WEB_URL_KEY: String = "ARABBIT_WEB_URL"

    override val contentViewLayoutID = R.layout.activity_webview

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        wvWebView = findViewById(R.id.wvWebView)
        pbWebView = findViewById(R.id.pbWebView)
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    fun initData() {
        var url = intent.extras?.getString(WEB_URL_KEY)
        if (StringUtil.isEmpty(url)) {
            url = URL
        }

        val webSettings = wvWebView.settings
        webSettings.javaScriptEnabled = true

        wvWebView.requestFocus()

        // 设置setWebChromeClient对象
        wvWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
//                toolbar.title = title
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pbWebView.progress = newProgress
            }
        }

        wvWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wvWebView.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                toolbar.title = StringUtil.getTrimedString(wvWebView.url)
                pbWebView.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
//                toolbar.title = StringUtil.getTrimedString(wvWebView.title)
                pbWebView.visibility = View.GONE
            }
        }

        wvWebView.loadUrl(url)
    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    override fun onBackPressed() {
        if (wvWebView.canGoBack()) {
            wvWebView.goBack()
            return
        }

        super.onBackPressed()
    }

    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    override fun onPause() {
        super.onPause()
        wvWebView.onPause()
    }

    override fun onResume() {
        wvWebView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        wvWebView.destroy()
        super.onDestroy()
    }

    companion object {

        const val URL = "https://github.com/ddnosh"
    }
}
