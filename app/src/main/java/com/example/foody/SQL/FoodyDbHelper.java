package com.example.foody.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foody.R;
import com.example.foody.UserFragment;
import com.example.foody.model.Product;
import com.example.foody.model.ProductAmount;
import com.example.foody.model.Shop;
import com.example.foody.model.SingletonLogin;
import com.example.foody.model.User;

import java.util.ArrayList;
import java.util.List;

public class FoodyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
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

    private static final String TABLE_NAME_PRODUCTAMOUNT = "foody_productAmount";
    private static final String COLUMN_ID_PRODUCTAMOUNT = "_id";
    private static final String COLUMN_AMOUNT_PRODUCTAMOUNT = "productAmount_amount";
    private static final String COLUMN_PRODUCTID_PRODUCTAMOUNT = "productAmount_productId";

    private static final String TABLE_NAME_CHECKOUT = "foody_checkOut";
    private static final String COLUMN_ID_CHECKOUT = "_id";
    private static final String COLUMN_PRICE_CHECKOUT = "checkOut_price";


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

    private static final String SQL_CREATE_TABLE_PRODUCTAMOUNT =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME_PRODUCTAMOUNT + " (" +
                    FoodyDbHelper.COLUMN_ID_PRODUCTAMOUNT + " INTEGER PRIMARY KEY, " +
                    FoodyDbHelper.COLUMN_AMOUNT_PRODUCTAMOUNT + " TEXT, " +
                    FoodyDbHelper.COLUMN_PRODUCTID_PRODUCTAMOUNT + " TEXT)";

    private static final String SQL_CREATE_TABLE_CHECKOUT =
            "CREATE TABLE " + FoodyDbHelper.TABLE_NAME_CHECKOUT + " (" +
                    FoodyDbHelper.COLUMN_ID_CHECKOUT + " INTEGER PRIMARY KEY, " +
                    FoodyDbHelper.COLUMN_PRICE_CHECKOUT + " TEXT)";



    private static final String SQL_DELETE_ENTRIES_SHOP =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_SHOP;


    private static final String SQL_DELETE_ENTRIES_PRODUCT =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_PRODUCT;
    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_USER;

    private static final String SQL_DELETE_ENTRIES_PRODUCTAMOUNT =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_PRODUCTAMOUNT;

    private static final String SQL_DELETE_ENTRIES_CHECKOUT =
            "DROP TABLE IF EXISTS " + FoodyDbHelper.TABLE_NAME_CHECKOUT;

    public FoodyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SHOP);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCTAMOUNT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_CHECKOUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_SHOP);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_PRODUCT);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_USER);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_PRODUCTAMOUNT);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_CHECKOUT);

        onCreate(sqLiteDatabase);
    }

    public void intitializeData() {
        int count = this.getShopCount();

        if (count == 0) {
            final List<Shop> shopList = new ArrayList<Shop>();
            final List<Product> productList = new ArrayList<Product>();

            shopList.add(new Shop(1, "Cơm Ngon Giang Béo - Phố Bưởi", "250 Đường Bưởi, P. Cống Vị,  Quận Ba Đình, Hà Nội", String.valueOf(R.drawable.image1)));
            shopList.add(new Shop(2, "Lẩu Đức Trọc - Tây Sơn", "61 Ngõ 298 Tây Sơn, P. Ngã Tư Sở, Đống Đa, Hà Nội", String.valueOf(R.drawable.image2)));
            shopList.add(new Shop(3, "Lâm Anh - Bún Đậu & Bún Nước", "2 Ngách 44/64 Ngõ 44 Trần Thái Tông, P. Dịch Vọng Hậu, Cầu Giấy, Hà Nội", String.valueOf(R.drawable.image2)));
            shopList.add(new Shop(4, "Trà Sữa Tocotoco - Ngọc Hồi", "Km13 QL1A Ngọc Hồi, X. Ngọc Hồi, Thanh Trì, Hà Nội", String.valueOf(R.drawable.image2)));
            productList.add(new Product("1", "Trà Chanh", "5", "", "1"));
            productList.add(new Product("2", "Bún giả cầy", "50", "", "1"));
            productList.add(new Product("3", "Ếch Xào Thả Lẩu 0,5kg"
                    , "151", "", "2"));
            productList.add(new Product("4", "Ba Chỉ Bò Mỹ 0,5kg"
                    , "160", "", "2"));
            productList.add(new Product("5", "Bún Sườn Mọc Măng"
                    , "36", "", "3"));
            productList.add(new Product("6", "Nước đậu nành"
                    , "9", "", "3"));
            productList.add(new Product("7", "Trà Sữa Ba Anh Em"
                    , "28", "", "4"));
            productList.add(new Product("8", "Trà Xanh Sữa Vị Nhài"
                    , "22", "", "4"));
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

    public List<Shop> getShopWithShopId(String shopId) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Shop> shopList = new ArrayList<Shop>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_SHOP + " WHERE " + COLUMN_ID_SHOP + " = " + shopId;


        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
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

    public Product getProductWithProductId(String productId) {
        SQLiteDatabase db = this.getWritableDatabase();


        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PRODUCT + " WHERE " + COLUMN_ID_PRODUCT + " = " + productId;


        Cursor cursor = db.rawQuery(selectQuery, null);
        Product product = null;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                product = new Product(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

                // Adding note to list
            } while (cursor.moveToNext());
        }

        // return note list
        return product;
    }

    public long addProductAmount(ProductAmount productAmount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_AMOUNT_PRODUCTAMOUNT, productAmount.getAmount());
        values.put(FoodyDbHelper.COLUMN_PRODUCTID_PRODUCTAMOUNT, productAmount.getProduct().getProductId());


        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_PRODUCTAMOUNT, null, values);

//        Log.d("FoodyDbHelper", "Created successful " + productAmount);

        Log.d("FoodyDbHelper", String.valueOf(newRowId));
        return newRowId;
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_USERNAME_USER, user.getUsername());
        values.put(FoodyDbHelper.COLUMN_PASSWORD_USER, user.getPassword());
        values.put(FoodyDbHelper.COLUMN_EMAIL_USER, user.getEmail());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_USER, null, values);

        Log.d("FoodyDbHelper", "Created successful " + user.getEmail());

        Log.d("FoodyDbHelper", String.valueOf(newRowId));
        return newRowId;
    }

    public List<ProductAmount> getAllProductAmount() {
        String selectQuery = "SELECT  * FROM " + FoodyDbHelper.TABLE_NAME_PRODUCTAMOUNT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<ProductAmount> productAmountList = new ArrayList<ProductAmount>();

        if (cursor.moveToFirst()) {
            do {
                ProductAmount productAmount = new ProductAmount(cursor.getString(0), Integer.parseInt( cursor.getString(1)), getProductWithProductId(cursor.getString(2)));

                // Adding note to list
                productAmountList.add(productAmount);
            } while (cursor.moveToNext());
        }

        // return note list
        return productAmountList;
    }


    public int getShopCount() {
        String countQuery = "SELECT * FROM " + FoodyDbHelper.TABLE_NAME_SHOP;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

//    public User getUser(String email, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                FoodyDbHelper.COLUMN_ID_USER,
//                FoodyDbHelper.COLUMN_USERNAME_USER,
//                FoodyDbHelper.COLUMN_PASSWORD_USER,
//                FoodyDbHelper.COLUMN_EMAIL_USER
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        String selection = FoodyDbHelper.COLUMN_EMAIL_USER + " = ? AND " + FoodyDbHelper.COLUMN_PASSWORD_USER + " = ?";
//        String[] selectionArgs = {email, password};
//
//        // How you want the results sorted in the resulting Cursor
//
//
//        Cursor cursor = db.query(
//                FoodyDbHelper.TABLE_NAME_USER,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                null               // The sort order
//        );
//
//        User user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
//        return user;
//    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + FoodyDbHelper.TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = ? AND " + COLUMN_PASSWORD_USER + " = ? ";


        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
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

    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodyDbHelper.COLUMN_PRODUCTNAME_PRODUCT, product.getProductName());
        values.put(FoodyDbHelper.COLUMN_PRICE_PRODUCT, product.getPrice());
        values.put(FoodyDbHelper.COLUMN_IMGURL_PRODUCT, product.getImageItem());
        values.put(FoodyDbHelper.COLUMN_SHOPID_PRODUCT, product.getShopId());

        long newRowId = db.insert(FoodyDbHelper.TABLE_NAME_PRODUCT, null, values);
        return newRowId;
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

    public void deleteAllProductAmount() {
        String selectQuery = "DELETE FROM " + FoodyDbHelper.TABLE_NAME_PRODUCTAMOUNT;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(selectQuery);
    }


    public void deleteAllProduct() {
        String selectQuery = "DELETE FROM " + FoodyDbHelper.TABLE_NAME_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(selectQuery);
    }
}
