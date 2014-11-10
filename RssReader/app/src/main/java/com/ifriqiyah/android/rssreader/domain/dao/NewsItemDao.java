package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;

import com.ifriqiyah.android.rssreader.domain.NewsItemEntity;

import java.util.List;

public class NewsItemDao extends BaseEntityDao<NewsItemEntity> {

    public NewsItemDao(Context context) {
        super(context);
    }

    @Override
    public void saveOrUpdate(NewsItemEntity entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<NewsItemEntity> getAll() {
        return null;
    }

    @Override
    public NewsItemEntity getById(int id) {
        return null;
    }

    @Override
    public boolean exists(int id) {
        return false;
    }
}
