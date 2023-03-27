package SearchEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher; //used to search through a text for multiple occurrences of a regular expression.
import java.util.regex.Pattern; //defining pattern and manipulating strings
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;//specifically for extracting elements in html
import org.jsoup.select.Elements;//for selecting multiple elements

public class UsingRegularExpression 
{
	public static ArrayList<String> linkList = new ArrayList<>();
	public static void getLinks(String webaddress) 
	{
        Document htmlPage;
        try 
        {
        	htmlPage = Jsoup.connect(webaddress).get();
            Elements path = htmlPage.select("a[href]");
           System.out.println("\n");
            System.out.println("Found (" + path.size() + ") links on " + webaddress);
            for (Element link : path) 
            {
            	String abst_URL = link.attr("abs:href");
                String regexpression = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
                Pattern url_Pattrn = Pattern.compile(regexpression);
                Matcher match_URL = url_Pattrn.matcher(abst_URL);
                while (match_URL.find()) 
                {
                    linkList.add(match_URL.group(0));
                }
            }
            System.out.println("\nW E B   P A G E S   C R A W L E D   S U C C E S S F U L L Y ! ! ! !\n");
        }
        catch (IllegalArgumentException e) {
        	//System.out.print("\u001B[32m");
        	System.out.println("\n");
            System.err.println("The URL you have entered is Invalid : " + webaddress);
        } 
        catch (IOException error) 
        {
            error.printStackTrace();
        }
    }
    public static String getLinkAddress(String webaddress) 
    {
    	String regexpression = "[a-zA-Z0-9]+";
		Pattern Pattern_link = Pattern.compile(regexpression);
		Matcher Link_match = Pattern_link.matcher(webaddress);
		StringBuffer string = new StringBuffer();
		while (Link_match.find()) 
		{
			string.append(Link_match.group(0));// likely containing a matched portion of text from a regular expression pattern.
		}
		String link_Adress = string.substring(0);
		return link_Adress;
    }

}