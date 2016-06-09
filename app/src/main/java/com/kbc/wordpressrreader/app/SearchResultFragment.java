package com.kbc.wordpressrreader.app;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.kbc.navigationviewpagerliveo.R;
import com.kbc.wordpressrreader.adaptor.ImageRecordsAdapter;


/**
 * Fragment to show search result
 */
public class SearchResultFragment extends Fragment {
    private String mQuery;

    private SearchResultListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment
     * using the provided parameters to display search result.
     *
     * @param query search query.
     * @return A new instance of SearchResultFragment.
     */
    public static SearchResultFragment newInstance(String query) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString("query", query);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Need to enable Options Menu in order to listen for home event
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mQuery = getArguments().getString("query", "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_result, container, false);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbarwp);
    //    ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(getString(R.string.search_result) + " \"" + mQuery + "\"");

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        RecyclerViewFragment fragment = RecyclerViewFragment.newInstance(mQuery);
        ImageRecordsAdapter fragment1 = ImageRecordsAdapter.newInstance(mQuery);
        ft.add(R.id.search_container, fragment);
        ft.add(R.id.search_container, fragment1);
        ft.addToBackStack(null);
        ft.commit();

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mListener.onHomePressed();
        }
        return true;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (SearchResultListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement SearchResultListener");
        }
    }

    // Interface used to communicate with MainActivity
    public interface SearchResultListener {
        void onHomePressed();
    }
}
