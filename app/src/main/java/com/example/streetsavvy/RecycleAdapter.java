package com.example.streetsavvy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private String[] products;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview;
        public ViewHolder(TextView v) {
            super(v);
            textview = v;
        }
    }

    public RecycleAdapter(String[] data) {
        products = data;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textview.setText(products[position]);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }
}
