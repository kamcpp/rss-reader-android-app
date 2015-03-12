package com.ifriqiyah.android.rssreader;

import android.app.Application;
import android.content.Context;

import com.ifriqiyah.android.rssreader.adapter.MenuElementModel;
import com.ifriqiyah.android.rssreader.reader.MenuElementReader;

import java.io.File;
import java.io.IOException;

import dagger.ObjectGraph;

public class MyApplication extends Application {

    private static Context context;
    private static String myPackageName;
    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        myPackageName = getPackageName();
        objectGraph = ObjectGraph.create(new ApplicationModule());
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
