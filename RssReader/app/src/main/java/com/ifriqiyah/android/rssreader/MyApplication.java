package com.ifriqiyah.android.rssreader;

import android.app.Application;
import android.content.Context;

import org.labcrypto.avicenna.Avicenna;

public class MyApplication extends Application {

    private static Context context;
    private static String myPackageName;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        myPackageName = getPackageName();
        Avicenna.addDependencyFactory(new ApplicationDF());
    }

    public static Context getContext() {
        return context;
    }

    public static String getMyPackageName() {
        return myPackageName;
    }
}
