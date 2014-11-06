package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ifriqiyah.android.rssreader.domain.MenuElementEntity;
import com.ifriqiyah.android.rssreader.domain.dao.BaseEntityDao;

import java.util.List;

public class MenuElementDao extends BaseEntityDao<MenuElementEntity> {

    public MenuElementDao(Context context) {
        super(context);
    }

    @Override
    public void saveOrUpdate(MenuElementEntity menuElementEntity) {
        if (!exists(menuElementEntity.getId())) {
            Log.d("DEBUG", "Adding a new record ...");
            execute("insert into menu_element values (?, ?, ?, ?, ?, ?);",
                    new String[] {"" + menuElementEntity.getId(),
                    menuElementEntity.getText(),
                    menuElementEntity.getEnglishText(),
                    menuElementEntity.getArticleRssURL(),
                    menuElementEntity.getNewsRssURL(),
                    "0"});
        } else {
            execute("update menu_element set element_text = ?, english_text = ?, article_rss_url = ?, news_rss_url = ? where _id = ?",
                    new String[] {menuElementEntity.getText(),
                    menuElementEntity.getEnglishText(),
                    menuElementEntity.getArticleRssURL(),
                    menuElementEntity.getNewsRssURL(),
                    "" + menuElementEntity.getId()});
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<MenuElementEntity> getAll() {
        return null;
    }

    @Override
    public MenuElementEntity getById(int id) {
        return null;
    }

    @Override
    public boolean exists(int id) {
        return executeReader("select _id from menu_element where _id = " + id).getCount() > 0;
    }
}
