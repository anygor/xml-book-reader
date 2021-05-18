package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class XMLGettingTests {

	@Autowired
	private UrlXmlGetter urlXmlGetter;

	@Test
	void testGetXmlNoParameter() {
		String url = "http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/far-far-away/";
		String actualXml = urlXmlGetter.getXML(url);
		String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Introduction</title>        <body>        Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia.        </body>    </section>    <?content-link file=\"section-2.xml\"?></content>";
		assertThat(expectedXml.equals(actualXml)).isTrue();
	}

	@Test
	void testGetXmlSpecificSection() {
		String url = "http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/far-far-away/";
		String section = "section-4.xml";
		String actualXml = urlXmlGetter.getXML(url, section);
		String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Italic Mountains</title>        <body>        When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy.        </body>    </section>    <?content-link file=\"section-5.xml\"?></content>";
		assertThat(expectedXml.equals(actualXml)).isTrue();
	}
}
