import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HtmlHighlighterTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    
    // some simple test inputs for manual verification of output
    @Test
    void test() {
	String input = "<html>\n" + 
		"<head>\n" + 
		"<title>HTML highlight test page</title>\n" + 
		"</head>\n" + 
		"<body>\n" + 
		"This is text in the body.\n" + 
		"<br>\n" + 
		"<h1>This is a heading</h1>\n" + 
		"<p>This is a paragraph.</p>\n" + 
		"There is more text in the body after the paragraph.\n" + 
		"</body>\n" + 
		"</html>";
	
	
	System.out.println(HtmlHighlighter.highlightHtml(input));
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
	
	String input2 = "<HTML>\n" + 
		"<HEAD><TITLE>Welcome to Capital One highlighter test</TITLE></HEAD>\n" + 
		"<BODY>\n" + 
		"<H1>Welcome to Capital One</H1>\n" + 
		"<P><A HREF=\"http://tech.capitalone.ca\">Visit Our Blog</A></P>\n" + 
		"</BODY>\n" + 
		"</HTML>";
	
	
	System.out.println(HtmlHighlighter.highlightHtml(input2));
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~");

	
	String input3 = "<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" + 
		"<html>\n" + 
		"<head>\n" + 
		"<title>Turtles are cool</title>\n" + 
		"<meta http-equiv=\"Content-Type\" content=\"text/html\">\n" + 
		"<link href=\"style.css\" type=\"text/css\"/>\n" + 
		"</head>\n" + 
		"\n" + 
		"<body>\n" + 
		"<p>Turtles swim in the <a href=\"http://ocean.com/\">ocean</a>.</p>\n" + 
		"<p>Some turtles are over 100 years old.  Here is a picture of a turtle:\n" + 
		"<img src=\"images/turtle.jpg\" width=\"100\" height=\"100\">\n" + 
		"</p>\n" + 
		"</body>\n" + 
		"</html>\n" ;
	
	System.out.println(HtmlHighlighter.highlightHtml(input3));
	
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~");

	
	String input4 = "";
	
	System.out.println(HtmlHighlighter.highlightHtml(input4));

	
	
    }

}
