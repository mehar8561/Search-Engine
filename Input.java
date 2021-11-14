package Web_crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class Input {

	


     public static void main(String args[]) throws IOException
     {
    	
        // Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
         //Matcher matcher = p.matcher(doc.text());
         
    	
        
 		 Document document = Jsoup.connect("http://stackoverflow.com/questions/15893655/").userAgent("Mozilla").get();

        document.outputSettings(new Document.OutputSettings().prettyPrint(false)); //makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        String op =  Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
        
         
         System.out.println(op);

     }

}

