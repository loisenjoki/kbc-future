package com.kbc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kbc.navigationviewpagerliveo.R;
import com.kbc.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homeboyz on 2/23/16.
 */
public class ImageRecord extends RecyclerView.Adapter<ImageRecord.ViewHolder> {
    // A list of posts
    private List<Post> posts;
    private Context mContext;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public ImageRecord(ArrayList<Post> posts, OnItemClickListener listener) {
        this.posts = posts;
        mListener = listener;

    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_all_news, viewGroup, false);
        mContext = viewGroup.getContext();
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Glide.with(mContext)
                .load(posts.get(i).getThumbnailUrl())
                .centerCrop()
                .into(viewHolder.thumbnailImageView);

        viewHolder.title.setText(posts.get(i).getTitle());

        int count = posts.get(i).getCommentCount();
        //   String countText = (count == 1 || count == 0) ? count + " read more" : count + " read more";
        //  viewHolder.commentCount.setText(countText);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(posts.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;
        TextView title;
        TextView commentCount;

        public String formatString(String s) {
            String myString = s;
            //apostrophe
            myString = myString.replace("...&#8217;..", "\'");
            //double quotes
            myString = myString.replace("&#8216;", "\"");
            myString = myString.replace("&#34;", "\"");
            myString = myString.replace("&#x22;", "\"");

            return myString;
        }


        public ViewHolder(View itemView) {
            super(itemView);

            thumbnailImageView = (ImageView) itemView.findViewById(R.id.thumbnail);


            title = (TextView) itemView.findViewById(R.id.title);


            String article = "...&#8217;..";
            article = formatString(article);
            title.setText(article);

            commentCount = (TextView) itemView.findViewById(R.id.comment_count);

        }

    }
}