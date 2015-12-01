package loise.kbc.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import loise.kbc.navigationviewpagerliveo.R;

public class IftiinRadio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iftiin_radio);

        final WebView webview = (WebView)findViewById(R.id.webView4);
        //http://iframe.dacast.com/b/37017/c/79023

        String Url = "http://iframe.dacast.com/b/37017/c/79360";
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

    }
}
