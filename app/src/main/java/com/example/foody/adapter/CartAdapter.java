package com.example.foody.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foody.R;
import com.example.foody.model.Product;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private List<Product> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CartAdapter(Context context, List<Product> products) {
        this.context = context;
        this.listData = products;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cart_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imgProductImage = (ImageView) convertView.findViewById(R.id.imgProductImage);
            viewHolder.txtProductName = (TextView) convertView.findViewById(R.id.txtProductName);
            viewHolder.txtProductPrice = (TextView) convertView.findViewById(R.id.txtProductPrice);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = this.listData.get(i);
        int ImageID = this.getMipmapResIdByName(product.getImageItem());
        viewHolder.imgProductImage.setImageResource(ImageID);
        viewHolder.txtProductName.setText(product.getProductName());
        viewHolder.txtProductPrice.setText(product.getPrice());
        return convertView;
    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("ProductListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView imgProductImage;
        TextView txtProductName, txtProductPrice;


    }

}
