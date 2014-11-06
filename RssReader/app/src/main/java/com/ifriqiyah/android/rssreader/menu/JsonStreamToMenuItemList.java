package com.ifriqiyah.android.rssreader.menu;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonStreamToMenuItemList {

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String ENGLISH_TEXT = "englishText";
    private static final String SMALL_ICON_URL = "smallIconURL";
    private static final String BIG_ICON_URL = "bigIconURL";
    private static final String SMALL_ICON_HASH = "smallIconHash";
    private static final String BIG_ICON_HASH = "bigIconHash";
    private static final String ARTICLE_RSS_URL = "articleRssURL";
    private static final String NEWS_RSS_URL = "newsRssURL";

    public static List<MenuElement> getMennuItems(InputStream inputStream) throws IOException {
        List<MenuElement> menuElements = new ArrayList<MenuElement>();

        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        reader.beginArray();
        while (reader.hasNext()) {
            menuElements.add(readMenuItem(reader));
        }
        reader.endArray();
        return menuElements;
    }

    private static MenuElement readMenuItem(JsonReader reader) throws IOException {
        int id = -1;
        String text = null;
        String englishText = null;
        String smallIconURL = null;
        String bigIconURL = null;
        String smallIconHash = null;
        String bigIconHash = null;
        String newsRssURL = null;
        String articleRssURL = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(ID)) {
                id = reader.nextInt();
            } else if (name.equals(TEXT)) {
                text = reader.nextString();
            } else if (name.equals(ENGLISH_TEXT)) {
                englishText = reader.nextString();
            } else if (name.equals(SMALL_ICON_URL)) {
                smallIconURL = reader.nextString();
            } else if (name.equals(BIG_ICON_URL)) {
                bigIconURL = reader.nextString();
            } else if (name.equals(SMALL_ICON_HASH)) {
                smallIconHash = reader.nextString();
            } else if (name.equals(BIG_ICON_HASH)) {
                bigIconHash = reader.nextString();
            } else if (name.equals(ARTICLE_RSS_URL)) {
                articleRssURL = reader.nextString();
            } else if (name.equals(NEWS_RSS_URL)) {
                newsRssURL = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new MenuElement(id, text, englishText, smallIconURL, bigIconURL, smallIconHash, bigIconHash, newsRssURL, articleRssURL);
    }
}
