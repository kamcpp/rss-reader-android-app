package com.ifriqiyah.android.rssreader.util;

public class HashProviderFactory {
    public static HashProvider getHashPoProvider() {
        return new Sha1HashProvider();
    }
}
