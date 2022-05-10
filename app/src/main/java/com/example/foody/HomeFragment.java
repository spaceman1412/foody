package com.example.foody;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.foody.SQL.FoodyDbHelper;
import com.example.foody.adapter.HomeAdapter;
import com.example.foody.model.Shop;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ImageButton imageButton;
    HomeAdapter adapter;
    List<Shop> shopList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageButton = (ImageButton) view.findViewById(R.id.btnCart);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_homeFragment);

        FoodyDbHelper db = new FoodyDbHelper(view.getContext());
        db.deleteAllShop();
        db.intitializeData();

        shopList = new ArrayList<Shop>(
        );
//        shopList.add(new Shop(1,"ShopName", "aaa", "aaaa"));
//
//        shopList.add(new Shop(2,"ShopName1", "aaa", "aaaa"));
//        shopList.add(new Shop(3,"ShopName2", "aaa", "aaaa"));

//        db.addShop(new Shop(1,"ShopName", "aaa", "aaaa"));
//        db.addShop(new Shop(2,"ShopName2", "aaa", "aaaa"));
        shopList = db.getAllShop();
        adapter = new HomeAdapter(shopList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}