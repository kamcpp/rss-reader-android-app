package com.ifriqiyah.android.rssreader.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class NewsItem {
    @DatabaseField(id = true , dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(dataType = DataType.INTEGER)
    private int menuId;
    @DatabaseField(dataType = DataType.STRING)
    private String sourceId;
    @DatabaseField(dataType = DataType.LONG)
    private long publishDate;
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isArticle;
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isFavorite;
    @DatabaseField(dataType = DataType.STRING)
    private String title;
    @DatabaseField(dataType = DataType.STRING)
    private String source;
    @DatabaseField(dataType = DataType.STRING)
    private String link;
    @DatabaseField(dataType = DataType.STRING)
    private String details;
    @DatabaseField(dataType = DataType.STRING)
    private String enclosureURL;
    @DatabaseField(dataType = DataType.STRING)
    private String category;
    @DatabaseField(dataType = DataType.STRING)
    private String author;
    @DatabaseField(dataType = DataType.STRING)
    private String comments;
    @DatabaseField(dataType = DataType.STRING)
    private String rTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isArticle() {
        return isArticle;
    }

    public void setArticle(boolean isArticle) {
        this.isArticle = isArticle;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEnclosureURL() {
        return enclosureURL;
    }

    public void setEnclosureURL(String enclosureURL) {
        this.enclosureURL = enclosureURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getrTitle() {
        return rTitle;
    }

    public void setrTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
