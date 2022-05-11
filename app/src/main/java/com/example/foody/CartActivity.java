package com.example.foody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;
import com.example.foody.model.Shop;
import com.example.foody.model.SingletonLogin;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    Button btnCheckout;
    RecyclerView recyclerView;

    public static List<CartItem> cartItemList = new ArrayList<CartItem>();
    CartAdapter adapter;

    public static List<Product> productsList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnCheckout = findViewById(R.id.btnCheckout);

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


//        for(Product product: productList)
//        {
//            if(shopId.contains(product.getShopId())) shopId.add(product.getShopId());
//        }


        List<CartItem> getlist = SingletonLogin.getCartItemList();

        for (CartItem cartItem : getlist) {
            for (Product product : cartItem.getProducts()) {
                Log.d("CartActivity", product.getProductName());
            }
        }


        adapter = new CartAdapter(getlist);

        recyclerView.setAdapter(adapter);


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CheckoutActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }


    public void DoSingleTon() {
        List<Product> productList = SingletonLogin.getProductList();
        List<String> shopId = new ArrayList<String>();
        shopId.add(productList.get(0).getShopId());
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (!shopId.contains(product.getShopId())) shopId.add(product.getShopId());
        }
        for (String id : shopId) {
            Log.d("CartActivity", id);
        }

        for (String id : shopId) {
            List<Product> productList1 = new ArrayList<Product>();
            for (Product product : productList) {
                if (product.getShopId().equals(id)) {
                    productList1.add(product);
                }
            }
            int i;

            List<CartItem> getlist = SingletonLogin.getCartItemList();
            boolean giongNhau = false;
            for (CartItem cartItem : getlist) {
                if (cartItem.getCartID().equals(productList1.get(0).getShopId())) {
                    giongNhau = true;
                    List<Product> productList2 = cartItem.getProducts();

                    for(Product product: productList1)
                    {
                        if(!productList2.contains(product)) productList2.add(product);
                    }

                    cartItem.setProducts(productList2);
                }
            }
            if (!giongNhau) {
                CartItem cartItem = new CartItem(productList1.get(0).getShopId(), productList1, productList1.get(0).getShopId(), SingletonLogin.getUserId());
                getlist.add(cartItem);
                SingletonLogin.setCartItemList(getlist);
            }
        }
    }
}