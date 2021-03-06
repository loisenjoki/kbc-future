package com.kbc.adapter;

import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kbc.navigationviewpagerliveo.R;
import com.kbc.ui.activity.MainActivity;
import com.kbc.model.Post;

public class PostFragmetntAll extends AppCompatActivity {

    private static final String TAG = "PostFragment";

    private int id;
    private String title;
    private String content;
    private String url;
    private String featuredImageUrl;

    private WebView webView;

    private ImageView featuredImageView;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;

    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;

    private PostListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post);

        toolbar = (Toolbar) findViewById(R.id.toolbarwp);
        //((OldMainActivity)getActivity()).setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        // The following two layouts are needed to expand the collapsed Toolbar
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarLayout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        featuredImageView = (ImageView) findViewById(R.id.featuredImage);

        // Create the WebView
        webView = (WebView) findViewById(R.id.webview_post);


//        setUIArguments(Bundle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Bundle args = getIntent().getExtras();

        if (args != null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    // Clear the content first
                    webView.loadData("", "text/html; charset=UTF-8", null);
                    featuredImageView.setImageBitmap(null);

                    id = args.getInt("id");
                    title = args.getString("title");
                    String date = args.getString("date");
                    String author = args.getString("author");
                    content = args.getString("content");
                    url = args.getString("url");
                    featuredImageUrl = args.getString("featuredImage");
                    // Download featured image
                    Glide.with(PostFragmetntAll.this)
                            .load(featuredImageUrl)
                            .centerCrop()
                            .into(featuredImageView);

                    // Construct HTML content
                    // First, some CSS
                    String html = "<style>img{max-width:100%;height:auto;} " +
                            "iframe{width:100%;}</style> ";
                    // Article Title
                    html += "<h2>" + title + "</h2> ";
                    // Date & author
                    html += "<h4>" + date + " " + author + "</h4>";
                    // The actual content
                    html += content;

                    // Enable JavaScript in order to be able to Play Youtube videos
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebChromeClient(new WebChromeClient());

                    // Load and display HTML content
                    // Use "charset=UTF-8" to support non-English language
                    webView.loadData(html, "text/html; charset=UTF-8", null);

                    Log.d(TAG, "Showing post, ID: " + id);
                    Log.d(TAG, "Featured Image: " + featuredImageUrl);

                    // Reset Actionbar
                    //   mListener.getFragmentManager();
                    //     mListener.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    // Expand the Toolbar by default
                    expandToolbar();

                    // Make sure the article starts from the very top
                    // Delayed coz it can take some time for WebView to load HTML content
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.smoothScrollTo(0, 0);
                        }
                    }, 500);
                }
            });
        }
    }

    public void setUIArguments(final Bundle args) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu()");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_post, menu);

        // Get share menu item
        MenuItem item = menu.findItem(R.id.action_share);
        // Initialise ShareActionProvider
        // Use MenuItemCompat.getActionProvider(item) since we are using AppCompat support library
        ShareActionProvider shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        // Share the article URL
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, title + "\n" + url);
        shareActionProvider.setShareIntent(i);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.action_comments:
                mListener.onCommentSelected(id);
                return true;*/
            case R.id.action_share:
                return true;
            case R.id.action_send_to_wear:
                sendToWear();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Expand a collapsed Toolbar
     * See <a href="http://stackoverflow.com/questions/30655939/">Stack Overflow</a> for more info
     */
    private void expandToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();

        if (behavior != null) {
            behavior.setTopAndBottomOffset(0);
            behavior.onNestedPreScroll(coordinatorLayout, appBarLayout, null, 0, 1, new int[2]);
        }
    }

    /**
     * Send a BigTextStyle notification with text contents of the post to Android Wear devices
     */
    private void sendToWear() {
        // Intent used to run app on the phone from watch
        Intent viewIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, viewIntent, 0);

        // Use BigTextStyle to read long notification
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //bigTextStyle.setBigContentTitle(title);
        // Use Html.fromHtml() to remove HTML tags
        bigTextStyle.bigText(Html.fromHtml(content));

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setStyle(bigTextStyle);

        // Load featured image as background image
        Glide.with(this)
                .load(featuredImageUrl)
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>(360, 360) {
                    @Override
                    public void onResourceReady(Bitmap resource,
                                                GlideAnimation<? super Bitmap> glideAnimation) {
                        builder.setLargeIcon(resource);

                        NotificationManagerCompat notificationManagerCompat =
                                NotificationManagerCompat.from(getApplicationContext());
                        notificationManagerCompat.cancel(id);
                        notificationManagerCompat.notify(id, builder.build());
                    }
                });
    }

    // @Override
   /* public void onAttach(Activity activity) {
        mListener.onAttach(activity);
        try {
            mListener = (PostFragmetntAll.PostListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PostListener");
        }
    }*/


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface PostListener extends ImageRecordsAdapter.PostListListener {
        // void onCommentSelected(int id);
        void onHomePressed();

        @Override
        void onPostSelected(Post post, boolean isSearch);

        @Override
        void getFragmentManager(Toolbar toolbar);
    }


}
