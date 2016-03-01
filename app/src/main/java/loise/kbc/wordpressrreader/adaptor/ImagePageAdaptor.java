package loise.kbc.wordpressrreader.adaptor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import loise.kbc.wordpressrreader.model.Category;

/**
 * Created by homeboyz on 2/23/16.
 */


    public class ImagePageAdaptor extends FragmentPagerAdapter {

    private ArrayList<Category> categories;

    public ImagePageAdaptor(FragmentManager fm, ArrayList<Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageRecordsAdapter.newInstance(categories.get(position).getId());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }

    @Override
    public int getCount() {
        return categories.size();
    }
}
