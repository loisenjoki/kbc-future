package com.kbc.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kbc.navigationviewpagerliveo.R;

import java.lang.reflect.InvocationTargetException;


public class IftiinRadio extends Fragment {
    WebView webview;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=inflater.inflate(R.layout.activity_iftiin_radio, container, false);

      webview = (WebView)v.findViewById(R.id.webView4);
        //http://iframe.dacast.com/b/37017/c/79023

        String Url = "http://iframe.dacast.com/b/57052/c/79189";
        //style=\"color:black;font-family:Helvetica;font-size:12px;\"

        String vid = "<html>" +
                "<body style=\"margin: 0; padding: 0; font-size:20px;\">" +
                "<iframe width=\"100%\" height=\"100%\" src=\""+Url+"\" type=\"text/html\" frameborder=\"0\" scrolling=\"yes\">" +
                "</iframe>" +
                "</body>" +
                "</html>";
        WebChromeClient mWebChromeClient = new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress) {
            }

            //<iframe src="http://iframe.dacast.com/b/37017/c/78898" scrolling="no" frameborder="0" height="100" width="180"></iframe>
        };
        webview.setWebChromeClient(mWebChromeClient);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                webview.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.setInitialScale(1);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadData(vid, "text/html", "UTF-8");

        super.onPause();

        return v;

    }

    public void onPause() {
        super.onPause();

        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webview, (Object[]) null);

        } catch (ClassNotFoundException cnfe) {

        } catch (NoSuchMethodException nsme) {

        } catch (InvocationTargetException ite) {

        } catch (IllegalAccessException iae) {

        }
    }
}
