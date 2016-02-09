package loise.kbc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import loise.kbc.navigationviewpagerliveo.R;


/**
 * Created by loise on 10/12/15.
 */
public class Business extends Fragment {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.business, container, false);

        CircularProgressBar circularProgressBar = (CircularProgressBar)v.findViewById(R.id.progress);
        circularProgressBar.setColor(getResources().getColor(R.color.primary_dark_material_dark));
        circularProgressBar.setBackgroundColor(getResources().getColor(R.color.nliveo_white));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progress));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.progress));
        int animationDuration = 2000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(100, animationDuration); // Default duration = 1500ms

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(getActivity(), loise.kbc.wordpressrreader.app.MainActivity.class));

                // close this activity
                //  finish();
            }
        }, SPLASH_TIME_OUT);

        return v;
    }
}
