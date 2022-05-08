package com.example.foody.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.R;
import com.example.foody.model.Product;

import java.util.List;

public class DetailItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Product> productList;

    public DetailItemAdapter(List<Product> productList)
    {
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailitem_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.textView_itemDetail_tenDoAn)).setText(productList.get(position).getProductName());
        ((TextView) holder.itemView.findViewById(R.id.textView_itemDetail_giaTien)).setText(productList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);

        }
    }
}
