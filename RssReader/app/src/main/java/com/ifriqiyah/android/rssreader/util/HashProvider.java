package com.ifriqiyah.android.rssreader.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface HashProvider {
    String hash(String text);

    String hash(File file) throws IOException;
}
