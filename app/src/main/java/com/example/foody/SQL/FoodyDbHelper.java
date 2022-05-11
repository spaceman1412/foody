package com.example.foody.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foody.R;
import com.example.foody.model.Product;
import com.example.foody.model.Shop;
import com.example.foody.model.SingletonLogin;
import com.example.foody.model.User;

import java.util.ArrayList;
import java.util.List;

public class FoodyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Foody.db";

    private static final String TABLE_NAME_SHOP = "foody_shop";
    private static final String COLUMN_ID_SHOP = "_id";
    private static final String COLUMN_SHOPNAME_SHOP = "shop_name";
    private static final String COLUMN_ADRESS_SHOP = "shop_adress";
    private static final String COLUMN_IMGURL_SHOP = "shop_imgUrl";

    private static final String TABLE_NAME_PRODUCT = "foody_product";
    private static final String COLUMN_ID_PRODUCT = "_id";
    private static final String COLUMN_PRODUCTNAME_PRODUCT = "product_name";
    private static final String COLUMN_PRICE_PRODUCT = "product_price";
    private static final String COLUMN_IMGURL_PRODUCT = "product_imgUrl";
    private static final String COLUMN_SHOPID_PRODUCT = "product_shopId";

    private static final String TABLE_NAME_USER = "foody_user";
    private static final String COLUMN_ID_USER = "_id";
    private static final String COLUMN_USERNAME_USER = "user_username";
    private static final String COLUMN_PASSWORD_USER = "user_password";
    private static final String COLUMN_EMAIL_USER = "user_email";



    private static final String SQL_CREATE_TABLE_SHOP =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME_SHOP + " (" +
                    FoodyDbHelper.COLUMN_ID_SHOP + " INTEGER PRIMARY KEY," +
                    FoodyDbHelper.COLUMN_SHOPNAME_SHOP + " TEXT," +
                    FoodyDbHelper.COLUMN_ADRESS_SHOP + " TEXT," +
                    FoodyDbHelper.COLUMN_IMGURL_SHOP + " TEXT)";

    private static final String SQL_CREATE_TABLE_PRODUCT =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME_PRODUCT + " (" +
                    FoodyDbHelper.COLUMN_ID_PRODUCT + " INTEGER PRIMARY KEY," +
                    FoodyDbHelper.COLUMN_PRODUCTNAME_PRODUCT + " TEXT," +
                    FoodyDbHelper.COLUMN_PRICE_PRODUCT + " TEXT," +
                    FoodyDbHelper.COLUMN_IMGURL_PRODUCT + " TEXT," +
                    FoodyDbHelper.COLUMN_SHOPID_PRODUCT + " TEXT)";

    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME_USER + " (" +
                    FoodyDbHelper.COLUMN_ID_USER + " INTEGER PRIMARY KEY," +
                    FoodyDbHelper.COLUMN_USERNAME_USER + " TEXT," +
                    FoodyDbHelper.COLUMN_PASSWORD_USER + " TEXT," +
                    FoodyDbHelper.COLUMN_EMAIL_USER + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_SHOP =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_SHOP;


    private static final String SQL_DELETE_ENTRIES_PRODUCT =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_PRODUCT;
    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_USER;

    public FoodyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SHOP);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_SHOP);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_PRODUCT);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_USER);

        onCreate(sqLiteDatabase);
    }

    public void intitializeData() {
        int count = this.getShopCount();

        if (count == 0) {
            final List<Shop> shopList = new ArrayList<Shop>();
            final List<Product> productList = new ArrayList<Product>();

            shopList.add(new Shop(1, "Cơm Ngon Giang Béo - Phố Bưởi", "250 Đường Bưởi, P. Cống Vị,  Quận Ba Đình, Hà Nội", String.valueOf(R.drawable.image1)));
            shopList.add(new Shop(2, "Lẩu Đức Trọc - Tây Sơn", "61 Ngõ 298 Tây Sơn, P. Ngã Tư Sở, Đống Đa, Hà Nội", String.valueOf(R.drawable.image2)));
            productList.add(new Product("1", "Trà Chanh", "5", "", "1"));
            productList.add(new Product("2", "Bún giả cầy", "50", "", "1"));
            productList.add(new Product("3", "Ếch Xào Thả Lẩu 0,5kg"
                    , "151", "", "2"));
            productList.add(new Product("4", "Ba Chỉ Bò Mỹ 0,5kg"
                    , "160", "", "2"));
            for (Shop shop : shopList) {
                addShop(shop);
            }
            for (Product product : productList) {
                addProduct(product);
            }
        }
    }

    public List<Product> getProductWithShopId(String shopId) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PRODUCT + " WHERE " + COLUMN_SHOPID_PRODUCT + " = " + shopId;


        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

                // Adding note to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return note list
        return productList;
    }

    public long addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_USERNAME_USER, user.getUsername());
        values.put(FoodyDbHelper.COLUMN_PASSWORD_USER, user.getPassword());
        values.put(FoodyDbHelper.COLUMN_EMAIL_USER, user.getEmail());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_USER, null, values);

        Log.d("FoodyDbHelper","Created successful "+ user.getEmail());

        Log.d("FoodyDbHelper", String.valueOf(newRowId));
        return newRowId;
    }



    public int getShopCount() {
        String countQuery = "SELECT * FROM " + FoodyDbHelper.TABLE_NAME_SHOP;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public List<Product> getAllProduct() {
        String selectQuery = "SELECT  * FROM " + FoodyDbHelper.TABLE_NAME_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Product> productList = new ArrayList<Product>();

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

                // Adding note to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return note list
        return productList;
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_PRODUCTNAME_PRODUCT, product.getProductName());
        values.put(FoodyDbHelper.COLUMN_PRICE_PRODUCT, product.getPrice());
        values.put(FoodyDbHelper.COLUMN_IMGURL_PRODUCT, product.getImageItem());
        values.put(FoodyDbHelper.COLUMN_SHOPID_PRODUCT, product.getShopId());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_PRODUCT, null, values);
    }


    public void addShop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_SHOPNAME_SHOP, shop.getShopName());
        values.put(FoodyDbHelper.COLUMN_ADRESS_SHOP, shop.getAdress());
        values.put(FoodyDbHelper.COLUMN_IMGURL_SHOP, shop.getImgUrl());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_SHOP, null, values);
    }

    public List<Shop> getAllShop() {
        String selectQuery = "SELECT  * FROM " + FoodyDbHelper.TABLE_NAME_SHOP;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Shop> shopList = new ArrayList<Shop>();

        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                // Adding note to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }

        // return note list
        return shopList;
    }

    public void deleteAllShop() {
        String selectQuery = "DELETE FROM " + FoodyDbHelper.TABLE_NAME_SHOP;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(selectQuery);
    }

    public void deleteAllProduct() {
        String selectQuery = "DELETE FROM " + FoodyDbHelper.TABLE_NAME_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(selectQuery);
    }
}
