package com.example.foody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    Button btnCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnCheckout =  findViewById(R.id.btnCheckout) ;
        List<Product> studentList = getListData();

        final ListView listView =  findViewById(R.id.listCartItem);
        listView.setAdapter(new CartAdapter(this, studentList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Product product = (Product) o;
                Toast.makeText(CartActivity.this, "Selected: "  + product, Toast.LENGTH_LONG).show();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CheckoutActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    private List<Product> getListData() {
        List<Product> productList = new ArrayList<Product>();
        Product item1 = new Product("T01", "Chicken", "5$", "chicken01");
        Product item2 = new Product("T02", "Hambergert", "3$", "item01");
        Product item3 = new Product("T03", "Beefsteak Kobe", "50$", "item02");
        productList.add(item1);
        productList.add(item2);
        productList.add(item3);
        return productList;
    }
}