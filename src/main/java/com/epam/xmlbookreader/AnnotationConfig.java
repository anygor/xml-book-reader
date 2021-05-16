package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.util.XMLHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
    @Bean
    public UrlXmlGetter urlXmlGetter() {
        return new UrlXmlGetter();
    }
    @Bean
    public XMLHandler xmlHandler() {
        return new XMLHandler();
    }
}
