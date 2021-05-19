package com.epam.xmlbookreader.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UrlXmlGetter implements XMLGetter {

    private final Logger logger = LogManager.getLogger(UrlXmlGetter.class);

    public InputStream getXmlInputStream(String urlParam, String section) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlParam + section);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            return connection.getInputStream();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream getXmlInputStream(String urlParam) {
        return getXmlInputStream(urlParam, "section-1.xml");
    }

}
