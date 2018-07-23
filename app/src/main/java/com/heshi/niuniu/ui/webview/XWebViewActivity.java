package com.heshi.niuniu.ui.webview;

import android.os.Bundle;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class XWebViewActivity extends BaseActivity<XWebViewPresent> implements XWebViewContract.Model {

    @BindView(R.id.web_view_company)
    WebView webViewCompany;
    private String[] urlStr = {"file:///android_asset/vue/dist/index.html#/message/grap",
    };

    public static int SNATCH_PACK = 0;
    public static int SEND_PACK = 1;
    public static final String TYPE = "type";
    private int type;


    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xweb_view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        type = getIntent().getIntExtra(TYPE, -1);
        String url=getUrl(type);
        initWebView(url);
    }

    private void initWebView(String url) {
        //        WebView webView = (WebView) findViewById(R.id.webView);
//        webViewCompany.requestFocus();
        WebSettings settings = webViewCompany.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDomStorageEnabled(true);

        webViewCompany.loadUrl(url);


//        webViewCompany.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (url.startsWith("tel:")) {
//                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
//                    startActivity(intent);
//                    return true;
//                } else if (url.startsWith("http://") && (url.substring(url.length() - 4, url.length()).contains("."))) {
//                    Uri uri = Uri.parse(url);
//                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(it);
//                } else {
//                    view.loadUrl(url);
//                }
//                return false;
//            }
//        });
//        webViewCompany.addJavascriptInterface(new JavascriptInterface(mContext), "imagelistner");

    }

    private String getUrl(int type) {
        if (type != -1) {
            return urlStr[type];
        }
        return "";
    }

}
