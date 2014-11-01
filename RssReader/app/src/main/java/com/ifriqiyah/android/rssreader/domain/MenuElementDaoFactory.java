package com.ifriqiyah.android.rssreader.domain;

public class MenuElementDaoFactory {
    public static MenuElementDao getMenuItemDao() {
        return new SqliteMenuElementDao();
    }
}
