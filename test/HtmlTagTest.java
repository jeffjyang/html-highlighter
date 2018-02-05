import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HtmlTagTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
	HtmlTag headOpen = new HtmlTag("<head>");
	System.out.println(headOpen.toString());
	
	HtmlTag headClose = new HtmlTag("</head>");
	System.out.println(headClose.toString());
	System.out.println(headClose.isOpenTag());
	System.out.println(headClose.matches(headOpen));
	
	HtmlTag titleOpen = new HtmlTag("<title>");
	System.out.println(titleOpen.toString());
	System.out.println(titleOpen.isOpenTag());
	
	
    }

}
