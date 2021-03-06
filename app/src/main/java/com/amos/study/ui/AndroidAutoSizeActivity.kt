package com.amos.study.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.amos.study.base.BaseActivity
import com.amos.study.databinding.ActivityAndroidAutoSizeBinding
import com.amos.study.widget.ObservableWebView

/**
 * @author: amos
 * @date: 2021/2/20 16:12
 * @description:
 */
class AndroidAutoSizeActivity : BaseActivity() {
    private val url: String = "https://hd.get88.cn/xiguatest/xieyi/PrivacyProtocal.html"
    private lateinit var mViewBinding: ActivityAndroidAutoSizeBinding


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AndroidAutoSizeActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_android_auto_size)
        mViewBinding = ActivityAndroidAutoSizeBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)

        initWebView()
        var jsBridge = JSAppBridgeImpl()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mViewBinding.autoWebView.addJavascriptInterface(
                jsBridge,
                JSAppBridge.JS_OBJ_NAME
            )
        } else {
            mViewBinding.autoWebView.removeJavascriptInterface("searchBoxJavaBridge_")
        }
        val preUA: String = mViewBinding.autoWebView.settings.userAgentString
        mViewBinding.autoWebView.settings.userAgentString = preUA + jsBridge.userAgent
        mViewBinding.autoWebView.loadUrl(url)

        mViewBinding.tvRightBtn.setOnClickListener {
            MyDialogFragment().show(supportFragmentManager, MyDialogFragment.TAG)
        }
    }

    private fun initWebView() {
        val webSettings: WebSettings = mViewBinding.autoWebView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = false
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.useWideViewPort = true
        webSettings.textSize = WebSettings.TextSize.NORMAL
        mViewBinding.autoWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY)
        // 内容调试支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //autoWebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        // 跨域安全支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowFileAccess = false
            webSettings.allowFileAccessFromFileURLs = false
            webSettings.allowUniversalAccessFromFileURLs = false
        }
        // 混合加载支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        // 媒体自动播放支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.mediaPlaybackRequiresUserGesture = false
        }
        mViewBinding.autoWebView.setWebChromeClient(object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        })
        mViewBinding.autoWebView.setWebViewClient(object :
            ObservableWebView.ObservableWebViewClient(mViewBinding.autoWebView) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // 如果要求页面加载完毕之后可用 则设置按钮状态
                /*if (getActivity() != null && confirmEnableWhenPageLoaded) {
                    confirm.setEnabled(true);
                }*/
            }

            override fun onPageStarted(
                view: WebView?,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler,
                error: SslError?
            ) {
                handler.proceed()
            }
        })
    }


}