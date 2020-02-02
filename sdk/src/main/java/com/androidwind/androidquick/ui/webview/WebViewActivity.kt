package com.androidwind.androidquick.ui.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import com.androidwind.androidquick.R
import com.androidwind.androidquick.constant.Constant
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter
import com.androidwind.androidquick.ui.base.QuickActivity
import com.androidwind.androidquick.util.StringUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class WebViewActivity : QuickActivity() {

    private var wvWebView: WebView? = null
    private var pbWebView: ProgressBar? = null

    private var mBundle: Bundle? = null

    override val contentViewLayoutID = R.layout.activity_webview

    override val overridePendingTransitionMode: TransitionMode = TransitionMode.LEFT

    override val isLoadDefaultTitleBar: Boolean = true

    override val isBindEventBus: Boolean = false

    override fun onEventComing(eventCenter: EventCenter<*>) {
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        wvWebView = findViewById(R.id.wvWebView)
        pbWebView = findViewById(R.id.pbWebView)
        initData()
    }

    override fun toggleOverridePendingTransition(): Boolean {
        return false
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    fun initData() {
        var url = mBundle?.getString(Constant.QUICKBASE_WEB_URL)
        if (StringUtil.isEmpty(url)) {
            url = URL
        }

        val webSettings = wvWebView?.settings
        webSettings?.javaScriptEnabled = true

        wvWebView?.requestFocus()

        // 设置setWebChromeClient对象
        wvWebView?.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                toolbar.title = title
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pbWebView!!.progress = newProgress
            }
        }

        wvWebView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wvWebView?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                toolbar.title = StringUtil.getTrimedString(wvWebView!!.url)
                pbWebView!!.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                toolbar.title = StringUtil.getTrimedString(wvWebView!!.title)
                pbWebView!!.visibility = View.GONE
            }
        }

        wvWebView?.loadUrl(url)
    }

    override fun getBundleExtras(extras: Bundle) {
        mBundle = extras
    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    override fun onBackPressed() {
        if (wvWebView!!.canGoBack()) {
            wvWebView?.goBack()
            return
        }

        super.onBackPressed()
    }

    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    override fun onPause() {
        super.onPause()
        wvWebView?.onPause()
    }

    override fun onResume() {
        wvWebView?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        if (wvWebView != null) {
            wvWebView?.destroy()
            wvWebView = null
        }
        super.onDestroy()
    }

    companion object {

        const val URL = "https://github.com/ddnosh"
    }
}
