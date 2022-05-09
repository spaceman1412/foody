package com.example.foody.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.R;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList)
    {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_shop,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tenShop =  holder.itemView.findViewById(R.id.textView_cart_tenShop);
        ListView listView = holder.itemView.findViewById(R.id.listView_cart_listShop);

        tenShop.setText(cartItemList.get(position).getShop().getShopName());
        listView.setAdapter(new CartListViewAdapter(cartItemList.get(position).getProducts()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

}
