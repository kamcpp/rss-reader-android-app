package com.ifriqiyah.android.rssreader.domain.dao;

import android.content.Context;

import com.ifriqiyah.android.rssreader.domain.MenuElementEntity;

public class DaoFactory <E> {
    public EntityDao <E> create(Context context, Class<E> clazz) {
        if (clazz == MenuElementEntity.class) {
            return (EntityDao<E>)(new MenuElementDao(context));
        }
        throw new IllegalArgumentException();
    }
}
