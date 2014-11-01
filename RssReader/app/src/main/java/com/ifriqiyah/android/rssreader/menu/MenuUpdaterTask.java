package com.ifriqiyah.android.rssreader.menu;


import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.domain.MenuElementDao;
import com.ifriqiyah.android.rssreader.domain.MenuElementDaoFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.ifriqiyah.android.rssreader.Constants.*;

public class MenuUpdaterTask extends AsyncTask<Void, Void, List<MenuElement>> {
    @Override
    protected List<MenuElement> doInBackground(Void... voids) {
        HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
        HttpGet httpGet = new HttpGet(MENU_JSON_FILE_URL);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            List<MenuElement> menuElements = JsonStreamToMenuItemList.getMennuItems(inputStream);

            MenuElementDao menuElementDao = MenuElementDaoFactory.getMenuItemDao();
            for (MenuElement menuElement : menuElements) {
                menuElementDao.saveOrUpdate(menuElement);
                MenuItemIconDownloader.checkOrDownloadMenuItemIcons(menuElement);
            }
            return menuElements;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ((AndroidHttpClient)httpClient).close();
        }
    }
}