package com.ifriqiyah.android.rssreader.util;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import static com.ifriqiyah.android.rssreader.Constants.*;

public class FileDownloader {

    private String uri;
    private OutputStream out;
    private boolean makeHashFile; // TODO
    private boolean closeStream;

    public FileDownloader(String uri, OutputStream out) {
        this(uri, out, false, true);
    }

    public FileDownloader(String uri, OutputStream out, boolean makeHashFile) {
        this(uri, out, makeHashFile, true);
    }

    public FileDownloader(String uri, OutputStream out, boolean makeHashFile, boolean closeStream) {
        this.uri = uri;
        this.out = out;
        this.makeHashFile = makeHashFile;
        this.closeStream = closeStream;
    }

    public void download() throws IOException {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
                HttpGet httpGet = new HttpGet(uri);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    InputStream inputStream = httpResponse.getEntity().getContent();
                    int b = -1;
                    while ((b = inputStream.read()) != -1) {
                        out.write(b);
                    }
                    if (closeStream) {
                        // out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    httpGet.abort();
                } finally {
                    ((AndroidHttpClient) httpClient).close();
                }
                return null;
            }
        }.execute();
        try {
            asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
