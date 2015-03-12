package com.ifriqiyah.android.rssreader.adapter;

import com.ifriqiyah.android.rssreader.MyApplication;
import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MenuElementModel {

    @Inject
    List<MenuElement> menuElements;

    @Inject
    Dao<MenuElement, Integer> menuElementDao;

    public MenuElementModel() {
        MyApplication.getObjectGraph().inject(this);
    }

    public void refresh() {
        if (menuElements == null) {
            menuElements = new ArrayList<MenuElement>();
        }
        try {
            List<MenuElement> newMenuElements = menuElementDao.queryForAll();
            menuElements.clear();
            for (MenuElement menuElement : newMenuElements) {
                menuElements.add(menuElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
