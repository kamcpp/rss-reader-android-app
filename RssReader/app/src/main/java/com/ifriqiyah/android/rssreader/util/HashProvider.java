package com.ifriqiyah.android.rssreader.util;

import java.io.File;
import java.io.IOException;

public interface HashProvider {
    String hash(String text);

    String hash(File file) throws IOException;
}
