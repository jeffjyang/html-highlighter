import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighlighterMain {

    public static void main(String[] args) {
	String inputFilePath = "input.html";
	
	String inputFile = "";

	System.out.println("Beginning HTML highlighting");
	
	try {
	    inputFile = new String(Files.readAllBytes(Paths.get(inputFilePath)));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	if (!"".equals(inputFile)) {
	    try(PrintWriter out = new PrintWriter("output.txt")){
		out.println(HtmlHighlighter.highlightHtml(inputFile));
		System.out.println("Done");
		return;
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }
	} else {
	     System.out.println("Error encountered");
	     return;
	}
	
    }

}
