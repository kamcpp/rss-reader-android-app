package com.ifriqiyah.android.rssreader.domain.dao;

import android.database.sqlite.SQLiteDatabase;

import com.ifriqiyah.android.rssreader.MyApplication;
import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.domain.NewsItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class ORMLiteOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "if.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<MenuElement, Integer> menuElementDao = null;
    private Dao<NewsItem, Integer> newsItemDao = null;

    public ORMLiteOpenHelper() {
        super(MyApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MenuElement.class);
            TableUtils.createTable(connectionSource, NewsItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, MenuElement.class, true);
            TableUtils.dropTable(connectionSource, NewsItem.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<MenuElement, Integer> getMenuElementDao() throws SQLException {
        if (menuElementDao == null) {
            menuElementDao = getDao(MenuElement.class);
        }
        return menuElementDao;
    }
    public Dao<NewsItem, Integer> getNewsItemDao() throws SQLException {
        if (newsItemDao == null) {
            newsItemDao = getDao(NewsItem.class);
        }
        return newsItemDao;
    }
}
