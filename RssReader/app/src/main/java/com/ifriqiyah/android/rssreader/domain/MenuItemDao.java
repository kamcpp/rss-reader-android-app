package com.ifriqiyah.android.rssreader.domain;


import java.util.List;

public interface MenuItemDao {

    void saveOrUpdate(MenuItem menuItem);

    void delete(int id);

    List<MenuItem> getAll();

    MenuItem getById(int id);
}
