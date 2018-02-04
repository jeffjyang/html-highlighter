import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
   
    
    public static List<HtmlTag> tokenize (StringBuffer buf) {
	List<HtmlTag> tags = new ArrayList<>();
	while (true) {
	    HtmlTag tag = nextTag(buf);
	    if (tag == null) {
		break;
	    } else {
		tags.add(tag);
		
	    }
	    
	}
	
	return tags;
	
	
    }
    
    private static HtmlTag nextTag (StringBuffer buffer) {
	int indexStartTag = buffer.indexOf("<"); 
	int indexEndTag = buffer.indexOf(">");

	boolean isOpenTag;
	boolean isSelfClosing;
	String element;
	String text;
	
	if (indexStartTag == 0 && indexStartTag < indexEndTag) {
	    // if this is a closing tag 
	    if (buffer.charAt(1) == '/') {
		element = buffer.substring(2, indexEndTag);
		isOpenTag = false;
	    } else {
		element = buffer.substring(1, indexEndTag);
		isOpenTag = true;
	    }
	    
	    
	    
	} else {
	    return null;
	}
	
	// delete the first tag 
	buffer.delete(indexStartTag, indexEndTag + 1);

	
	
	int indexNextTag = buffer.indexOf("<");
	text = buffer.substring(0, indexNextTag);

	buffer.delete(0, indexNextTag);
	
	
	isSelfClosing = isSelfClosingTag(element);
	
	
	return new HtmlTag(element, text, isOpenTag, isSelfClosing);

	
    }
    
    
    


    
    private static boolean isSelfClosingTag(String element) {
	return false; // TODO 
    }
    
    
    
}
