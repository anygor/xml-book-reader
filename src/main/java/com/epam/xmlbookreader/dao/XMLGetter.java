package com.epam.xmlbookreader.dao;

import java.io.InputStream;

public interface XMLGetter {
    InputStream getXmlInputStream(String url);
}
