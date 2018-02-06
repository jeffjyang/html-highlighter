import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HtmlHighlighter {



    private HtmlHighlighter () {

    }

    public static String highlightHtml(String input) {

	Map<String, String> htmlColors = new HashMap<>();
	
	Stack<HtmlTag> htmlStack = new Stack<>();

	Stack<ColorTag> toApply = new Stack<>();	


	StringBuilder htmlSequence = new StringBuilder(input);
	int index = 0;

	while (index < htmlSequence.length()) {
	    int startIndex = htmlSequence.indexOf("<", index);
	    int endIndex = htmlSequence.indexOf(">", startIndex);

	    if (startIndex > htmlSequence.length() - 1 || endIndex + 0 > htmlSequence.length() - 1 || startIndex < 0 || endIndex < 0) {
		break;
	    }

	    HtmlTag htmlTag = new HtmlTag(htmlSequence.substring(startIndex, endIndex + 1));


	    if (htmlTag.isSelfClosing()) {

		ColorTag thisColorTag;
		if (htmlColors.containsKey(htmlTag.getElement())) {
		    thisColorTag = new ColorTag(htmlColors.get(htmlTag.getElement()), startIndex);
		}else {
		    thisColorTag = new ColorTag(startIndex);
		    htmlColors.put(htmlTag.getElement(), thisColorTag.getColor());
		}
			
		toApply.push(thisColorTag);
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
		// closing tag matches most recent open tag
		if (htmlStack.isEmpty()) {
		    System.out.println("err");
		} else if (htmlStack.peek().matches(htmlTag)) {
		    // remove the stuff associated with this tag from the stack
		    htmlStack.pop();

		    if (!htmlStack.isEmpty()) {
			// if the next character is a new line, put the tag after the new line
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

	while (!toApply.isEmpty()) {
	    htmlSequence.insert(toApply.peek().getIndex(), toApply.pop().toString());
	}

	return htmlSequence.toString();

    }



}
