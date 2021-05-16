package com.epam.xmlbookreader.controller;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.XMLHandler;
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
    private XMLHandler xmlHandler;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "text/xml")
    @ResponseBody
    public String books() {
        List<Book> books = new LinkedList<>();
        for (String url : bookUrls) {
            Book book = new Book();
            xmlHandler.setUrl(url);
            xmlHandler.containsContentLink(urlXmlGetter.getXML(url));
            String xml = xmlHandler.getXmlResult();
            System.out.println(xml);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        return xmlBooks.get(0);
    }
}
