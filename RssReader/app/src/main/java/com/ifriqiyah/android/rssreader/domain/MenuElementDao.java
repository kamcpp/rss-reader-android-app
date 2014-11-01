package com.ifriqiyah.android.rssreader.domain;


import java.util.List;

public interface MenuElementDao {

    void saveOrUpdate(MenuElement menuElement);

    void delete(int id);

    List<MenuElement> getAll();

    MenuElement getById(int id);
}
