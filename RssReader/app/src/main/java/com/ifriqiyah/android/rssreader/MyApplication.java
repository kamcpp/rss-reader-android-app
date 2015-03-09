package com.ifriqiyah.android.rssreader;

import android.app.Application;
import android.content.Context;


import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.domain.NewsItem;
import com.ifriqiyah.android.rssreader.domain.dao.ORMLiteOpenHelper;
import com.ifriqiyah.android.rssreader.reader.MenuElementReader;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static Context context;
    private static String myPackageName;
    private static List<MenuElement> menuElementEntities;
    private static Dao<MenuElement, Integer> menuElementDao;
    private static Dao<NewsItem, Integer> newsItemDao;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        myPackageName = getPackageName();
        makeDAO();
        menuElementEntities = new ArrayList<MenuElement>();

        if (!new File("/data/data/"+myPackageName+"/files/").exists()){
            new File("/data/data/"+myPackageName+"/files/").mkdir();
            try {
                MenuElementReader.readAndFillList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

    public static String getMyPackageName() {
        return myPackageName;
    }

    public static List<MenuElement> getMenuElementEntities() {
        try {
            List<MenuElement> menuElements = getMenuElementDao().queryForAll();
            menuElementEntities.clear();
            for (MenuElement menuElement : menuElements){
                menuElementEntities.add(menuElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuElementEntities;
    }

    public static Dao<MenuElement, Integer> getMenuElementDao() {
        return menuElementDao;
    }

    public static Dao<NewsItem, Integer> getNewsItemDao() {
        return newsItemDao;
    }

    private void makeDAO(){
        ORMLiteOpenHelper ormLiteOpenHelper = new ORMLiteOpenHelper();
        try {
            menuElementDao = ormLiteOpenHelper.getMenuElementDao();
            newsItemDao = ormLiteOpenHelper.getNewsItemDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
