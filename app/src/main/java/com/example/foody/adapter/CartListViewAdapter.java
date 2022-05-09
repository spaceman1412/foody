package com.example.foody.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foody.R;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartListViewAdapter extends BaseAdapter {


    public CartListViewAdapter(List<Product> productList) {
        this.productList = productList;
    }

    List<Product> productList;

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewProduct;
        if (view == null) {
            viewProduct = View.inflate(viewGroup.getContext(), R.layout.cart_list_item, null);
        } else viewProduct = view;

        //Bind sữ liệu phần tử vào View
        Product product = (Product) getItem(i);
        TextView productName = viewProduct.findViewById(R.id.txtProductName);
        TextView productPrice = viewProduct.findViewById(R.id.txtProductPrice);
        productName.setText(product.getProductName());
        productPrice.setText(product.getPrice());

        return viewProduct;
    }
}
