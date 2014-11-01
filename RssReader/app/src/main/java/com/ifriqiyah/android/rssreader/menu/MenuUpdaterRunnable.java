package com.ifriqiyah.android.rssreader.menu;


import android.net.http.AndroidHttpClient;

import com.ifriqiyah.android.rssreader.domain.MenuItem;
import com.ifriqiyah.android.rssreader.domain.MenuItemDaoFactory;
import com.ifriqiyah.android.rssreader.domain.MenuItemDao;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MenuUpdaterRunnable implements Runnable {

    private static final String MENU_JSON_FILE_URL = "http://ifriqiyah.com/android/menu.json";
    private static final String USER_AGENT = "RSS_READER_ANDROID_APP";

    @Override
    public void run() {
        HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
        HttpGet httpGet = new HttpGet(MENU_JSON_FILE_URL);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            List<MenuItem> menuItems = JsonStreamToMenuItemList.getMennuItems(inputStream);

            MenuItemDao menuItemDao = MenuItemDaoFactory.getMenuItemDao();
            for (MenuItem menuItem : menuItems) {
                menuItemDao.saveOrUpdate(menuItem);
                MenuItemIconDownloader.checkOrDownloadMenuItemIcons(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}