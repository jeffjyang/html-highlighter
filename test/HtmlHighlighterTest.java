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
	
	HtmlHighlighter highlighter = new HtmlHighlighter();
	
	System.out.println(highlighter.highlightHtml(input));
	
    }

}
