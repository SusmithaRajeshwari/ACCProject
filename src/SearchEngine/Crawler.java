package SearchEngine;

public class Crawler 
{
	
	public static String[] fetchURLList () 
	{
        String[] listOfURL = UsingRegularExpression.linkList.toArray(new String[UsingRegularExpression.linkList.size()]);
        return listOfURL;
    }
    public static void crawler(String webaddress) 
    {
    	UsingRegularExpression.getLinks(webaddress);
    	HTMLtoTEXT.htmlToText();
    }
}