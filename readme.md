# XML Reader

Before trying to launch the app make sure your have JDK 11 installed and environment variables set up.

Download the **xml-reader-app.jar** file and move it to any place that suits for you. For example: _C:\xml-reader-app.jar_

_Optional_ In the same folder create a file **application.properties** and put links to the books you want to parse, for example:

_book.url=http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/far-far-away/,http://ec2-52-91-150-126.compute-1.amazonaws.com/content/books/european-languages/_, 

For multiple links use comma without spaces.

Then in terminal go to the folder and run the app with _java -jar xml-reader-app.jar_ command.

To see the parsed json, go to _http://localhost:8080/books_ 



