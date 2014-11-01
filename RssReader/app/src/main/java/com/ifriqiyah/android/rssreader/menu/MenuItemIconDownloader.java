package com.ifriqiyah.android.rssreader.menu;

import android.os.Environment;

import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.util.FileDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static com.ifriqiyah.android.rssreader.Constants.*;

public class MenuItemIconDownloader {

    public static void checkAndBuildDirectories() {
        File smallIconsDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small");
        File bigIconsDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big");
        smallIconsDirectory.mkdirs();
        bigIconsDirectory.mkdirs();
    }
    public static void checkOrDownloadMenuItemIcons(MenuElement menuElement) throws IOException {
        checkAndBuildDirectories();
        File smallIconImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small/" + menuElement.getId() + ".png");
        File smallIconHashFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/small/" + menuElement.getId() + ".hash");
        File bigIconImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big/" + menuElement.getId() + ".png");
        File bigIconHashFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu/big/" + menuElement.getId() + ".hash");

        boolean shouldDownloadSmallIcon = false;
        if (!smallIconImageFile.exists()) {
            shouldDownloadSmallIcon = true;
        } else {
            Scanner scanner = new Scanner(new FileInputStream(smallIconHashFile));
            String hash = scanner.nextLine();
            scanner.close();
            if (!hash.trim().equals(menuElement.getSmallIconHash())) {
                shouldDownloadSmallIcon = true;
            }
        }

        if(shouldDownloadSmallIcon) {
            new FileDownloader(ICONS_BASE_URL + "/small/" + menuElement.getId() + ".png", smallIconImageFile.getAbsolutePath(), true).download();
        }

        boolean shouldDownloadBigIcon = false;
        if (!bigIconImageFile.exists()) {
            shouldDownloadBigIcon = true;
        } else {
            Scanner scanner = new Scanner(new FileInputStream(bigIconHashFile));
            String hash = scanner.nextLine();
            scanner.close();
            if (!hash.trim().equals(menuElement.getBigIconHash())) {
                shouldDownloadBigIcon = true;
            }
        }

        if(shouldDownloadBigIcon) {
            new FileDownloader(ICONS_BASE_URL + "/big/" + menuElement.getId() + ".png", bigIconImageFile.getAbsolutePath(), true).download();
        }
    }
}
