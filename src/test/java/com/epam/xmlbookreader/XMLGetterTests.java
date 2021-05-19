package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootTest
public class XMLGetterTests {

	@Autowired
	private UrlXmlGetter urlXmlGetter;

	@Test
	void getXmlTest() throws IOException {
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>The European Languages</title>        <body>        The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators.        </body>    </section>    <?content-link file=\"section-2.xml\"?></content>";
		String url = "http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/european-languages/";
		InputStream stream = urlXmlGetter.getXmlInputStream(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		assertThat(content.toString().equals(expected)).isTrue();
	}
}
