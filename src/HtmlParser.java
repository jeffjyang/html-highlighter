import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
   
    public static List<HtmlElement> tokenize (StringBuffer buf) {
	List<HtmlElement> htmlElements = new ArrayList<>();
	
	while (true) {
	    HtmlElement element = nextTag(buf);
	    if (element == null) {
		break;
	    } else {
		htmlElements.add(element);
	    }
	}
	
	return htmlElements;
	
    }
    
    private static HtmlElement nextTag (StringBuffer buffer) {
	int indexStartTag = buffer.indexOf("<"); 
	int indexEndTag = buffer.indexOf(">");

	// this is an html tag
	if (indexStartTag == 0 && indexStartTag < indexEndTag) {
	    boolean isOpenTag;
	    String element;
		
	    // parse element 
	    if (buffer.charAt(1) == '/') {
		// if this is a closing tag
		// TODO is this even possible???
		element = buffer.substring(2, indexEndTag);
		isOpenTag = false;
	    } else {
		element = buffer.substring(1, indexEndTag);
		isOpenTag = true;
	    }
	    // delete the first tag 
	    buffer.delete(indexStartTag, indexEndTag + 1);
    		
	    return new HtmlElement(element, isOpenTag);

	} else {
	    // we know that this is not an html tag and instead is text 
	    
	    String text = buffer.substring(0, indexStartTag);
	    
	    buffer.delete(0, indexStartTag);
	    
	    return new HtmlElement(text);
	}

    }
    
    
    
    
}
