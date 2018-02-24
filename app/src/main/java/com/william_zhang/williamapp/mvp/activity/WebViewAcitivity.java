package com.william_zhang.williamapp.mvp.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.william_zhang.base.mvp.baseImpl.BaseActivity;
import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.mvp.contact.WebViewContact;
import com.william_zhang.williamapp.mvp.presenter.WebViewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by william_zhang on 2018/2/24.
 */

public class WebViewAcitivity extends BaseActivity<WebViewContact.presenter> implements WebViewContact.view {
    @OnClick(R.id.submit)
    void submit() {
        webview.loadUrl("javascript:Test.execute(\"Test.test();\")");
        webview.evaluateJavascript("javascript:Test.execute(\"Test.test();\")", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                //js返回结果
            }
        });
    }

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        presenter.init();
    }

    @Override
    public WebViewContact.presenter initPresenter() {
        return new WebViewPresenter(this);
    }

    @Override
    public void newStateChange(int state) {

    }

    @Override
    public void init() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new AndroidJs(), "androidjs");
        webview.loadUrl("file:///android_asset/webview.html");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //设定开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //设定加载结束的操作
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //每个资源图片的加载都会调用
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载服务异常的时候调用
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }

        });
        //5.1以上默认http和https不混合处理
        //开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //newProgress 当前的加载进度
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //获取网页中的标题
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
                //支持js中的警告框，自己定义弹出框 返回true
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
                //支持js中的确认框，自己定义弹出框 返回true
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
                //支持js中的输入框，自己定义弹出框 返回true
            }
        });
    }

    class AndroidJs extends Object {
        @JavascriptInterface
        public void print(String test) {
            Toast.makeText(WebViewAcitivity.this, test, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        if(webview!=null){
            webview.destroy();
        }
        super.onDestroy();
    }
}
