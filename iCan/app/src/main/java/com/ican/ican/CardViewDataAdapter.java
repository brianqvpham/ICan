package com.ican.ican;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

    public CardViewDataAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview, viewGroup, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.name.setText(Recyclable.values()[i].name);
        viewHolder.count.setText("" + (MainActivity.dataRange ? Recyclable.values()[i].alltime : Recyclable.values()[i].daily));
    }

    @Override
    public int getItemCount() {
        return Recyclable.values().length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView count;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.name);
            count = (TextView) itemLayoutView
                    .findViewById(R.id.count);

        }
    }
}
