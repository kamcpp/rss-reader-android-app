package com.ifriqiyah.android.rssreader.domain;

public class MenuElementEntity {

    private int id;
    private String text;
    private String englishText;
    private String articleRssURL;
    private String newsRssURL;
    private boolean notified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getArticleRssURL() {
        return articleRssURL;
    }

    public void setArticleRssURL(String articleRssURL) {
        this.articleRssURL = articleRssURL;
    }

    public String getNewsRssURL() {
        return newsRssURL;
    }

    public void setNewsRssURL(String newsRssURL) {
        this.newsRssURL = newsRssURL;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }
}
