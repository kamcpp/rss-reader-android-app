package com.ifriqiyah.android.rssreader.menu;

import android.os.Environment;

import com.ifriqiyah.android.rssreader.domain.MenuItem;
import com.ifriqiyah.android.rssreader.util.FileDownloader;

import java.io.File;

public class MenuItemIconDownloader {

    private static final String ICONS_BASE_DIR = "http://ifriqiyah.com/android/icons";
    private static final String DOWNLOAD_DIR = "ifapp";

    public static void checkAndBuildDirectories() {
        File smallIconsDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small");
        File bigIconsDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big");
        smallIconsDirectory.mkdirs();
        bigIconsDirectory.mkdirs();
    }
    public static void checkOrDownloadMenuItemIcons(MenuItem menuItem) {
        checkAndBuildDirectories();
        File smallIconImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small/" + menuItem.getId() + ".png");
        File smallIconHashFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small/" + menuItem.getId() + ".hash");
        File bigIconImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big/" + menuItem.getId() + ".png");
        File bigIconHashFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big/" + menuItem.getId() + ".hash");

        if (!smallIconImageFile.exists()) {
            FileDownloader
        }

    }
}
