

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import loise.kbc.adapter.CustomGrid;
import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.activity.CoroRadio;
import loise.kbc.ui.activity.EnglishServiceRadio;
import loise.kbc.ui.activity.IftiinRadio;
import loise.kbc.ui.activity.LiveTv;
import loise.kbc.ui.activity.MainActivityRadio;
import loise.kbc.ui.activity.PwaniRadio;
import loise.kbc.ui.activity.RadioTaifa;

/**
 * Created by loise on 9/28/15.
 */
public class RadioFragment extends Fragment {
    private boolean mSearchCheck;
    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static RadioFragment newInstance(String text){
        RadioFragment mFragment = new RadioFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    GridView grid;
    String[] web = {
            "Radio Taifa fm",
            "English service",
            "Coro Fm",
            "Iftiin",
            "Pwani",
            "Mayienga ",

    } ;
    int[] imageId = {
            R.drawable.radiotaifa,
            R.drawable.english,
            R.drawable.corologo,
            R.drawable.iftiin,
            R.drawable.pwani,
            R.drawable.mayiegalogo,

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.radio_fragment, container, false);

        CustomGrid adapter = new CustomGrid(getActivity(), web, imageId);
        grid=(GridView)rootView.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

                switch (position){

                    case 0:
                        startActivity(new Intent(getActivity(), RadioTaifa.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), EnglishServiceRadio.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), CoroRadio.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), IftiinRadio.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), PwaniRadio.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), LiveTv.class));
                        break;

                }
            }
        });



        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
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

        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

        menu.findItem(R.id.menu_add).setVisible(true);
        menu.findItem(R.id.menu_search).setVisible(true);

        mSearchCheck = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {

            case R.id.menu_add:

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

