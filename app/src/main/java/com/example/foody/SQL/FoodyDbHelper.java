package com.example.foody.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foody.R;
import com.example.foody.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class FoodyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Foody.db";

    private static final String TABLE_NAME = "foody_shop";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SHOPNAME = "shop_name";
    private static final String COLUMN_ADRESS = "shop_adress";
    private static final String COLUMN_IMGURL = "shop_imgUrl";


    private static final String SQL_CREATE_TABLE_SHOP =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME + " (" +
                    FoodyDbHelper.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    FoodyDbHelper.COLUMN_SHOPNAME + " TEXT," +
                    FoodyDbHelper.COLUMN_ADRESS + " TEXT," +
                    FoodyDbHelper.COLUMN_IMGURL + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME;


    public FoodyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SHOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void intitializeData()
    {
       int count = this.getShopCount();

       if(count == 0 )
       {
           final List<Shop> shopList = new ArrayList<Shop>();

           shopList.add(new Shop(1,"Cơm Ngon Giang Béo - Phố Bưởi", "250 Đường Bưởi, P. Cống Vị,  Quận Ba Đình, Hà Nội", String.valueOf(R.drawable.image1)));
           shopList.add(new Shop(2,"Lẩu Đức Trọc - Tây Sơn", "61 Ngõ 298 Tây Sơn, P. Ngã Tư Sở, Đống Đa, Hà Nội", String.valueOf(R.drawable.image2)));
           for(Shop shop: shopList)
           {
               addShop(shop);
           }
       }
    }

    public int getShopCount()
    {
        String countQuery = "SELECT * FROM " + FoodyDbHelper.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery,null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public void addShop(Shop shop)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_SHOPNAME,shop.getShopName());
        values.put(FoodyDbHelper.COLUMN_ADRESS,shop.getAdress());
        values.put(FoodyDbHelper.COLUMN_IMGURL,shop.getImgUrl());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME,null,values);
    }

    public List<Shop> getAllShop()
    {
        String selectQuery = "SELECT  * FROM " + FoodyDbHelper.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        List<Shop> shopList = new ArrayList<Shop>();

        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));

                // Adding note to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }

        // return note list
        return shopList;
    }

    public void deleteAllShop()
    {
        String selectQuery = "DELETE FROM " + FoodyDbHelper.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(selectQuery);
    }
}
