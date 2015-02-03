package com.ifriqiyah.android.rssreader.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.ifriqiyah.android.rssreader.MyApplication;
import com.ifriqiyah.android.rssreader.domain.MenuElementEntity;
import com.ifriqiyah.android.rssreader.domain.NewsItemEntity;
import com.ifriqiyah.android.rssreader.domain.dao.MenuElementDao;
import com.ifriqiyah.android.rssreader.util.NewsItemSync;
import com.ifriqiyah.android.rssreader.util.RssFetcher;

import java.util.List;

public class RssFetcherService extends Service {

    private MenuElementDao menuElementDao = new MenuElementDao(MyApplication.getContext());
    private RssFetcher rssFetcher = new RssFetcher();
    private NewsItemSync newsItemSync = new NewsItemSync();
    private Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateNews();
                handler.postDelayed(this, 30000);
            }
        }, 1000);
        return START_STICKY;
    }

    private void updateNews() {
        List<MenuElementEntity> menuElementEntities = menuElementDao.getAll();
        for (MenuElementEntity menuElementEntity : menuElementEntities) {
            List<NewsItemEntity> newsItemEntities = rssFetcher.fetch(menuElementEntity.getNewsRssURL(), menuElementEntity.getId(), false);
            newsItemSync.sync(newsItemEntities);
            newsItemEntities = rssFetcher.fetch(menuElementEntity.getArticleRssURL(), menuElementEntity.getId(), true);
            newsItemSync.sync(newsItemEntities);
        }
    }
}
