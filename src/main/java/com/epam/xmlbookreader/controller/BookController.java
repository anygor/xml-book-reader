package com.epam.xmlbookreader.controller;

import com.epam.xmlbookreader.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.epam.xmlbookreader.service.BookService;

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
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Book> books() {
        List<Book> books = new LinkedList<>();
        for (String url : bookUrls) {
            books.add(bookService.getBookWithStream(url));
        }
        return books;
    }
}
