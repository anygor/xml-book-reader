package com.epam.xmlbookreader.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlXmlGetter implements XMLGetter {

    private final Logger logger = LogManager.getLogger(UrlXmlGetter.class);

    public String getXML(String urlParam, String section) {
        StringBuilder content;
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try {
            URL url = new URL(urlParam + section);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            content = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();

        } catch (MalformedURLException e) {
            logger.error("MalformedUrl at UrlXmlGetter - getXml(): " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at UrlXmlGetter - getXml(): " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("IOException at UrlXmlGetter - getXml() - close bufferedReader: " + e.getMessage());
                }
            }
        }
        return null;

    }

    @Override
    public String getXML(String urlParam) {
        return getXML(urlParam, "section-1.xml");
    }
}
