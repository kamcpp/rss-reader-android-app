package com.ifriqiyah.android.rssreader.util;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpHelper {

    public interface DataDownloadFinishListener {
        void downloadFinished(byte[] data);
    }

    public interface StringDownloadFinishedListener {
        void stringDownloadFinished(String data);
    }

    public interface FileDownloadFinishedListener {
        void fileDownloadFinished(String path);
    }

    public byte[] downloadAsByteArray(final String url) throws IOException {
        try {
            AsyncTask<Void, Void, byte[]> asyncTask = new AsyncTask<Void, Void, byte[]>() {
                @Override
                protected byte[] doInBackground(Void... params) {
                    HttpGet httpGet = null;
                    try {
                        HttpClient httpClient = AndroidHttpClient.newInstance("");
                        httpGet = new HttpGet(url);
                        HttpResponse httpResponse = httpClient.execute(httpGet);
                        if (httpResponse.getStatusLine().getStatusCode() != 200) {
                            return null;
                        }
                        return EntityUtils.toByteArray(httpResponse.getEntity());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (httpGet != null) {
                                httpGet.abort();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            };
            asyncTask.execute(new Void[]{});
            return asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void downloadAsByteArrayAsync(final String url, final DataDownloadFinishListener dataDownloadFinishListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] data = downloadAsByteArray(url);
                    if (dataDownloadFinishListener != null) {
                        dataDownloadFinishListener.downloadFinished(data);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public String downloadAsString(final String url, String charSet) throws IOException {
        return new String(downloadAsByteArray(url), charSet);
    }

    public void downloadAsStringAsync(final String url, final String charSet, final StringDownloadFinishedListener stringDownloadFinishedListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String string = downloadAsString(url, charSet);
                    if (stringDownloadFinishedListener != null) {
                        stringDownloadFinishedListener.stringDownloadFinished(string);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void downloadToFile(String url, String filePath) throws IOException {
        byte[] responseByteArray = downloadAsByteArray(url);
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(responseByteArray);
        outputStream.close();
    }

    public void downloadToFileAsync(final String url, final String filePath, final FileDownloadFinishedListener fileDownloadFinishedListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downloadToFile(url, filePath);
                    if (fileDownloadFinishedListener != null) {
                        fileDownloadFinishedListener.fileDownloadFinished(filePath);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
