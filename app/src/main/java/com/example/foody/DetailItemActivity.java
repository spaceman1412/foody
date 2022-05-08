package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foody.adapter.DetailItemAdapter;
import com.example.foody.model.Product;
import com.example.foody.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class DetailItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Bundle extras = getIntent().getExtras();
        Shop shop = (Shop) extras.getSerializable("shop");

        List<Product> productsList = new ArrayList<Product>();
        Product item1 = new Product("T01", "Chicken", "5$", "chicken01");
        Product item2 = new Product("T02", "Hambergert", "3$", "item01");
        Product item3 = new Product("T03", "Beefsteak Kobe", "50$", "item02");
        productsList.add(item1);
        productsList.add(item2);
        productsList.add(item3);

        TextView textView = findViewById(R.id.textView_titleNamedetail);
        textView.setText(shop.getShopName());
        recyclerView = findViewById(R.id.recyclerView_detailItem_listItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DetailItemAdapter(productsList));
    }
}