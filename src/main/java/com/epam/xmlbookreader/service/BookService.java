package com.epam.xmlbookreader.service;

import com.epam.xmlbookreader.dao.XMLGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.function.Function;

@Component
public class BookService {

	private final Logger logger = LogManager.getLogger(BookService.class);

	@Autowired
	private XMLGetter urlXmlGetter;

	@Autowired
	private BookRetrievingHandler bookRetrievingHandler;

	private String rootTag = "root";
	private int bookPathMargin = 7;

	public Book getBookWithStream(String url) {
		return urlXmlGetter.getXmlInputStream(url, new Function<InputStream, Book>() {
			@Override
			public Book apply(InputStream inputStream) {
				Book book = bookRetrievingHandler.getBookFromInputStream(url, inputStream);
				book.setTitle(url.substring(url.indexOf("/books/") + bookPathMargin));
				book.setId(book.getTitle().hashCode());
				return book;
			}
		});
	}
}

