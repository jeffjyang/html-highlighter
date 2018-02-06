import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HtmlHighlighterTest {

    @BeforeEach
    void setUp() throws Exception {
    }

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
	
    }

}
