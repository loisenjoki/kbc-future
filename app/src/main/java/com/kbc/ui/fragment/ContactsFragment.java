
package com.kbc.ui.fragment;


import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kbc.ui.activity.InstagramFeed;
import com.kbc.ui.activity.TimelineKbc;
import com.kbc.navigationviewpagerliveo.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
  

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_contacts, container, false);

        /*v.findViewById(R.id.menu_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimelineKbc.class));

            }
        });
        v.findViewById(R.id.menu_item2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("https://www.facebook.com/kbcchannel1/"));
                startActivity(intent1);
            }
        });
        v.findViewById(R.id.menu_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/kbckenya");
                Intent Instagram = new Intent(Intent.ACTION_VIEW, uri);

                Instagram.setPackage("com.instagram.android");

                try {
                    startActivity(Instagram);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/kbckenya")));
                }
               // startActivity(new Intent(getActivity(),InstagramFeed.class));
            }
        });*/
        return v;


    }


}
