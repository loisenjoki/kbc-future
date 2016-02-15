package loise.kbc.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


import java.lang.reflect.InvocationTargetException;

import loise.kbc.navigationviewpagerliveo.R;

/**
 * Created by loise on 9/28/15.
 */
public class TvFragment extends Fragment {
    String TAG = "com.ebookfrenzy.videoplayer";
    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static TvFragment newInstance(String text){
        TvFragment mFragment = new TvFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }
    WebView video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_video, container, false);

        video = (WebView)v.findViewById(R.id.videoView1);


        video.getSettings().setJavaScriptEnabled(true);
//video.getSettings().setPluginState(WebSettings.PluginState.ON);
//video.getSettings().setUserAgent(USER_MOBILE);
        video.setWebChromeClient(new WebChromeClient() {
        });

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = getHTML("0sFTC7l0okg"); //Only change this video id to change video!
        video.loadDataWithBaseURL("", html, mimeType, encoding, "");

            super.onPause();



        return v;



    }

    public String getHTML(String videoId) {


        String html =
                "<iframe class=\"youtube-player\" "
                        + "style=\"border: 0; width: 100%; height: 100%;"
                        + "padding:0px; margin:5px\" "
                        + "src=\"http://iframe.dacast.com/b/57052/c/75409"
                        + "frameborder=\"0\" " + "allowfullscreen autobuffer "
                        + "controls onclick=\"this.play()\">\n" + "</iframe>\n";
        return html;
    }
    @Override
    public void onPause() {
        super.onPause();

        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(video, (Object[]) null);

        } catch(ClassNotFoundException cnfe) {

        } catch(NoSuchMethodException nsme) {

        } catch(InvocationTargetException ite) {

        } catch (IllegalAccessException iae) {

        }
    }


}
