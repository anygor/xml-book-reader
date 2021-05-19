package com.epam.xmlbookreader.dao;

import java.io.InputStream;
import java.util.function.Function;

public interface XMLGetter {
    <T> T getXmlInputStream(String url, Function<InputStream, T> consumer);
}
