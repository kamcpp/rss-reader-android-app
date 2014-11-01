package com.ifriqiyah.android.rssreader;

import android.os.Environment;
import android.provider.ContactsContract;

public class Constants {

    public static final String SITE_NAME = "ifriqiyah";
    public static final String DOMAIN = SITE_NAME + ".com";
    public static final String BASE_URL = "http://" + DOMAIN;
    public static final String MENU_JSON_FILE_URL = BASE_URL + "/android/menu.json";
    public static final String USER_AGENT = "RSS_READER_ANDROID_APP";
    public static final String ICONS_BASE_URL = BASE_URL + "/android/icons";
    public static final String DOWNLOAD_DIR = Environment.getExternalStorageState() + "/ifapp";
}
