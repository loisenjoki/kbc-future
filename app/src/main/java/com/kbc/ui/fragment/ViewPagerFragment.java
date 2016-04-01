
package com.kbc.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.kbc.adapter.TabPagerItem;
import com.kbc.adapter.ViewPagerAdapter;
import com.kbc.navigationviewpagerliveo.R;


public class ViewPagerFragment extends Fragment {
    private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem() {
        mTabs.add(new TabPagerItem(getString(R.string.inter), InternationalNews.newInstance(getString(R.string.inter))));
        mTabs.add(new TabPagerItem(getString(R.string.local), NewsFragment.newInstance(getString(R.string.local))));
        mTabs.add(new TabPagerItem(getString(R.string.business), MainFragment.newInstance(getString(R.string.business))));
        mTabs.add(new TabPagerItem(getString(R.string.sport), MainFragment.newInstance(getString(R.string.sport))));
        mTabs.add(new TabPagerItem(getString(R.string.tech), MainFragment.newInstance(getString(R.string.tech))));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mViewPager.setOffscreenPageLimit(mTabs.size());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));
        final TabLayout mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mSlidingTabLayout.setElevation(10);
        }

        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSlidingTabLayout.setupWithViewPager(mViewPager);
            }
        }, 1);
    }
}
