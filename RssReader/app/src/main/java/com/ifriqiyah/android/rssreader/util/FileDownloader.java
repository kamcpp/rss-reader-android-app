package com.ifriqiyah.android.rssreader.util;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

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
        Log.d("DEBUG", "Inside download method ...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("DEBUG", "Making httpClient ...");
                HttpClient httpClient = AndroidHttpClient.newInstance(USER_AGENT);
                Log.d("DEBUG", "Making httpGet for uri: '" + uri + "' ...");
                HttpGet httpGet = new HttpGet(uri);
                Log.d("DEBUG", "Ready to download ...");
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
            }
        });
        try {
            Log.d("DEBUG", "Starting ...");
            thread.start();
            thread.join();
            Log.d("DEBUG", "Done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
