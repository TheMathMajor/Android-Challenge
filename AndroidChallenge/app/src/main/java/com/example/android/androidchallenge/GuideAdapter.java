package com.example.android.androidchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pengqi on 2/22/2018.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Guide> myDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;
        public TextView location;
        public TextView date;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            icon = (ImageView) v.findViewById(R.id.icon);
            name = (TextView) v.findViewById(R.id.name);
            location = (TextView) v.findViewById(R.id.location);
            date = (TextView) v.findViewById(R.id.date);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GuideAdapter(ArrayList<Guide> myDataset, Context context) {
        this.myDataset = myDataset;
        this.context = context;
    }

    public void setFriendsList(ArrayList<Guide> myDataset) {
        this.myDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(myDataset.get(position).getEventName());
        holder.location.setText(myDataset.get(position).getEventLocation());
        holder.date.setText(myDataset.get(position).getEventDate());
        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context)
                .load(myDataset.get(position).getEventImageURL()).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }
}
