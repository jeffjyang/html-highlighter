import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HtmlTag {
    
    private final String element;
    private final boolean isOpenTag;

    private static final Set<String> selfClosingTags = new HashSet<>(
	    Arrays.asList("TODO"));
    
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
    
        
    public String toString() {
    	// TODO verify 
	return "<" + (isOpenTag ? "" : "/") + element + ">";
    	
    }
    
    public boolean isOpenTag() {
	return isOpenTag;
    }
    
    public boolean isSelfClosing() {
    	return selfClosingTags.contains(element);
    }
        
    public boolean matches(HtmlTag other) {
	if (other.isOpenTag() != isOpenTag) {
	    return element.equals(element);
	}
	return false;
    }
    
}
