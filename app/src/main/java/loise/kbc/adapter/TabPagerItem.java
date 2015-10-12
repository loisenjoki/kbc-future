package loise.kbc.adapter;

import android.support.v4.app.Fragment;

public class TabPagerItem {

    private final CharSequence mTitle;
    private final Fragment mFragment;
    private int icon;

    public TabPagerItem(CharSequence title, Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public int setIcon(int icon) {
        this.icon = icon;
        return icon;
    }
}