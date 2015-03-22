package com.ifriqiyah.android.rssreader.adapter;

import com.ifriqiyah.android.rssreader.MyApplication;
import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.j256.ormlite.dao.Dao;

import org.labcrypto.avicenna.Avicenna;
import org.labcrypto.avicenna.InjectHere;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuElementModel {

    @InjectHere
    List<MenuElement> menuElements;

    @InjectHere
    Dao<MenuElement, Integer> menuElementDao;

    public MenuElementModel() {
        Avicenna.inject(this);
    }

    public void refresh() {
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
