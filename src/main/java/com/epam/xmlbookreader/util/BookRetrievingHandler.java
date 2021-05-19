package com.epam.xmlbookreader.util;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.model.Section;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;

public class BookRetrievingHandler extends DefaultHandler {

    private final Logger logger = LogManager.getLogger(BookRetrievingHandler.class);

    private Book book;

    private boolean inSection = false;
    private boolean inTitle = false;
    private boolean inBody = false;

    private Section section;

    private String url;

    @Autowired
    private UrlXmlGetter urlXmlGetter;

    public Book getBookFromInputStream(String url, InputStream inputStream) {
        this.url = url;
        this.book = new Book();
        return getBookWithSectionsFromXmlInputStream(inputStream);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("section")) {
            section = new Section();
            inSection = true;
        }
        if (inSection) {
            if (qName.equals("title")) {
                inTitle = true;
            }
            if (qName.equals("body")) {
                inBody = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)  {
        if (qName.equals("section")) {
            inSection = false;
            if (book.getSections() == null) { book.setSections(new LinkedList<>()); }
            book.getSections().add(section);
        }
        if (inSection) {
            if (qName.equals("title")) {
                inTitle = false;
            }
            else if (qName.equals("body")) {
                inBody = false;
            }
        }
    }

    @Override
    public void processingInstruction(String target, String data) {
        if (target.equals("content-link")) {
            InputStream stream = urlXmlGetter.getXmlInputStream(url, data.substring(data.indexOf("\"")).replaceAll("\"", ""));
            this.getBookWithSectionsFromXmlInputStream(stream);
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                logger.error(e.getClass().toString() + " at processingInstruction: " + e.getMessage());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (inSection) {
            if (inTitle) {
                section.setTitle(new String(ch, start, length));
                section.setId(section.getTitle().hashCode());
            }
            else if (inBody) {
                section.setBody(new String(ch, start, length).trim());
            }
        }
    }

    public Book getBookWithSectionsFromXmlInputStream(InputStream inputStream) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);

        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error(e.getClass().toString() + " at getBookFromXml: " + e.getMessage());
        }
        return book;
    }
}
