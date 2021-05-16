package com.epam.xmlbookreader.util;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

public class XMLHandler extends DefaultHandler {

    @Autowired
    private UrlXmlGetter urlXmlGetter;

    @Autowired
    private XMLHandler handler;

    private String url;

    private StringBuilder xmlResult = new StringBuilder();

    public boolean containsContentLink(String xml) {
        try {
            xmlResult.append(xml);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), handler);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void processingInstruction(String target, String data) {
        if (target.equals("content-link")) {
            String xml = urlXmlGetter.getXML(url, data.substring(data.indexOf("\"")).replaceAll("\"", ""));
            containsContentLink(xml);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getXmlResult() {
        return xmlResult.toString();
    }

    public void setXmlResult(StringBuilder xmlResult) {
        this.xmlResult = xmlResult;
    }
}
