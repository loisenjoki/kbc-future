package loise.kbc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.fragment.InternationalNews;

/**
 * Created by v on 10-Feb-15.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<ProductsData> productsDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName;
        TextView textViewDescription;
        TextView textViewMoreData;
        TextView textViewPrice;
        ImageView imageViewIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewProductName = (TextView) itemView.findViewById(R.id.name);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.description);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.price);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imagev);
        }
    }

    public Adapter(ArrayList<ProductsData> products) {
        this.productsDataSet = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        view.setOnClickListener(InternationalNews.myOnClickListener);

        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText()

            }
        });

        TextView textViewProductName = holder.textViewProductName;
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewPrice = holder.textViewPrice;
        ImageView imageViewIcon = holder.imageViewIcon;

        textViewProductName.setText(productsDataSet.get(listPosition).getName());
        textViewDescription.setText(productsDataSet.get(listPosition).getDescription());

        textViewPrice.setText(productsDataSet.get(listPosition).getPrice());
        imageViewIcon.setImageResource(productsDataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return productsDataSet.size();
    }
}



