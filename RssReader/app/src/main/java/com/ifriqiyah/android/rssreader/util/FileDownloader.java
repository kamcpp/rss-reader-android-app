package com.ifriqiyah.android.rssreader.util;

import java.io.IOException;

public class FileDownloader {

    private String uri;
    private String filePath;
    private boolean makeHashFile;

    public FileDownloader(String uri, String filePath, boolean makeHashFile) {
        this.uri = uri;
        this.filePath = filePath;
        this.makeHashFile = makeHashFile;
    }

    public void download() throws IOException {

    }
}
