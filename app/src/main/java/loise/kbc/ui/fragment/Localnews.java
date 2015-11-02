package loise.kbc.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import loise.kbc.adapter.Adapter;
import loise.kbc.adapter.Data;
import loise.kbc.adapter.ProductsData;
import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.activity.News;


/**
 * Created by loise on 10/7/15.
 */
public class Localnews extends Fragment {

    private boolean mSearchCheck;
    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    private ShareActionProvider mShareActionProvider;

    public static Localnews newInstance(String text){
        Localnews mFragment = new Localnews();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    //  declaration of recycle view design
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ProductsData> products;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.news_fragment, container, false);
        //Set the Onclick Listener
        myOnClickListener = new MyOnclickListener(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        products = new ArrayList<ProductsData>();
        //Loop around the array of products
        for (int i = 0; i < Data.drawableArray.length; i++) {

            products.add(new ProductsData(
                    Data.nameArray[i],
                    Data.descriptionArray[i],
                    Data.moredesc[i],
                    Data.priceArray[i],
                    Data.drawableArray[i],
                    Data.id_[i]

            ));

        }
        removedItems = new ArrayList<Integer>();
        adapter = new Adapter(products);
        recyclerView.setAdapter(adapter);

        final MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout)rootView.findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
        materialRefreshLayout.setShowArrow(true);
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
        materialRefreshLayout.finishRefreshLoadMore();

        return rootView;
    }

    private static class MyOnclickListener implements View.OnClickListener{

        private final Context context;
        private MyOnclickListener(Context context){
            this.context = context;
            // Toast.makeText(context, "mdcvmx vf", Toast.LENGTH_SHORT).show();


        }




        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewProductName = (TextView) viewHolder.itemView.findViewById(R.id.name);
            String selectedName = (String) textViewProductName.getText();
            int selectedItemId = -1;

            for (int i = 0; i < Data.nameArray.length; i++) {
                if (selectedName.equals(Data.nameArray[i])) {
                    selectedItemId = Data.id_[i];
                }
            }
//            removedItems.add(selectedItemId);
//            products.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);

            Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show();
            Intent intent;
            intent = new Intent(context, News.class);
            context.startActivity(intent);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
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

