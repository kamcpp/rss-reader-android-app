package com.ifriqiyah.android.rssreader;

import com.ifriqiyah.android.rssreader.adapter.MenuElementAdapter;
import com.ifriqiyah.android.rssreader.adapter.MenuElementModel;
import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.domain.NewsItem;
import com.ifriqiyah.android.rssreader.domain.dao.ORMLiteOpenHelper;
import com.ifriqiyah.android.rssreader.reader.MenuElementReader;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true,
        injects = {MenuElementAdapter.class,
                MenuElementModel.class,
                MenuElementReader.class})
public class ApplicationModule {

    private ORMLiteOpenHelper ormLiteOpenHelper;

    public ApplicationModule() {
        ormLiteOpenHelper = new ORMLiteOpenHelper();
    }

    @Provides
    @Singleton
    public List<MenuElement> provideMenuElements() {
        return new ArrayList<MenuElement>();
    }

    @Provides
    @Singleton
    public Dao<MenuElement, Integer> provideMenuElementDao() {
        try {
            return ormLiteOpenHelper.getMenuElementDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    public Dao<NewsItem, Integer> provideMenuItemDao() {
        try {
            return ormLiteOpenHelper.getNewsItemDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
