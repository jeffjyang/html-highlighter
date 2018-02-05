import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HtmlTagTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
	HtmlTag tag = new HtmlTag("<head>");
	System.out.println(tag.toString());
	HtmlTag tag2 = new HtmlTag("</head>");
	System.out.print(tag2.isOpenTag());
	System.out.println(tag2.toString());
	HtmlTag tag3 = new HtmlTag("<head>");
	System.out.println(tag3.toString());
    }

}
