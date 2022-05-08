package com.example.foody.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.DetailItemActivity;
import com.example.foody.R;
import com.example.foody.model.Shop;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<Shop> shopsList;

    public HomeAdapter(List<Shop> shopsList)
    {
        this.shopsList = shopsList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_fragment,viewGroup,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        TextView tenDoAn =  viewHolder.itemView.findViewById(R.id.textView_home_tenDoAn);
        TextView diaChi = viewHolder.itemView.findViewById(R.id.textView_home_diaChi);

        tenDoAn.setText(shopsList.get(position).getShopName());
        tenDoAn.setText(shopsList.get(position).getAdress());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailItemActivity.class);
                intent.putExtra("shop", shopsList.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopsList.size();
    }
}

