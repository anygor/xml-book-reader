package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.dao.XMLGetter;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
    @Bean
    public XMLGetter xmlGetter() {
        return new UrlXmlGetter();
    }
    @Bean
    public UrlXmlGetter urlXmlGetter() {
        return new UrlXmlGetter();
    }
    @Bean
    public BookRetrievingHandler bookRetrievingHandler() {
        return new BookRetrievingHandler();
    }
}
