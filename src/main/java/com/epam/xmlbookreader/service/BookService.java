package com.epam.xmlbookreader.service;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import com.epam.xmlbookreader.util.XMLCollectingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

	@Autowired
	private UrlXmlGetter urlXmlGetter;

	@Autowired
	private XMLCollectingHandler xmlCollectingHandler;

	@Autowired
	private BookRetrievingHandler bookRetrievingHandler;

	private String rootTag = "root";
	private int bookPathMargin = 7;

	public Book getBook(String url) {
		String fullXml = "<" + rootTag + ">" + xmlCollectingHandler.getFullXml(url) + "</" + rootTag + ">";
		Book book = bookRetrievingHandler.getBookFromXml(fullXml);
		book.setTitle(url.substring(url.indexOf("/books/") + bookPathMargin));
		book.setId(book.getTitle().hashCode());
		return book;
	}
}
