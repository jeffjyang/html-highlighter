import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HtmlTag {
    
    private final String element;
    private final boolean isOpenTag;

    private static final Set<String> selfClosingTags = new HashSet<String>(
            Arrays.asList("!doctype", "!--", "?xml", "xml", "area", "base",
                          "basefont", "br", "col", "frame", "hr", "img",
                          "input", "link", "meta", "param"));
    
    public HtmlTag (String element, boolean isOpenTag) {
	this.element = element;
    	this.isOpenTag = isOpenTag;
    }
        
    public HtmlTag (String htmlTag) {
	if (htmlTag.charAt(1) == '/') {
	    this.isOpenTag = false;
	    htmlTag = htmlTag.substring(2);
	} else {
	    this.isOpenTag = true;
	    htmlTag = htmlTag.substring(1);
	}
	
	this.element = htmlTag.split("\\s|\\/|>")[0];	// split the string at any whitespace, /, or >

    }
    

    
    public boolean isOpenTag() {
	return isOpenTag;
    }
    
    public boolean isSelfClosing() {
    	return selfClosingTags.contains(element);
    }
    
    private String getElement() {
	return element;
    }
    
    public boolean matches(HtmlTag other) {
	if (other.isOpenTag() != isOpenTag) {
	    return element.equals(other.getElement());
	}
	return false;
    }
    
    @Override
    public String toString() {
    	// TODO verify 
	return "<" + (isOpenTag ? "" : "/") + element + ">";
    	
    }
    
    @Override
    public boolean equals(Object o) {
	if (o instanceof HtmlTag) {
	    HtmlTag other = (HtmlTag) o;
	    return element.equals(other.element) && isOpenTag == other.isOpenTag;
	} else {
	    return false;
	}
	
    }
    
}
