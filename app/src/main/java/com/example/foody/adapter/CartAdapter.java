package com.example.foody.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.wifi.WifiManager;
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

import com.example.foody.CartActivity;
import com.example.foody.R;
import com.example.foody.SQL.FoodyDbHelper;
import com.example.foody.model.CartItem;
import com.example.foody.model.Product;
import com.example.foody.model.ProductAmount;
import com.example.foody.model.SingletonLogin;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartItem> cartItemList;

    Context context;

    public CartAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_shop, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView tenShop = holder.itemView.findViewById(R.id.textView_cart_tenShop);
        FoodyDbHelper foodyDbHelper = new FoodyDbHelper(holder.itemView.getContext());
        String shopName = foodyDbHelper.getShopWithShopId(cartItemList.get(position).getShopName()).get(0).getShopName();
        LinearLayout linearLayout = holder.itemView.findViewById(R.id.linearLayout_cart_listShop);
        LinearLayout linearLayoutLarge = holder.itemView.findViewById(R.id.cart_list_shop_large);
        tenShop.setText(shopName);

        List<Product> listProduct = cartItemList.get(position).getProducts();
        LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
        for (Product product : listProduct) {
            View child = inflater.inflate(R.layout.cart_list_item, null);

            ((TextView) child.findViewById(R.id.txtProductName)).setText(product.getProductName());
            ((TextView) child.findViewById(R.id.txtProductPrice)).setText(product.getPrice());
            TextView amount = child.findViewById(R.id.textView_cart_listItem_amount);

            ProductAmount productAmount = new ProductAmount(product.getProductId(), Integer.parseInt(amount.getText().toString()), product);
            List<ProductAmount> productAmountList = SingletonLogin.getProductAmountList();
            productAmountList.add(productAmount);
            SingletonLogin.setProductAmountList(productAmountList);



            foodyDbHelper.deleteAllProductAmount();
            for(ProductAmount productAmount2: SingletonLogin.getProductAmountList() )
            {
                foodyDbHelper.addProductAmount(productAmount2);
            }
            List<ProductAmount> productAmountList1 = foodyDbHelper.getAllProductAmount();
            for (ProductAmount productAmount2 : productAmountList1)
            {
                Log.d("CartActivity",productAmount2.getId());
            }


            TextView totalPrice = (TextView) ((Activity) context).findViewById(R.id.txtView_cart_tien);
            CartActivity cartActivity = new CartActivity();
            totalPrice.setText(String.valueOf(SingletonLogin.getPrice()));
            ((ImageView) child.findViewById(R.id.imageView_cart_listItem_plus)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = Integer.parseInt((String) amount.getText());
                    i++;
                    amount.setText(String.valueOf(i));
                    List<ProductAmount> productAmountList = SingletonLogin.getProductAmountList();
                    for (ProductAmount productAmount1 : productAmountList) {
                        if (productAmount1.getId().equals(product.getProductId())) {
                            productAmount1.setAmount(productAmount1.getAmount() + 1);
                            totalPrice.setText(String.valueOf(SingletonLogin.getPrice()));
                            foodyDbHelper.deleteAllProductAmount();
                            for(ProductAmount productAmount2: SingletonLogin.getProductAmountList() )
                            {
                                foodyDbHelper.addProductAmount(productAmount2);
                            }
                            List<ProductAmount> productAmountList1 = foodyDbHelper.getAllProductAmount();
                            for (ProductAmount productAmount2 : productAmountList1)
                            {
                                Log.d("CartActivity",productAmount2.getId());
                            }
                        }
                    }
                }
            });

            ((ImageView) child.findViewById(R.id.imageView_cart_listItem_minus)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = Integer.parseInt((String) amount.getText());
                    if (i > 1) {
                        i--;
                        amount.setText(String.valueOf(i));
                        List<ProductAmount> productAmountList = SingletonLogin.getProductAmountList();
                        for (ProductAmount productAmount1 : productAmountList) {
                            if (productAmount1.getId().equals(product.getProductId())) {
                                productAmount1.setAmount(productAmount1.getAmount() - 1);
                                totalPrice.setText(String.valueOf(SingletonLogin.getPrice()));
                                foodyDbHelper.deleteAllProductAmount();
                                for(ProductAmount productAmount2: SingletonLogin.getProductAmountList() )
                                {
                                    foodyDbHelper.addProductAmount(productAmount2);
                                }
                                List<ProductAmount> productAmountList1 = foodyDbHelper.getAllProductAmount();
                                for (ProductAmount productAmount2 : productAmountList1)
                                {
                                    Log.d("CartActivity",productAmount2.getId());
                                }
                            }
                        }
                    }
                }
            });

            ((ImageView) child.findViewById(R.id.imageView_cart_listItem_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.removeView(child);
                    List<Product> getProduct = cartItemList.get(position).getProducts();
                    List<CartItem> getCartList = SingletonLogin.getCartItemList();
                    getProduct.remove(product);
                    totalPrice.setText(String.valueOf(SingletonLogin.getPrice()));

                    SingletonLogin.getCartItemList().get(position).setProducts(getProduct);
                    if (SingletonLogin.getCartItemList().get(position).getProducts().size() == 0) {
                        linearLayoutLarge.removeAllViews();
                        Log.d("CartAdapter", "Calling remove layout");
                        getCartList.remove(cartItemList.get(position));
                        SingletonLogin.setCartItemList(getCartList);
                    }
                }
            });

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
