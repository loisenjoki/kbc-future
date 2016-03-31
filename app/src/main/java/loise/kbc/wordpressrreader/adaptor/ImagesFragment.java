package loise.kbc.wordpressrreader.adaptor;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;

import java.util.ArrayList;

import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.wordpressrreader.app.AppController;
import loise.kbc.wordpressrreader.app.MainActivity;
import loise.kbc.wordpressrreader.model.Category;
import loise.kbc.wordpressrreader.util.Config;
import loise.kbc.wordpressrreader.util.JsonParserNews;

/**
 * Created by homeboyz on 2/23/16.
 */
public class ImagesFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String TAG = "ImagesFragment";
    private ProgressDialog mProgressDialog;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private SearchView searchView;
    private MenuItem searchMenuItem;
    protected static ArrayList<Category> categories = null;
    private ImageRecordsAdapter.PostListListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_images, container, false);

        toolbar = (Toolbar)v.findViewById(R.id.toolbarwp);
//        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        // mAdapter = new ImageRecordsAdapter(getActivity());
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        // Preload 1 page to either side of the current page
        mViewPager.setOffscreenPageLimit(1);

        //listView = (ListView)v.findViewById(R.id.list1);
        //listView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCategories();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Log.d(TAG, "onCreateOptionsMenu()");

        Log.d(TAG, "onCreateOptionsMenu()");


        inflater.inflate(R.menu.menu_main, menu);

        // Create expandable & collapsible SearchView
        SearchManager searchManager = (SearchManager)
                getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false); // Expanded by default
        //searchView.requestFocus();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) getActivity());
        getActivity().invalidateOptionsMenu();

        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void resetActionBar() {
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        searchMenuItem.collapseActionView();
    }

    private void loadCategories() {
        // Display a progress dialog
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.loading_categories));
        // User cannot dismiss it by touching outside the dialog
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        // Make a request to get categories
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.CATEGORY_URL,
                null,
                new Response.Listener<JSONObject>() {
                    // Request succeeded
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        mProgressDialog.dismiss();

                        // Get categories from JSON data
                        categories = JsonParserNews.parseCategories(jsonObject);

                        ImagePageAdaptor adaptor = new
                                ImagePageAdaptor(getChildFragmentManager(), categories);
                        mViewPager.setAdapter(adaptor);
//                        mTabLayout.setupWithViewPager(mViewPager);
                    }
                },
                // Request failed
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d(TAG, "----- Volley Error -----");
                        mProgressDialog.dismiss();
                        // Show an INDEFINITE Snackbar. New in design support lib v22.2.1.
                        Snackbar.make(mViewPager, R.string.error_load_categories,
                                Snackbar.LENGTH_INDEFINITE).setAction(R.string.action_retry,

                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        loadCategories();
                                    }
                                }).show();
                    }
                });
        // Add the request to request queue
        AppController.getInstance().addToRequestQueue(request);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus(); // Hide soft keyboard
        mListener.onSearchSubmitted(query); // Deal with fragment transaction on MainActivity
        return false;
    }



    //  @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}



