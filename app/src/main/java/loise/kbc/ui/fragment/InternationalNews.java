package loise.kbc.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import loise.kbc.adapter.Adapter;
import loise.kbc.adapter.Data;
import loise.kbc.adapter.ProductsData;
import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.activity.MoreNews;

/**
 * Created by loise on 9/28/15.
 */
public class InternationalNews extends Fragment {

    private boolean mSearchCheck;
    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static InternationalNews newInstance(String text){
        InternationalNews mFragment = new InternationalNews();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);

        //Set the Onclick Listener
       myOnClickListener = new MyOnclickListener(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
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

        return v;
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

            Toast.makeText(context,"removed", Toast.LENGTH_SHORT).show();
            Intent intent;
            intent = new Intent(context, MoreNews.class);
            context.startActivity(intent);
        }
    }
    }



