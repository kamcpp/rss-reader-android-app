package com.ifriqiyah.android.rssreader.menu;

import android.os.Environment;
import android.util.Log;

import com.ifriqiyah.android.rssreader.util.FileDownloader;
import com.ifriqiyah.android.rssreader.util.HashProviderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

        String smallImageFileUrl = ICONS_BASE_URL + "/small/" + menuElement.getId() + ".png";
        String bigImageFileUrl = ICONS_BASE_URL + "/big/" + menuElement.getId() + ".png";

        String smallImageFileName = menuElement.getId() + ".png";
        String bigImageFileName = menuElement.getId() + ".png";
        String smallImageHashFileName = menuElement.getId() + ".hash";
        String bigImageHashFileName = menuElement.getId() + ".hash";

        String menuPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DOWNLOAD_DIR + "/menu";
        String smallImageFilePath = menuPath + "/small/" + smallImageFileName;
        String bigImageFilePath = menuPath + "/big/" + bigImageFileName;
        String smallImageHashFilePath = menuPath + "/small/" + smallImageHashFileName;
        String bigImageHashFilePath = menuPath + "/big/" + bigImageHashFileName;

        File smallIconImageFile = new File(smallImageFilePath);
        File smallIconHashFile = new File(smallImageHashFilePath);
        File bigIconImageFile = new File(bigImageFilePath);
        File bigIconHashFile = new File(bigImageHashFilePath);

        boolean shouldDownloadSmallIcon = false;
        if (!smallIconImageFile.exists()) {
            shouldDownloadSmallIcon = true;
        } else {
            if (smallIconHashFile.exists()) {
                Scanner scanner = new Scanner(new FileInputStream(smallIconHashFile));
                String hash = scanner.nextLine();
                scanner.close();
                if (!hash.trim().equals(menuElement.getSmallIconHash())) {
                    shouldDownloadSmallIcon = true;
                }
            } else {
                shouldDownloadSmallIcon = true;
            }
        }

        if (shouldDownloadSmallIcon) {
            Log.d("DEBUG", "Downloading " + smallImageFileName);
            new FileDownloader(smallImageFileUrl, new FileOutputStream(smallIconImageFile.getAbsolutePath()), true).download();
            PrintWriter printWriter = new PrintWriter(smallIconHashFile);
            String hash = HashProviderFactory.getHashPoProvider().hash(smallIconImageFile);
            printWriter.write(hash);
            printWriter.close();
            Log.d("DEBUG", "Generated hash value for '" + smallImageFileName + "': " + hash);
        } else {
            Log.d("DEBUG", "No need to download '" + smallImageFileName + "'");
        }

        boolean shouldDownloadBigIcon = false;
        if (!bigIconImageFile.exists()) {
            shouldDownloadBigIcon = true;
        } else {
            if (bigIconHashFile.exists()) {
                Scanner scanner = new Scanner(new FileInputStream(bigIconHashFile));
                String hash = scanner.nextLine();
                scanner.close();
                if (!hash.trim().equals(menuElement.getBigIconHash())) {
                    shouldDownloadBigIcon = true;
                }
            } else {
                shouldDownloadBigIcon = true;
            }
        }


        if (shouldDownloadBigIcon) {
            Log.d("DEBUG", "Downloading " + bigImageFileName);
            new FileDownloader(bigImageFileUrl, new FileOutputStream(bigIconImageFile.getAbsolutePath()), true).download();
            PrintWriter printWriter = new PrintWriter(bigIconHashFile);
            String hash = HashProviderFactory.getHashPoProvider().hash(bigIconImageFile);
            printWriter.write(hash);
            printWriter.close();
            Log.d("DEBUG", "Generated hash value for '" + bigImageFileName + "': " + hash);
        } else {
            Log.d("DEBUG", "No need to download '" + bigImageFileName + "'");
        }
    }
}
