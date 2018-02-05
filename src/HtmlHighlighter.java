import java.util.ArrayList;
import java.util.Stack;

public class HtmlHighlighter {
    
    private Stack<HtmlTag> htmlStack = new Stack<>();
    private Stack<ColorTag> colorStack = new Stack<>();
    
    private Stack<ColorTag> toApply = new Stack<>();
    
    public HtmlHighlighter () {
	
    }
    
    public String highlightHtml(String input) {
	StringBuilder htmlSequence = new StringBuilder(input);
	int index = 0;
	
	while (index < htmlSequence.length()) {
	    int startIndex = htmlSequence.indexOf("<", index);
	    int endIndex = htmlSequence.indexOf(">", startIndex);
	    
	    if (startIndex > htmlSequence.length() - 1 || endIndex + 1 > htmlSequence.length() -1) {
		break;
	    }
	    
//	    System.out.print("startIndex: " + startIndex + " endIndex: " + endIndex);
	    // TODO check out of bounds
	    HtmlTag htmlTag = new HtmlTag(htmlSequence.substring(startIndex, endIndex + 1));
//	    System.out.println("  " + htmlTag);
	    
	    if (htmlTag.isSelfClosing()) {
		ColorTag thisColorTag = new ColorTag(startIndex);
		toApply.push(thisColorTag);
		if (!htmlStack.isEmpty()) {
		    ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 1);
		    toApply.push(prevColorTag);
		} 
	    } else if (htmlTag.isOpenTag()) {

		ColorTag colorTag = new ColorTag(startIndex);
		htmlStack.push(htmlTag);
		colorStack.push(colorTag);
		toApply.add(colorTag);
		
//		System.out.println("	" + htmlTag);
//		System.out.println("	" + colorTag);
	    } else {
		// closing tag matches most recent open tag
		if (htmlStack.isEmpty()) {
		    System.out.println("err");
		} else if (htmlStack.peek().matches(htmlTag)) {
//		    System.out.println("		Closing tag!" + htmlStack.peek());
		    // remove the stuff associated with this tag from the stack
		    htmlStack.pop();
		    colorStack.pop();
		    
		    if (!colorStack.isEmpty()) {
			ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 1);
			toApply.push(prevColorTag);
			// DRY
		    }
		        
		} 
		
	    }
	    //index = index + (endIndex - startIndex) ;
	    index = endIndex;
	    
//	    System.out.println("     index is" + index);
	}
	
	
	
	while (!toApply.isEmpty()) {
	    htmlSequence.insert(toApply.peek().getIndex(), toApply.pop().toString());
	}
	

	return htmlSequence.toString();
    }
    
    
    
    
    
    
    
    
}
