package com.epam.xmlbookreader.dao;

import java.io.InputStream;

public interface XMLGetter {
    String getXML(String url);
    InputStream getXmlInputStream(String url);
}
