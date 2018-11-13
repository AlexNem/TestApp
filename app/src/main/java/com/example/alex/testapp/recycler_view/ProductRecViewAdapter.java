package com.example.alex.testapp.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.testapp.R;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.recycler_view.ProductFragment.OnListFragmentInteractionListener;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecViewAdapter extends RecyclerView.Adapter<ProductRecViewAdapter.ViewHolder> {


    private final List<ResponseProduct> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public ProductRecViewAdapter(List<ResponseProduct> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        context = holder.productImage.getContext();
        Picasso.with(context)
                .load(holder.mItem.getUrl())
                .into(holder.productImage);
//        holder.productImage.setText(mValues.get(position).getUrl());
        holder.productName.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final ImageView productImage;
        private final TextView productName;
        private ResponseProduct mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            productImage = view.findViewById(R.id.rec_image_product);
            productName = view.findViewById(R.id.rec_product_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + productName.getText() + "'";
        }
    }
}
