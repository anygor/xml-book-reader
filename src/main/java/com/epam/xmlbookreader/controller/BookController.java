package com.epam.xmlbookreader.controller;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@PropertySource("classpath:application.properties")
public class BookController {

    @Value("${book.url}")
    private ArrayList<String> bookUrls;

    private ArrayList<String> xmlBooks = new ArrayList<>();

    @Autowired
    private UrlXmlGetter urlXmlGetter;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "text/xml")
    @ResponseBody
    public String books() {
        for (String url : bookUrls) {
            xmlBooks.add(urlXmlGetter.getXML(url));
        }
        return xmlBooks.get(0);
    }
}
