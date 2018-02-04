import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HtmlTag {
    private final String element;
    private final String text;
    private final boolean isOpenTag;
    
    private final Set<String> selfClosingTags = new HashSet<>(
	    Arrays.asList("TODO"));
    
    public HtmlTag(String element, String text, boolean isOpenTag) {
	this.element = element;
	this.text = text;
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



