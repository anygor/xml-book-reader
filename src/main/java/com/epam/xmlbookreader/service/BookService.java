package com.epam.xmlbookreader.service;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.dao.XMLGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class BookService {

	private final Logger logger = LogManager.getLogger(BookService.class);

	@Autowired
	private UrlXmlGetter urlXmlGetter;

	@Autowired
	private BookRetrievingHandler bookRetrievingHandler;

	private String rootTag = "root";
	private int bookPathMargin = 7;

	public Book getBookWithStream(String url) {
		InputStream inputStream = urlXmlGetter.getXmlInputStream(url);
		Book book = bookRetrievingHandler.getBookFromInputStream(url, inputStream);
		book.setTitle(url.substring(url.indexOf("/books/") + bookPathMargin));
		book.setId(book.getTitle().hashCode());
		try {
			inputStream.close();
		} catch (IOException e) {
			logger.error("IO Exception at getBookWithStream: " + e.getMessage());
		}
		return book;
	}
}

