package com.ifriqiyah.android.rssreader.menu;

public class MenuElement {

    private int id;
    private String text;
    private String englishText;
    private String smallIconURL;
    private String bigIconURL;
    private String smallIconHash;
    private String bigIconHash;
    private String newsRssURL;
    private String articleRssURL;

    public MenuElement(int id, String text, String englishText, String smallIconURL, String bigIconURL, String smallIconHash, String bigIconHash, String newsRssURL, String articleRssURL) {
        this.id = id;
        this.text = text;
        this.englishText = englishText;
        this.smallIconURL = smallIconURL;
        this.bigIconURL = bigIconURL;
        this.smallIconHash = smallIconHash;
        this.bigIconHash = bigIconHash;
        this.newsRssURL = newsRssURL;
        this.articleRssURL = articleRssURL;
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

    public String getSmallIconURL() {
        return smallIconURL;
    }

    public void setSmallIconURL(String smallIconURL) {
        this.smallIconURL = smallIconURL;
    }

    public String getBigIconURL() {
        return bigIconURL;
    }

    public void setBigIconURL(String bigIconURL) {
        this.bigIconURL = bigIconURL;
    }

    public String getSmallIconHash() {
        return smallIconHash;
    }

    public void setSmallIconHash(String smallIconHash) {
        this.smallIconHash = smallIconHash;
    }

    public String getBigIconHash() {
        return bigIconHash;
    }

    public void setBigIconHash(String bigIconHash) {
        this.bigIconHash = bigIconHash;
    }

    public String getNewsRssURL() {
        return newsRssURL;
    }

    public void setNewsRssURL(String newsRssURL) {
        this.newsRssURL = newsRssURL;
    }

    public String getArticleRssURL() {
        return articleRssURL;
    }

    public void setArticleRssURL(String articleRssURL) {
        this.articleRssURL = articleRssURL;
    }
}
