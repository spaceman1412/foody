package com.example.foody.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        LinearLayout linearLayout = holder.itemView.findViewById(R.id.linearLayout_cart_listShop);
        tenShop.setText(cartItemList.get(position).getShop().getShopName());

        List<Product> listProduct = cartItemList.get(position).getProducts();
        LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
        for(Product product : listProduct) {
            View child = inflater.inflate(R.layout.cart_list_item,null);
//            ((TextView) child.findViewById(R.id.textView_itemDetail_tenDoAn)).setText(product.getProductName());
//            ((TextView) child.findViewById(R.id.textView_itemDetail_giaTien)).setText(product.getPrice());
            linearLayout.addView(child);
        }
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
