
package loise.kbc.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import loise.kbc.ui.activity.InstagramFeed;
import loise.kbc.ui.activity.TimelineKbc;
import loise.kbc.navigationviewpagerliveo.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends android.support.v4.app.Fragment {
  

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_contacts, container, false);
      /* fab1=(FloatingActionMenu)v.findViewById(R.id.menu_item);
        fab1.setOnMenuButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactsFragment.this, NewsFragment.class));
            }
        });*/
        v.findViewById(R.id.menu_item).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(getActivity(),InstagramFeed.class));
            }
        });
        return v;


    }


}
