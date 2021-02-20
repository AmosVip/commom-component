package com.amos.study.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;


import com.amos.study.BuildConfig;
import com.amos.study.utlis.FSystemSettingHelper;
import com.amos.study.utlis.PreventFastClickHelper;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;


//import com.jindashi.yingstock.common.utils.DBUtils;


//import com.jindashi.yingstock.business.mine.LoginActivity;


public final class JSAppBridgeImpl implements JSAppBridge, Serializable {
    private static final String TAG = "JSAppBridgeImpl";
    private String mUserAgent;
    /**
     * 是否拦截返回键
     */
    boolean interceptOnBack;
    private transient Activity mContext;

    private transient WebView mWebView;

    private String mParams;

    private Activity activity;
    private static final String NATIVE_CACHE_PREFIX = "native_cache_prefix"; //用于h5调用源生的存储方法key中

    public JSAppBridgeImpl setWebView(WebView webView) {
        mWebView = webView;
        return this;
    }

    public JSAppBridgeImpl() {
        String version = "2.28.0";
        String device = "";
        String appType = "1";
        mUserAgent = String.format(" android superstock(%s,%s,%s) ", version, device, appType);
    }

    /**
     * js 判断应用是否有开启通知
     *
     * @param jsFun
     */
    @JavascriptInterface
    public void checkJurisdiction(String jsFun) {
        invokeJs(jsFun, String.valueOf(FSystemSettingHelper.isHasOpenNotification()));
    }

    /**
     * H5跳转到源生的通知设置页面
     */
    @JavascriptInterface
    public void goPushSetting() {
        if (PreventFastClickHelper.isFastClick()) {
            return;
        }
        FSystemSettingHelper.openNotificationSetting(mContext);
    }

    /**
     * 获取APP 的一些基础信息
     * getAppInfo
     * {
     * channel:''//渠道
     * os:'' 0安卓 1ios
     * version:''版本号
     * }
     *
     * @param jsFun
     */
    @JavascriptInterface
    public void getAppInfo(String jsFun) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("channel", "yingyongbao");
            jsonObject.put("os", "0");
            jsonObject.put("version","2.28.0");
            jsonObject.put("imei", "");
            jsonObject.put("androidid", "");
            jsonObject.put("oaid", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        invokeJs(jsFun, jsonObject);
    }

    /**
     * 2.17.0版本后 统一使用这个方法进行页面的跳转
     * 同推送逻辑保持一致
     */
    @JavascriptInterface
    public void commonJSCallBack(String params) {

    }


    //为h5提供的存储方法
    @JavascriptInterface
    public void storeNativeData(String params) {

    }

    //h5获取存储的值
    @JavascriptInterface
    public void getNativeData(String params) {

    }

    @JavascriptInterface
    public void openVideoList(String params) {

    }

    @JavascriptInterface
    public void openUserAccout() { //跳转到个人中心

    }

    @JavascriptInterface
    public void openMasterIndex(String params) { //跳转到大咖页


    }

    @Override
    @JavascriptInterface
    public void openGroupChat(String params) {


    }

    @Override
    @JavascriptInterface
    public void openPrivacyProtocal() { //跳转到隐私政策详情页

    }

    @Override
    @JavascriptInterface
    public void openImage(String[] allUrls, String currUrl) {

    }

    @Override
    @JavascriptInterface
    public void onBack(String init) {
        boolean bool = false;
        if (!TextUtils.isEmpty(init)) {
            try {
                JSONObject jsonObject = new JSONObject(init);
                bool = jsonObject.optBoolean("isInit");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bool) {
            interceptOnBack = bool;
        } else if (interceptOnBack) {
            mContext.finish();
        }
    }

    @Override
    public boolean canClosePage() {
        return !interceptOnBack;
    }

    @Override
    public void invokeJsOnBack() {

    }

    @Override
    @JavascriptInterface
    public String init() {

        return result(0, "succ", null);
    }

    @Override
    @JavascriptInterface
    public String shareImageTextLink(String paramStr) {

        return result(0, "succ", null);
    }

    @Override
    @JavascriptInterface
    public void getUkey(String jsFun) {

    }

    @Override
    @JavascriptInterface
    public String openWeb(String paramStr) {
        /*JsonObject jo = null;
        String ret = null;
        try {
            jo = new JsonParser().parse(paramStr).getAsJsonObject();
            String newUrl = jo.get("url").getAsString();
            String title = jo.get("title").getAsString();
            openWebView(title, newUrl);
            ret = result(0, "succ", null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = result(-1, e.getMessage(), null);
        }*/

        String ret = "";
        try {
            if (!TextUtils.isEmpty(paramStr)) {
                JSONObject jsonObject = new JSONObject(paramStr);
                if (jsonObject != null) {
                    String newUrl = jsonObject.optString("url");
                    String title = jsonObject.optString("title");
                    openWebView(title, newUrl);
                    ret = result(0, "succ", null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = result(-1, e.getMessage(), null);
        }
        return ret;
    }

    @Override
    @JavascriptInterface
    public String openLogin() {

        return result(0, "succ", null);
    }

    @Override
    @JavascriptInterface
    public String openStockSearch() {

        return result(0, "succ", null);
    }

    @Override
    public void onLoginRequest() {

    }

    @Override
    @JavascriptInterface
    public void share(String params) {

    }

    @Override
    @JavascriptInterface
    public void shareWXMiniProgram(String params, byte[] bytes) {

    }

    @Override
    @JavascriptInterface
    public void openWeChatMiniProgram(String params) {

    }

    @Override
    @JavascriptInterface
    public void shareData(String params) {

    }

    @Override
    @JavascriptInterface
    public String openVideoLive() {

        return result(0, "succ", null);
    }

    @Override
    @JavascriptInterface
    public void openStockDetail(String params) {

    }

    @Override
    @JavascriptInterface
    public void refreshSelfStock(String params) {

    }

    @Override
    @JavascriptInterface
    public void openStockPool(String params) {

    }

    @Override
    @JavascriptInterface
    public void openMoreHotNews(String params) {

    }

    @Override
    @JavascriptInterface
    public void openPdf(String params) {

    }

    /**
     * {
     * isFullscreen: Boolean // 是否全屏显示页面
     * }
     *
     * @param jsonParams
     */
    @Override
    @JavascriptInterface
    public void setFullscreen(String jsonParams) {

    }

    /**
     * {
     * title: '', // 右上角显示的文字
     * <p>
     * url: '' // 点击右上角文字跳转的地址
     * <p>
     * }
     *
     * @param jsonParams
     */
    @Override
    @JavascriptInterface
    public void setRightCorner(String jsonParams) {

    }

    /**
     * 打开一个新的页面
     * 只供本地使用 不支持 H5使用
     *
     * @param title
     * @param url
     */
    @Override
    @JavascriptInterface
    public void onOpenWebActivity(String title, String url) {
        openWebView(title, url);
    }

    /**
     * h5打开个股详情页信号弹窗
     *
     * @return
     */
    @Override
    @JavascriptInterface
    public void h5OpenSignDialog() {

    }

    @Override
    public void onNotificationRefresh() { //通知h5刷新的方法

    }

    @Override
    public String getParams() {
        return mParams;
    }

    @Override
    public void showH5FontSettingView() {

    }


    public String result(int code, String msg, JsonObject result) {
        JsonObject jo = new JsonObject();
        jo.addProperty("code", code);
        jo.addProperty("message", msg);
        jo.add("result", result);
        return jo.toString();
    }

    public JSAppBridgeImpl setContext(Activity mContext) {
        this.mContext = mContext;
        return this;
    }

    private void openWebView(String title, String url) {

    }

    public String getUserAgent() {
        return mUserAgent;
    }

    public void invokeJs(String jsFunction, JSONObject params) {
        if (mWebView == null) {

            return;
        }
        String js = jsFunction + "(" + params + ");";

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                //获取行情数据
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    mWebView.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {

                        }
                    });
                } else {
                    mWebView.loadUrl("javascript:" + js);
                }
            }
        });
    }

    public void invokeJs(String jsFunction, String params) {
        if (mWebView == null) {

            return;
        }
        String js = jsFunction + "(" + params + ");";

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                //获取行情数据
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    mWebView.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {

                        }
                    });
                } else {
                    mWebView.loadUrl("javascript:" + js);
                }
            }
        });
    }



}
