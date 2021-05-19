package com.epam.xmlbookreader.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.function.Function;

public class UrlXmlGetter implements XMLGetter {

    private final Logger logger = LogManager.getLogger(UrlXmlGetter.class);

    public <T> T getXmlInputStream(String urlParam, String section, Function<InputStream, T> inputStreamConsumer) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlParam + section);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try (InputStream inputStream = connection.getInputStream()) {
                return inputStreamConsumer.apply(inputStream);
            }
        } catch (ProtocolException e) {
            logger.error(e.getClass().toString() + " at " + "getInputStream: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            logger.error(e.getClass().toString() + " at " + "getInputStream: " + e.getMessage());
        } catch (IOException e) {
            logger.error(e.getClass().toString() + " at " + "getInputStream: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    public <T> T getXmlInputStream(String urlParam, Function<InputStream, T> inputStreamConsumer ) {
        return getXmlInputStream(urlParam, "section-1.xml", inputStreamConsumer);
    }

}
