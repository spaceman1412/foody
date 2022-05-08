package com.example.foody.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Foody.db";

    private static final String TABLE_NAME = "foody_shop";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SHOPNAME = "book_title";
    private static final String COLUMN_ADRESS = "book_author";
    private static final String COLUMN_IMGURL = "book_pages";

//
//    private static final String SQL_CREATE_TABLE_SHOP =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
//                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    public FoodyDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
