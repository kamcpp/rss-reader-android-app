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

import dagger.ObjectGraph;

public class MyApplication extends Application {

    private static Context context;
    private static String myPackageName;
    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {

        context = getApplicationContext();
        myPackageName = getPackageName();
        objectGraph = ObjectGraph.create(new ApplicationModule());

        if (!new File("/data/data/" + myPackageName + "/files/").exists()) {
            new File("/data/data/" + myPackageName + "/files/").mkdir();
            try {
                new MenuElementReader().readAndFillList();
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

    public static ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
