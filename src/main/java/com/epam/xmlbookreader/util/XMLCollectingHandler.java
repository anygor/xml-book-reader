package com.epam.xmlbookreader.util;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

public class XMLCollectingHandler extends DefaultHandler {

    private final Logger logger = LogManager.getLogger(XMLCollectingHandler.class);

    @Autowired
    private UrlXmlGetter urlXmlGetter;

    private String url;

    private StringBuilder xmlResult;

    public String getContentLinkToResultIfExists(String xml) {
        try {
            xmlResult.append(xml.substring(xml.indexOf(">") + 1));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), this);
        } catch (SAXException | ParserConfigurationException e) {
            logger.error(e.getClass().toString() + " at getContentLinkToResultIfExists:" + e.getMessage());
        } catch (IOException e) {
            logger.error("IO exception at getContentLinkToResultIfExists:" + e.getMessage());
        }
        return xml;
    }

    public String getFullXml(String url) {
        this.url = url;
        xmlResult = new StringBuilder();
        String urlXml = urlXmlGetter.getXML(url);
        getContentLinkToResultIfExists(urlXml);
        return xmlResult.toString();
    }

    @Override
    public void processingInstruction(String target, String data) {
        if (target.equals("content-link")) {
            String xml = urlXmlGetter.getXML(url, data.substring(data.indexOf("\"")).replaceAll("\"", ""));
            getContentLinkToResultIfExists(xml);
        }
    }
}
