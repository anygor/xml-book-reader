package com.epam.xmlbookreader.dao;

import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

public class BookGetter {

    @Autowired
    private BookRetrievingHandler bookRetrievingHandler;

    public Book getBookFromXml(String xml) {
        Book book;
        try {
            book = new Book();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            bookRetrievingHandler.setBook(book);
            parser.parse(new InputSource(new StringReader(xml)), bookRetrievingHandler);

        } catch (IOException | ParserConfigurationException | SAXException e) {
            book = null;
            e.printStackTrace();
        }
        return book;
    }

}
