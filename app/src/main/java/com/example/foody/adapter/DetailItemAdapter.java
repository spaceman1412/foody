package com.example.foody.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.CartActivity;
import com.example.foody.R;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;
import com.example.foody.model.SingletonLogin;
import com.google.android.material.datepicker.SingleDateSelector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final List<Product> productList;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ((TextView) holder.itemView.findViewById(R.id.textView_itemDetail_tenDoAn)).setText(productList.get(position).getProductName());
        ((TextView) holder.itemView.findViewById(R.id.textView_itemDetail_giaTien)).setText(productList.get(position).getPrice());


//        CartItem cartItem = new CartItem("a",)

        ((ImageView) holder.itemView.findViewById(R.id.imageView_item_detailItem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Product> productList1;

                productList1 = SingletonLogin.getProductList();
                for(Product product: productList1)
                {
                    Log.d("DetailItemAdapter",product.getProductName());
                }
                productList1.add(productList.get(position));

                SingletonLogin.setProductList(productList1);

                CartActivity cartActivity = new CartActivity();
                cartActivity.DoSingleTon();
            }
        });


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
