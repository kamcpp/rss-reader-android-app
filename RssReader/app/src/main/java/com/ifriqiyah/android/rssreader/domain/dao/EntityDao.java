package com.ifriqiyah.android.rssreader.domain.dao;

import java.util.List;

public interface EntityDao <E> {

    void saveOrUpdate(E entity);

    void delete(int id);

    List<E> getAll();

    E getById(int id);

    boolean exists(int id);
}
