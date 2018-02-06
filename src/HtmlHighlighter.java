import java.util.Stack;

public class HtmlHighlighter {


    
    private HtmlHighlighter () {

    }

    public static String highlightHtml(String input) {

	Stack<HtmlTag> htmlStack = new Stack<>();
	Stack<ColorTag> colorStack = new Stack<>();
	Stack<ColorTag> toApply = new Stack<>();	


	StringBuilder htmlSequence = new StringBuilder(input);
	int index = 0;

	while (index < htmlSequence.length()) {
	    int startIndex = htmlSequence.indexOf("<", index);
	    int endIndex = htmlSequence.indexOf(">", startIndex);

	    if (startIndex > htmlSequence.length() - 1 || endIndex + 1 > htmlSequence.length() -1) {
		break;
	    }

	    // TODO check out of bounds
	    HtmlTag htmlTag = new HtmlTag(htmlSequence.substring(startIndex, endIndex + 1));


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
	    } else {
		// closing tag matches most recent open tag
		if (htmlStack.isEmpty()) {
		    System.out.println("err");
		} else if (htmlStack.peek().matches(htmlTag)) {
		    // remove the stuff associated with this tag from the stack
		    htmlStack.pop();
		    colorStack.pop();

		    ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 1);
		    toApply.push(prevColorTag);

		    /*
		    if (!colorStack.isEmpty()) {
			int nextTagStart = htmlSequence.indexOf("<", endIndex -1);
			int nextTagEnd = htmlSequence.indexOf(">", nextTagStart);

			if (nextTagStart < htmlSequence.length()) {

			    HtmlTag nextHtmlTag = new HtmlTag(htmlSequence.substring(nextTagStart, nextTagEnd + 1));			
			    String inbetween = htmlSequence.substring(endIndex, nextTagStart);

			    if (inbetween.matches("\\S") || !nextHtmlTag.isOpenTag()) {

				//if (htmlSequence.charAt(endIndex + 1) == '\\') {
    			    	//    ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 2);
    			    	//    toApply.push(prevColorTag);
				//} else {
				//    ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 1);
	    			//    toApply.push(prevColorTag); 
				//}

				ColorTag prevColorTag = new ColorTag(colorStack.peek(), endIndex + 1);
	    			toApply.push(prevColorTag); 
			    } 
    			}
		    }

		     */

		} 

	    }
	    
	    
	    if (htmlTag.isSelfClosing() || !htmlTag.isOpenTag()) {
		if (!colorStack.isEmpty()) {
		    int nextTagStart = htmlSequence.indexOf("<", endIndex -1);
		    int nextTagEnd = htmlSequence.indexOf(">", nextTagStart);

		    if (nextTagStart < htmlSequence.length()) {

			HtmlTag nextHtmlTag = new HtmlTag(htmlSequence.substring(nextTagStart, nextTagEnd + 1));			
			String inbetween = htmlSequence.substring(endIndex, nextTagStart);

			if (inbetween.matches("\\s") || nextHtmlTag.isOpenTag()) {
			    toApply.pop();
			} 
		    }
		}
	    }
	    
	    index = endIndex;
	
	}

	while (!toApply.isEmpty()) {
	    htmlSequence.insert(toApply.peek().getIndex(), toApply.pop().toString());
	}

	return htmlSequence.toString();

    }



}
