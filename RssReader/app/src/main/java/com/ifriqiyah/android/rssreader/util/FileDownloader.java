package com.ifriqiyah.android.rssreader.util;

import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.ifriqiyah.android.rssreader.Constants.*;

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
        HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        InputStream inputStream = httpResponse.getEntity().getContent();
        try {
            OutputStream outputStream = new FileOutputStream(filePath);
            int b = -1;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
            outputStream.close();
        } catch (IOException e) {
            httpGet.abort();
            throw e;
        } finally {
            ((AndroidHttpClient)httpClient).close();
        }
    }
}
