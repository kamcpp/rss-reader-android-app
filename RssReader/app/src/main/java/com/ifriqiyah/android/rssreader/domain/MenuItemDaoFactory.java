package com.ifriqiyah.android.rssreader.domain;

import com.ifriqiyah.android.rssreader.domain.MenuItemDao;
import com.ifriqiyah.android.rssreader.domain.SqliteMenuItemDao;

public class MenuItemDaoFactory {
    public static MenuItemDao getMenuItemDao() {
        return new SqliteMenuItemDao();
    }
}
