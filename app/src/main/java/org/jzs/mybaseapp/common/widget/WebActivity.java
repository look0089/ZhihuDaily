package org.jzs.mybaseapp.common.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jzs.mybaseapp.common.base.BaseActivity;
import org.jzs.mybaseapp.common.utils.AppLog;
import org.jzs.mybaseapp.common.utils.CommonUtils;
import org.jzs.zhihudaily.R;


/**
 * 通用web页面
 */

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends BaseActivity {
    TextView tv_title;
    WebView webView;

    public String mHtmlUrl, mTitle;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_web);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        initView();
        Intent intent = getIntent();
        mHtmlUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("name");
        tv_title.setText(mTitle);
        // requestData(ApiConfig.getImageUrl(htmlUrl));
        requestData(mHtmlUrl);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.title_name);
        webView = (WebView) findViewById(R.id.webView);
    }

    /**
     * 请求网络数据
     */
    @SuppressWarnings("deprecation")
    private void requestData(String url) {

        WebSettings webSettings = webView.getSettings();

        webSettings.setRenderPriority(RenderPriority.HIGH);
        if (CommonUtils.isNetConnectionAvailable(this)) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);// 根据cache-control决定是否从网络上取数据。
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
        }
        webView.setWebChromeClient(new MyChromeClient());
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        // 开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "/yigong/webcache";
        // String cacheDirPath =
        // getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        // 设置数据库缓存路径
        webSettings.setDatabasePath(cacheDirPath);
        // 设置 Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
        // 开启 Application Caches 功能
        webSettings.setAppCacheEnabled(true);

        webSettings.setBuiltInZoomControls(false); // 放大缩放按钮
        // 如果访问的页面中有JavaScript，则WebView必须设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        // js = new JSKit(this);
        // webView.addJavascriptInterface(js, "myjs");
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        // 自适应屏幕
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setLoadWithOverviewMode(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                AppLog.redLog("AdDetails", "onPageStarted-----------");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                AppLog.redLog("AdDetails", "onPageFinished-----------");

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                AppLog.redLog("AdDetails", "onReceivedError-----------");

            }

        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent();
        intent1.putExtra("position", getIntent().getIntExtra("position", -1));
        setResult(RESULT_OK, intent1);
        super.onBackPressed();
    }

    // 设置加载进度
    public class MyChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);

            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    }
}
