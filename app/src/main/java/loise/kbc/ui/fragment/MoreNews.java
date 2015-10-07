package loise.kbc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import java.util.ArrayList;
import java.util.List;

import loise.kbc.adapter.CustomListViewAdapter;
import loise.kbc.adapter.Data;
import loise.kbc.adapter.ProductsData;
import loise.kbc.navigationviewpagerliveo.R;

public class MoreNews extends Fragment {

    private boolean mSearchCheck;
    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    private ShareActionProvider mShareActionProvider;

    ListView listView;
    List<ProductsData> rowItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_more_news, container, false);

        rowItems = new ArrayList<ProductsData>();
        for (int i = 0; i < Data.drawableArray.length; i++) {
            rowItems.add(new ProductsData(
                    Data.nameArray[i],
                    Data.descriptionArray[i],
                    Data.moredesc[i],
                    Data.priceArray[i],
                    Data.drawableArray[i],
                    Data.id_[i]

            ));
            listView = (ListView)v.findViewById(R.id.list);
            CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(),
                    R.layout.morenews_items, rowItems);
            listView.setAdapter(adapter);
           // listView.setOnItemClickListener(this);
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);



// 			Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_add);
        menu.findItem(R.id.menu_add).setVisible(true);
        //mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        menu.findItem(R.id.menu_search).setVisible(true);

        mSearchCheck = false;


    }
    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub


        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "www.kbc.co.ke";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


                break;

            case R.id.menu_search:
                mSearchCheck = true;

                break;
        }
        return true;
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck){
                // implement your search here
            }
            return false;
        }
    };
}