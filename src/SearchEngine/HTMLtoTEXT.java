package SearchEngine;

import java.io.BufferedWriter;
// class in Java provides a way to write text to a character-output stream efficiently by buffering output and writing it in larger chunks.
import java.io.File;
//You can use the File class to create, delete, rename files.
import java.io.FileWriter;
//class in Java provides a convenient way to write character data to a file.
import java.io.IOException;
import java.io.PrintWriter;
//PrintWriter is designed to write text to a file
//FileWriter writes raw bytes to the file, while PrintWriter writes characters and handles character encoding automatically.
import org.jsoup.Jsoup;
//Jsoup is a Java library for working with HTML documents. It provides a convenient way to parse HTML documents, extract data from them
//Jsoup provides methods for selecting elements based on their tag names, attributes, and content, and for modifying the contents of elements.
import org.jsoup.nodes.Document;
//The Document class in Jsoup represents an HTML document that has been parsed into a tree structure of elements and text nodes.
//When you parse an HTML document using Jsoup, you get back a Document object that represents the entire document,
//including the HTML, head, and body elements


public class HTMLtoTEXT 
{
	public static int c =0;
	public static int i =0;
	//give  input HTML file and the output text file
	//he method reads an HTML file at the location specified by file_html using Jsoup, 
	//extracts the text from the file using the doc.text() method, 
	//and writes the text to a text file at the location specified by file_text using a BufferedWriter
	public static void fileConverter(String file_html, String file_text) 
	{
		try 
		{
			File file_HTML = new File(file_html);
			Document doc = Jsoup.parse(file_HTML, "UTF-8");    
			String txt = doc.text(); 
			BufferedWriter text_write = new BufferedWriter(new FileWriter(file_text)); 
			text_write.write(txt);
			text_write.close();
		}
		catch (Exception error)
		{
			System.out.println("URL cannot be fetched:"+error);
		}
		
		
	}
	
	//This code defines a static method named "htmlFile()" that is responsible for
	//downloading the HTML content of a web page, saving it to a file, and storing it in a directory.
	public static void htmlFile(String link) throws IOException 
	{
		//The Jsoup.connect() method is used to connect to the given URL and retrieve its content as a Document object.
		Document link_url = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
				.referrer("http://www.google.com").ignoreHttpErrors(true).timeout(50*1000).get();
		//connect(link) creates a connection to the URL specified by the link variable.
		String HTML_store = "dat/HTMLfiles/";
		//this line of code is useful for storing a file path or directory path in a Java program. 
		String html = link_url.html();
		//line of code is useful for extracting the HTML content of a web page from a Document object obtained using the Jsoup library in Java. 
		File folder_html = new File(HTML_store);
		// this line of code creates a new File object that represents the folder at that path. 
		if (!folder_html.exists() && !folder_html.isDirectory())
		{
			folder_html.mkdir();
		}  
		//checks whether the "folder_html" directory exists and is a directory. 
		//If the directory does not exist or is not a directory, it creates the directory using the "mkdir()" method.
		PrintWriter text = new PrintWriter(HTML_store + UsingRegularExpression.getLinkAddress(link) + ".html");
		text.println(html);
		// this code  part of a program that downloads web pages
		//and saves them to files in the directory specified by the "HTML_store" variable. By saving the HTML code to files, 
		text.close();
		//and the file is released so that it can be accessed by other processes. 
	}
	
	
	public static void textFile(String link) throws IOException 
	{
		String store_Text = "dat/TextFiles/";
		File folder_text = new File(store_Text);
		if (!folder_text.exists() && !folder_text.isDirectory())
		{
			folder_text.mkdir();
		}   
		File folder_new = new File("dat/HTMLfiles/");
		//This file object is useful when a program needs to access or work with the files stored in that directory. 
		File[] file_stream = folder_new.listFiles();
		//the directory is used to retrieve all the HTML files present in the directory using the listFiles() method. 
		//These HTML files are then converted to text files using the fileConverter() method
		assert file_stream != null;
		for (File file : file_stream) 
		{  
			String file_html = "dat/HTMLfiles/" + file.getName();
			String file_text = store_Text + file.getName().replaceAll(".html", "") + ".txt";
			fileConverter(file_html,file_text);
			/*if(i==0) 
			{
				System.out.println("--------------------------------------------");
				System.out.println("MOST REPEATED WORD IN THE FILE");
				System.out.println("--------------------------------------------"); i++; 
				
			}
			  	if(c<5) 
			  	{
			  		System.out.println("________________________________________________________"); 
			  		c=c+1; 
			  		//Checks the most repeated word in the file
			  		RepeatWord.repeatedWord(file_text); 
			  	}*/
		}
	}
	
	// This code defines a static method named "htmlToText()", which is responsible for converting a list of URLs into text files.
	public static void htmlToText() 
	{
		try 
		{
			for (String url : UsingRegularExpression.linkList) 
			{
				htmlFile(url);
				textFile(url);
			}
		} 
		catch (Exception error)
		{

			System.out.println("URL cannot be fetched:"+error);
		} 
		
		
		
	}
}