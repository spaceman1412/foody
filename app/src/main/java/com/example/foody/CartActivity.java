package com.example.foody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;
import com.example.foody.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    Button btnCheckout;
    RecyclerView recyclerView;

    public static List<CartItem> cartItemList =  new ArrayList<CartItem>();;
    List<Product> productsList =  new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnCheckout =  findViewById(R.id.btnCheckout) ;

        recyclerView = findViewById(R.id.recyclerView_cart);


//        Product item1 = new Product("T01", "Chicken", "5$", "chicken01");
//        Product item2 = new Product("T02", "Hambergert", "3$", "item01");
//        Product item3 = new Product("T03", "Beefsteak Kobe", "50$", "item02");
//        productsList.add(item1);
//        productsList.add(item2);
//        productsList.add(item3);

//        cartItemList.add(new CartItem("a",productsList,new Shop(1,"a","a","a")));
//        cartItemList.add(new CartItem("a",productsList,new Shop(2,"a","a","a")));
//        cartItemList.add(new CartItem("a",productsList,new Shop("a","a","a")));
//        cartItemList.add(new CartItem("a",productsList,new Shop("a","a","a")));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new CartAdapter(cartItemList));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CheckoutActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }


}