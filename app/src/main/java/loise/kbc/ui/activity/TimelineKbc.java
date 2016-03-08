
package loise.kbc.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import loise.kbc.navigationviewpagerliveo.R;

public class TimelineKbc extends Fragment {
  //  private static final String TWITTER_KEY = "CtXW8KLLGFfQTqWlrdoK7oUbR";
   // private static final String TWITTER_SECRET = "xq0mHEOlOpBVfr1uyvehFF1dt05YIAPxCHav0diBfr99QuBXrL";
    private Toolbar toolbar;
     private ProgressDialog barprogress;
    private ListView listview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.activity_timeline_kbc, container, false);


        barprogress = ProgressDialog.show(getActivity(), "", "Loading.Please wait...", true);

        listview = (ListView)v. findViewById(R.id.list);
        final UserTimeline userTimeline = new UserTimeline.Builder().screenName("KBCChannel1").build();
        TweetTimelineListAdapter adapter;
        adapter = new TweetTimelineListAdapter(getActivity(), userTimeline);
        adapter = new TweetTimelineListAdapter.Builder(getActivity()).setTimeline(userTimeline).build();

        listview.setAdapter(adapter);
        barprogress.dismiss();
        /*userTimeline.previous(null, new Callback<TimelineResult<Tweet>>() {

            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
            }

            @Override
            public void failure(TwitterException exception) {
            }
        });*/
       /* MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
            }
        });
        materialRefreshLayout.finishRefresh();
        materialRefreshLayout.finishRefreshLoadMore();*/
        return v;
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_timeline_kbc, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

