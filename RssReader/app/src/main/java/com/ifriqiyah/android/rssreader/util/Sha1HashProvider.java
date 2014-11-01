package com.ifriqiyah.android.rssreader.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sha1HashProvider implements HashProvider {
    @Override
    public String hash(String text) {
        return null;
    }

    @Override
    public String hash(File file) throws IOException {
        InputStream fis = new FileInputStream(file);
        return String.valueOf(DigestUtils.sha1(fis));
    }
}
