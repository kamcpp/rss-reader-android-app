package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ifriqiyah.android.rssreader.domain.MenuElementEntity;
import com.ifriqiyah.android.rssreader.domain.dao.BaseEntityDao;

import java.util.ArrayList;
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
        List<MenuElementEntity> menuElementEntities = new ArrayList<MenuElementEntity>();
        Cursor cursor = executeReader("select * from menu_element;");
        if(cursor != null && cursor.moveToFirst()) {
            do {
                MenuElementEntity menuElementEntity = new MenuElementEntity();
                menuElementEntity.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                menuElementEntity.setText(cursor.getString(cursor.getColumnIndex("element_text")));
                menuElementEntity.setEnglishText(cursor.getString(cursor.getColumnIndex("english_text")));
                menuElementEntity.setNewsRssURL(cursor.getString(cursor.getColumnIndex("news_rss_url")));
                menuElementEntity.setArticleRssURL(cursor.getString(cursor.getColumnIndex("article_rss_url")));
                menuElementEntity.setNotified(cursor.getInt(cursor.getColumnIndex("notified")) == 1);
                menuElementEntities.add(menuElementEntity);
            } while(cursor.moveToNext());
        }
        return menuElementEntities;
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
