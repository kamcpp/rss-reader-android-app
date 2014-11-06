package com.ifriqiyah.android.rssreader.menu;


import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.ifriqiyah.android.rssreader.domain.dao.DaoFactory;
import com.ifriqiyah.android.rssreader.domain.dao.EntityDao;
import com.ifriqiyah.android.rssreader.domain.MenuElementEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.ifriqiyah.android.rssreader.Constants.*;

public class MenuUpdaterTask extends AsyncTask<Void, Void, List<MenuElement>> {

    private Context context;

    public MenuUpdaterTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<MenuElement> doInBackground(Void... voids) {
        HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
        HttpGet httpGet = new HttpGet(MENU_JSON_FILE_URL);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            List<MenuElement> menuElements = JsonStreamToMenuItemList.getMennuItems(inputStream);

            EntityDao<MenuElementEntity> entityDao = new DaoFactory<MenuElementEntity>().create(context, MenuElementEntity.class);
            for (MenuElement menuElement : menuElements) {
                entityDao.saveOrUpdate(convertMenuElementToMenuElementEntity(menuElement));
                MenuItemIconDownloader.checkOrDownloadMenuItemIcons(menuElement);
            }
            return menuElements;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ((AndroidHttpClient) httpClient).close();
        }
    }

    private MenuElementEntity convertMenuElementToMenuElementEntity(MenuElement menuElement) {
        MenuElementEntity menuElementEntity = new MenuElementEntity();
        menuElementEntity.setId(menuElement.getId());
        menuElementEntity.setText(menuElement.getText());
        menuElementEntity.setEnglishText(menuElement.getEnglishText());
        menuElementEntity.setArticleRssURL(menuElement.getArticleRssURL());
        menuElementEntity.setNewsRssURL(menuElement.getNewsRssURL());
        menuElementEntity.setNotified(false);
        return menuElementEntity;
    }
}