package com.amos.study.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.amos.study.BuildConfig
import com.amos.study.R
import com.amos.study.databinding.DialogAndroidAutoSizeBinding
import com.amos.study.widget.ObservableWebView

/**
 * @author: amos
 * @date: 2021/2/20 18:07
 * @description:
 */

class MyDialogFragment : DialogFragment() {
    private val url: String = "https://hd.get88.cn/xiguatest/xieyi/PrivacyProtocal.html"
    private lateinit var mContext: Context
    private lateinit var mViewBinding: DialogAndroidAutoSizeBinding

    companion object {
        val TAG: String = MyDialogFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }

    //private lateinit var mRootView: View;
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        return MyDialog(mContext, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //mRootView = inflater.inflate(R.layout.dialog_android_auto_size, container, false)
        mViewBinding = DialogAndroidAutoSizeBinding.inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        var jsBridge = JSAppBridgeImpl()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mViewBinding.webview.addJavascriptInterface(
                jsBridge,
                JSAppBridge.JS_OBJ_NAME
            )
        } else {
            mViewBinding.webview.removeJavascriptInterface("searchBoxJavaBridge_")
        }
        val preUA: String = mViewBinding.webview.getSettings().getUserAgentString()
        mViewBinding.webview.getSettings()
            .setUserAgentString(preUA + jsBridge.getUserAgent())
        mViewBinding.webview.loadUrl(url)
    }


    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        return super.show(transaction, tag)
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        super.showNow(manager, tag)
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }


    private fun initWebView() {
        val webSettings: WebSettings = mViewBinding.webview.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = false
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.useWideViewPort = true
        webSettings.textSize = WebSettings.TextSize.NORMAL
        mViewBinding.webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY)
        // 内容调试支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
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
        mViewBinding.webview.setWebChromeClient(object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        })
        mViewBinding.webview.setWebViewClient(object :
            ObservableWebView.ObservableWebViewClient(mViewBinding.webview) {
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


    class MyDialog(context: Context, theme: Int = 0) : AppCompatDialog(context, theme) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            window?.let { it ->
                it.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                it.setGravity(Gravity.CENTER)
                it.decorView?.let { view ->
                    view.setPadding(0, 0, 0, 0)
                }
            }
        }

        override fun setContentView(layoutResID: Int) {
            super.setContentView(layoutResID)
        }
    }

}