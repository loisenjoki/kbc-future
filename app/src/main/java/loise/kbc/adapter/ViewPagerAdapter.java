package loise.kbc.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

import loise.kbc.navigationviewpagerliveo.R;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
   /* private int[] imageResId = {
            R.mipmap.news,
            R.mipmap.tv,
            R.mipmap.listen
    };*/
    private List<TabPagerItem> mTabs;
    public ViewPagerAdapter(FragmentManager fragmentManager, List<TabPagerItem> tabs) {
        super(fragmentManager);
        this.mTabs = tabs;
    }

    public void setDatasource(List<TabPagerItem> datasource){
        mTabs = datasource;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return mTabs.get(i).getFragment();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

       /* Drawable image = ContextCompat.getDrawable((Context) mTabs, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;*/

        return mTabs.get(position).getTitle();
    }
}