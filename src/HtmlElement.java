import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HtmlElement {
    private final boolean isHtmlTag;
    
    private final String element;
    private final boolean isOpenTag;

    private final String text;
    
    
    private final Set<String> selfClosingTags = new HashSet<>(
	    Arrays.asList("TODO"));
    
    public HtmlElement(String text) {
	this.isHtmlTag = false;
    	this.text = text;

    	this.element = "";
    	this.isOpenTag = true;
    }
    
    public HtmlElement (String element, boolean isOpenTag) {
    	this.isHtmlTag = true;
	this.element = element;
    	this.text = "";
    	this.isOpenTag = isOpenTag;
    }
        
        
    public String toString() {
    	// TODO verify 

    	if (isOpenTag) {
    	    return "<" + element + ">" + text;
    	} else { 
    	    return text + "<" + element + " />";
    	}
    	
    }
    
    
    public boolean isSelfClosing() {
    	return selfClosingTags.contains(element);
    }
        

}
