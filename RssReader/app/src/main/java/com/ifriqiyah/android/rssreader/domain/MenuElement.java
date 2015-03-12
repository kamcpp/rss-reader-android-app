package com.ifriqiyah.android.rssreader.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class MenuElement {
    @DatabaseField(id = true, dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(dataType = DataType.STRING)
    private String text;
    @DatabaseField(dataType = DataType.STRING)
    private String englishText;
    @DatabaseField(dataType = DataType.STRING)
    private String articleRssURL;
    @DatabaseField(dataType = DataType.STRING)
    private String newsRssURL;
    @DatabaseField(dataType = DataType.STRING)
    private String smallIconUrl;
    @DatabaseField(dataType = DataType.STRING)
    private String bigIconUrl;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] smallIcon;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] bigIcon;
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean notified;

    public MenuElement(int id, String text, String englishText, String bigIconUrl, String smallIconUrl, String articleRssURL, String newsRssURL) {
        this.id = id;
        this.text = text;
        this.englishText = englishText;
        this.articleRssURL = articleRssURL;
        this.newsRssURL = newsRssURL;
        this.bigIconUrl = bigIconUrl;
        this.smallIconUrl = smallIconUrl;
    }

    public MenuElement() {
        // ORMLite use this
    }

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

    public String getSmallIconUrl() {
        return smallIconUrl;
    }

    public void setSmallIconUrl(String smallIconUrl) {
        this.smallIconUrl = smallIconUrl;
    }

    public String getBigIconUrl() {
        return bigIconUrl;
    }

    public void setBigIconUrl(String bigIconUrl) {
        this.bigIconUrl = bigIconUrl;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public byte[] getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(byte[] smallIcon) {
        this.smallIcon = smallIcon;
    }

    public byte[] getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(byte[] bigIcon) {
        this.bigIcon = bigIcon;
    }
}
