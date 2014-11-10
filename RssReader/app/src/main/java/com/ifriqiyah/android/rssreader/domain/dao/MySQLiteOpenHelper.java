package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kamran on 11/6/14.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "if.db";
    private final static int DATABASE_VERSION = 2;

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String NEWS_ITEM_TABLE_CREATE_QUERY = "create table news_item (" +
            "_id integer primary key autoincrement," +
            "menu_id integer not null," +
            "source_id integer not null," +
            "title text not null," +
            "description text," +
            "pub_date integer not null," +
            "source text," +
            "link text not null," +
            "enclosure_url text," +
            "category text," +
            "author text," +
            "comments text," +
            "rtitle text," +
            "keywords text," +
            "detail text," +
            "is_favorite integer);";

    private static final String MENU_ELEMENT_TABLE_CREATE_QUERY = "create table menu_element (" +
            "_id integer primary key," +
            "element_text text not null," +
            "english_text text not null," +
            "article_rss_url text not null," +
            "news_rss_url text not null," +
            "notified integer not null);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NEWS_ITEM_TABLE_CREATE_QUERY);
        sqLiteDatabase.execSQL(MENU_ELEMENT_TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists news_item");
        sqLiteDatabase.execSQL("drop table if exists menu_element");
        onCreate(sqLiteDatabase);
    }
}
