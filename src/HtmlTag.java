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
    /**
     * HtmlTag constructor.
     * 
     * @param element
     * 		represents the HTML tag (i.e. "p" or "h2")
     * @param isOpenTag
     * 		indicates if the HTML tag is an opening tag or closing tag.
     * effects: creates an HtmlTag object for the provided tag.
     */
    public HtmlTag (String element, boolean isOpenTag) {
	this.element = element;
    	this.isOpenTag = isOpenTag;
    }
        
    /**
     * HtmlTag constructor. Will automatically parse the tag for the element 
     * and whether or not the tag is an opening tag or closing tag. 
     * 
     * @param htmlTag
     * 		full string representation of the HTML tag (i.e. "<p>" or "<head>").
     * 		requires: htmlTag to be a valid representation of an HTML tag.
     * effects: creates an HtmlTag object for the provided tag.
     */
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
    

    /**
     * Checks if this tag is an opening tag. Self-closing tags are considered
     * opening tags.
     * 
     * @return true if the tag is an opening tag, false otherwise.
     */
    public boolean isOpenTag() {
	return isOpenTag;
    }
    
    /**
     * Checks if the HtmlTag is self-closing.
     * 
     * @return true if the tag is self closing, false otherwise.
     */
    public boolean isSelfClosing() {
    	return selfClosingTags.contains(element);
    }
    
    /**
     * Gets the element of the HtmlTag.
     * 
     * @return the element of the HtmlTag.
     */
    public String getElement() {
	return element;
    }
    
    /**
     * Checks if this HtmlTag matches the other HtmlTag. Two HtmlTags are considered 
     * matching if they have the same element and opposite types (i.e. one is an opening
     * tag and the other is a closing tag).
     * 
     * @return true if this HtmlTag matches the other HtmlTag.
     */
    public boolean matches(HtmlTag other) {
	if (other.isOpenTag() != isOpenTag) {
	    return element.equals(other.getElement());
	}
	return false;
    }
    
    /**
     * Returns a string representation of the HtmlTag (e.g. "</body>").
     * 
     * @return a string representation of the HtmlTag.
     */
    @Override
    public String toString() {
    	// TODO verify 
	return "<" + (isOpenTag ? "" : "/") + element + ">";
    	
    }
    
    /**
     * Checks if two HtmlTags are equal. Two HtmlTags are considered equal
     * if they have the same element and same type (i.e. both opening tags or 
     * both closing tags).
     * 
     * @return true if this HtmlTag is equal to o, false otherwise.
     */
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
