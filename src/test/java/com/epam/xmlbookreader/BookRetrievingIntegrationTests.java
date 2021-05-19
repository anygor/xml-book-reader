package com.epam.xmlbookreader;

import com.epam.xmlbookreader.dao.UrlXmlGetter;
import com.epam.xmlbookreader.model.Book;
import com.epam.xmlbookreader.util.BookRetrievingHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRetrievingIntegrationTests {

	@Autowired
	private BookRetrievingHandler bookRetrievingHandler;

	@Autowired
	private UrlXmlGetter urlXmlGetter;

	@Test
	void testGetBookFromStream() throws IOException {
		String url = "http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/far-far-away/";
		InputStream stream = urlXmlGetter.getXmlInputStream(url, "section-1.xml");
		Book actual = bookRetrievingHandler.getBookFromInputStream(url, stream);
		boolean firstSectionsAreEqual = actual.getSections().get(0).getTitle().equals
				("Introduction") &&
				actual.getSections().get(0).getBody().equals
						("Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia.");
		boolean secondSectionsAreEqual = actual.getSections().get(1).getTitle().equals
				("World of Grammar") &&
				actual.getSections().get(1).getBody().equals
						("It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.");
		boolean thirdSectionsAreEqual = actual.getSections().get(2).getTitle().equals
				("The Big Oxmox") &&
				actual.getSections().get(2).getBody().equals
						("The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way.");
		boolean fourthSectionsAreEqual = actual.getSections().get(3).getTitle().equals
				("Italic Mountains") &&
				actual.getSections().get(3).getBody().equals
						("When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy.");
		boolean fifthSectionsAreEqual = actual.getSections().get(4).getTitle().equals
				("Little Blind Text") &&
				actual.getSections().get(4).getBody().equals
						("The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again.");
		boolean sixthSectionsAreEqual = actual.getSections().get(5).getTitle().equals
				("Far Away") &&
				actual.getSections().get(5).getBody().equals
						("And if she hasn’t been rewritten, then they are still using her. Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One");
		stream.close();
		assertThat(firstSectionsAreEqual &&
				secondSectionsAreEqual &&
				thirdSectionsAreEqual &&
				fourthSectionsAreEqual &&
				fifthSectionsAreEqual &&
				sixthSectionsAreEqual &&
				actual.getSections().size() == 6).isTrue();
	}

}
