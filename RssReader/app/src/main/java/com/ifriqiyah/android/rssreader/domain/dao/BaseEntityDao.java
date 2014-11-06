package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ifriqiyah.android.rssreader.domain.sqlite.MySQLiteOpenHelper;

public abstract class BaseEntityDao<E> implements EntityDao<E> {

    protected static SQLiteOpenHelper sqLiteOpenHelper;
    protected static SQLiteDatabase sqLiteDatabase;

    public BaseEntityDao(Context context) {
        if (sqLiteOpenHelper == null ) {
            sqLiteOpenHelper = new MySQLiteOpenHelper(context);
            sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        }
    }

    protected Cursor executeReader(String query) {
        return executeReader(query, null);
    }

    protected Cursor executeReader(String query, String[] values) {
        Cursor cursor = sqLiteDatabase.rawQuery(query, values);
        return cursor;
    }

    protected  void execute(String query) {
        execute(query, null);
    }

    protected void execute(String query, Object[] values) {
        sqLiteDatabase.execSQL(query, values);
    }
}
