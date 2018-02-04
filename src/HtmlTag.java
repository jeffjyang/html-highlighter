import java.util.List;

public class HtmlTag {
    private final String element;
    private final String text;
    private final boolean isOpenTag;
    private final boolean isSelfClosing;
    
    public HtmlTag(String element, String text, boolean isOpenTag, boolean isSelfClosing) {
	this.element = element;
	this.text = text;
	this.isOpenTag = isOpenTag;
	this.isSelfClosing = isSelfClosing;
    }
    
    
    public String toString() {
	// TODO verify 
	return "<" + element + (isOpenTag? "" : "/") + ">" + text;
    }
    
    
    
    
}
