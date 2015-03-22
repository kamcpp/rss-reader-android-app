package com.ifriqiyah.android.rssreader;

import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.domain.NewsItem;
import com.ifriqiyah.android.rssreader.domain.dao.ORMLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import org.labcrypto.avicenna.Dependency;
import org.labcrypto.avicenna.DependencyFactory;
import org.labcrypto.avicenna.Singleton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@DependencyFactory
public class ApplicationDF {

    private ORMLiteOpenHelper ormLiteOpenHelper;

    public ApplicationDF() {
        ormLiteOpenHelper = new ORMLiteOpenHelper();
    }

    @Dependency
    @Singleton
    public List<MenuElement> provideMenuElements() {
        return new ArrayList<MenuElement>();
    }

    @Dependency
    @Singleton
    public Dao<MenuElement, Integer> provideMenuElementDao() {
        try {
            return ormLiteOpenHelper.getMenuElementDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Dependency
    @Singleton
    public Dao<NewsItem, Integer> provideNewsItemDao() {
        try {
            return ormLiteOpenHelper.getNewsItemDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
