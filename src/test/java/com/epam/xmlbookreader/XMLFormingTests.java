package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.util.XMLCollectingHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class XMLFormingTests {

	@Autowired
	private XMLCollectingHandler xmlCollectingHandler;

	@MockBean
	private UrlXmlGetter urlXmlGetter;

	@Test
	void getFullXmlTest() {
		String url = "http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/european-languages/";
		Mockito.when(urlXmlGetter.getXML(url))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Introduction</title>        <body>        Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia.        </body>    </section>    <?content-link file=\"section-2.xml\"?></content>");
		Mockito.when(urlXmlGetter.getXML(url, "section-2.xml"))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>World of Grammar</title>        <body>        It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.        </body>    </section>    <?content-link file=\"section-3.xml\"?></content>");
		Mockito.when(urlXmlGetter.getXML(url, "section-3.xml"))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>The Big Oxmox</title>        <body>        The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way.        </body>    </section>    <?content-link file=\"section-4.xml\"?></content>");
		Mockito.when(urlXmlGetter.getXML(url, "section-4.xml"))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Italic Mountains</title>        <body>        When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy.        </body>    </section>    <?content-link file=\"section-5.xml\"?></content>");
		Mockito.when(urlXmlGetter.getXML(url, "section-5.xml"))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Little Blind Text</title>        <body>        The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again.        </body>    </section>    <?content-link file=\"section-6.xml\"?></content>");
		Mockito.when(urlXmlGetter.getXML(url, "section-6.xml"))
				.thenReturn("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><content>    <section>        <title>Far Away</title>        <body>        And if she hasn’t been rewritten, then they are still using her. Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One        </body>    </section></content>");

		String fullXmlExpected = "<root><content>    <section>        <title>Introduction</title>        <body>        Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia.        </body>    </section>    <?content-link file=\"section-2.xml\"?></content><content>    <section>        <title>World of Grammar</title>        <body>        It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.        </body>    </section>    <?content-link file=\"section-3.xml\"?></content><content>    <section>        <title>The Big Oxmox</title>        <body>        The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way.        </body>    </section>    <?content-link file=\"section-4.xml\"?></content><content>    <section>        <title>Italic Mountains</title>        <body>        When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy.        </body>    </section>    <?content-link file=\"section-5.xml\"?></content><content>    <section>        <title>Little Blind Text</title>        <body>        The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again.        </body>    </section>    <?content-link file=\"section-6.xml\"?></content><content>    <section>        <title>Far Away</title>        <body>        And if she hasn’t been rewritten, then they are still using her. Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One        </body>    </section></content></root>";
		String fullXmlActual = "<root>" + xmlCollectingHandler.getFullXml(url) + "</root>";
		assertThat(fullXmlExpected.equals(fullXmlActual)).isTrue();

	}
}
