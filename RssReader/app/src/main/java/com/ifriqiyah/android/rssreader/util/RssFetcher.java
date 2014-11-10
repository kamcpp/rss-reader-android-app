package com.ifriqiyah.android.rssreader.util;

import android.util.Log;

import com.ifriqiyah.android.rssreader.domain.NewsItemEntity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssFetcher {

    private List<NewsItemEntity> newsItemEntities;
    private NewsItemEntity newsItemEntity;

    public List<NewsItemEntity> fetch(String url, int menuElementId, boolean isArticle) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Log.d("DEBUG", "Downloading from '" + url + "'");
            new FileDownloader(url, out).download();
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(in, new DefaultHandler() {
                @Override
                public void startDocument() throws SAXException {
                    newsItemEntities = new ArrayList<NewsItemEntity>();
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("item")) {
                        newsItemEntity = new NewsItemEntity();
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    Log.d("DEBUG", "QQQQQNAAAAMEEEE: '" + qName + "'");
                    if (qName.equals("item")) {
                        newsItemEntities.add(newsItemEntity);
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String value = new String(ch, start, length);
                    Log.d("DEBUG", "Value: '" + value + "'");
                    if (value.equals("id")) {
                        newsItemEntity.setId(Integer.parseInt(value));
                    }

                }
            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("DEBUG", "Size of returning list: " + newsItemEntities.size());
        for (NewsItemEntity newsItemEntity1 : newsItemEntities) {
            // Log.d("DEBUG", newsItemEntity1.toString());
        }
        return newsItemEntities;
    }
}
