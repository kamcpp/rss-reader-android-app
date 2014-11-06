package com.ifriqiyah.android.rssreader.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Sha1HashProvider implements HashProvider {
    @Override
    public String hash(String text) {
        return null;
    }

    @Override
    public String hash(File file) throws IOException {
        // InputStream fis = new FileInputStream(file);
        // return String.valueOf(DigestUtils.sha1(fis));
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1; ) {
                messageDigest.update(buffer, 0, read);
            }
            is.close();

            Formatter formatter = new Formatter();
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            String toReturn = formatter.toString();
            formatter.close();
            return toReturn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
