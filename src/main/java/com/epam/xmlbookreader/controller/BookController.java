package com.epam.xmlbookreader.controller;

import com.epam.xmlbookreader.dao.BookGetter;
import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.XMLCollectingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class BookController {

    @Value("${book.url}")
    private ArrayList<String> bookUrls;

    private ArrayList<String> xmlBooks = new ArrayList<>();

    @Autowired
    private UrlXmlGetter urlXmlGetter;

    @Autowired
    private XMLCollectingHandler xmlCollectingHandler;

    @Autowired
    private BookGetter bookGetter;

    private String rootTag = "root";
    private int bookPathMargin = 7;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Book> books() {
        List<Book> books = new LinkedList<>();
        for (String url : bookUrls) {
            xmlCollectingHandler.setXmlResult(new StringBuilder());
            xmlCollectingHandler.setUrl(url);
            String urlXml = urlXmlGetter.getXML(url);
            xmlCollectingHandler.appendContentLinkToResultIfExists(urlXml);
            String fullXml = "<" + rootTag + ">" + xmlCollectingHandler.getXmlResult() + "</" + rootTag + ">";
            Book book = bookGetter.getBookFromXml(fullXml);
            book.setTitle(url.substring(url.indexOf("/books/") + bookPathMargin));
            book.setId(book.getTitle().hashCode());
            books.add(book);
        }
        return books;
    }
}
