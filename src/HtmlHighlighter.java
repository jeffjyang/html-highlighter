import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HtmlHighlighter {



    private HtmlHighlighter () {

    }

    /**
     * Applies ColorTags (e.g. "\color[RED]") to an string containing an HTML file.
     * Any HTML tag will have the same color throughout the file, however colors
     * of any HTML tag may differ across different inputs.
     * 
     * @param input
     * 		represents an HTML file.
     * 		requires: input to represent a valid HTML file.
     * @return: a string representing an HTML file after the ColorTags have been applied. 
     */
    public static String highlightHtml(String input) {

	Map<String, String> htmlColors = new HashMap<>();
	
	Stack<HtmlTag> htmlStack = new Stack<>();

	Stack<ColorTag> toApply = new Stack<>();	


	StringBuilder htmlSequence = new StringBuilder(input);
	int index = 0;

	// iterate through the entire input file 
	while (index < htmlSequence.length()) {
	
	    int startIndex = htmlSequence.indexOf("<", index);
	    int endIndex = htmlSequence.indexOf(">", startIndex);

	    // error checking 
	    if (startIndex > htmlSequence.length() - 1 || endIndex + 0 > htmlSequence.length() - 1 || startIndex < 0 || endIndex < 0) {
		break;
	    }

	    HtmlTag htmlTag = new HtmlTag(htmlSequence.substring(startIndex, endIndex + 1));

	    if (htmlTag.isSelfClosing()) {

		ColorTag thisColorTag;
		if (htmlColors.containsKey(htmlTag.getElement())) {
		    thisColorTag = new ColorTag(htmlColors.get(htmlTag.getElement()), startIndex);
		} else {
		    thisColorTag = new ColorTag(startIndex);
		    htmlColors.put(htmlTag.getElement(), thisColorTag.getColor());
		}	
		toApply.push(thisColorTag);
		
		// Switch back to the previous color after this tag 
		if (!htmlStack.isEmpty()) {	
		    ColorTag prevColorTag = new ColorTag(htmlColors.get(htmlStack.peek().getElement()), endIndex + 1);
		    toApply.push(prevColorTag);
		} 

	    } else if (htmlTag.isOpenTag()) {
		
		ColorTag colorTag; 
		htmlStack.push(htmlTag);
		
		if (htmlColors.containsKey(htmlTag.getElement())) {
		    colorTag = new ColorTag(htmlColors.get(htmlTag.getElement()), startIndex);
		} else {
		    colorTag = new ColorTag(startIndex);
		    htmlColors.put(htmlTag.getElement(), colorTag.getColor());
		}
		
		toApply.add(colorTag);
	    
	    } else {
	
		// if we have a closing tag without a matching opening tag, throw an error 
		// TODO omit since requirement input is valid? 
		if (htmlStack.isEmpty()) {
		    System.out.println("err");
		} else {

		    // remove the corresponding opening tag from htmlStack 
		    htmlStack.pop();

		    // if we still have an unclosed tag, switch back to the previous color 
		    if (!htmlStack.isEmpty()) {
			
			// if the next character is a new line, put the ColorTag after the new line
			if (htmlSequence.charAt(endIndex + 1) == '\n') {
			    ColorTag prevColorTag = new ColorTag (htmlColors.get(htmlStack.peek().getElement()), endIndex + 2);
			    toApply.push(prevColorTag);			    
			} else {
			    ColorTag prevColorTag = new ColorTag (htmlColors.get(htmlStack.peek().getElement()), endIndex + 1);
			    toApply.push(prevColorTag);
			}

		    }

		} 

	    }


	    // If this ColorTag would be immediately overwritten by the very next 
	    // ColorTag, do not apply it to the output 
	    if ((htmlTag.isSelfClosing() || !htmlTag.isOpenTag()) && !htmlStack.isEmpty()) {

		int nextTagStart = htmlSequence.indexOf("<", endIndex -1);
		int nextTagEnd = htmlSequence.indexOf(">", nextTagStart);

		if (nextTagStart < htmlSequence.length() || nextTagEnd <= htmlSequence.length() || nextTagStart > -1 || nextTagEnd > -1) {

		    HtmlTag nextHtmlTag = new HtmlTag(htmlSequence.substring(nextTagStart, nextTagEnd + 1));			
		    String inbetween = htmlSequence.substring(endIndex, nextTagStart);

		    if (inbetween.matches("\\s") || nextHtmlTag.isOpenTag()) {
			toApply.pop();
		    } 
		}

	    }

	    index = endIndex;

	}

	// Apply all the ColorTags to the output file 
	while (!toApply.isEmpty()) {
	    htmlSequence.insert(toApply.peek().getIndex(), toApply.pop().toString());
	}

	return htmlSequence.toString();

    }


}
