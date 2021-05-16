package com.epam.xmlbookreader.util;

import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.model.Section;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class BookRetrievingHandler extends DefaultHandler {

    private Book book;

    private boolean inSection = false;
    private boolean inTitle = false;
    private boolean inBody = false;

    private List<Section> sections;
    private Section section;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("root")) {
            sections = new LinkedList<>();
            book.setSections(sections);
        }
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
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("section")) {
            inSection = false;
            sections.add(section);
        }
        if (inSection) {
            if (qName.equals("title")) {
                inTitle = false;
            }
            else if (qName.equals("body")) {
                inBody = false;
            }
        }
        if (qName.equals("root")) {
            book.setSections(sections);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
