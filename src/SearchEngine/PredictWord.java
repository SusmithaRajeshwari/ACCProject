package SearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredictWord {

	public static String htmlDirectoryPath = "dat/HTMLfiles/";
	public static String textDirectoryPath = "dat/Textfiles/";
	static HashMap<String, Integer> numbers = new HashMap<String, Integer>();
	static ArrayList<String> key = new ArrayList<String>();
	/**
	 * Used to find the nearest word to the misspelled word by the user
	 * @param sourceFile :the file in which the word is to be searched
	 * @param match :the pattern matcher to find the words matching the pattern
	 * @param str :the mis-spelt word
	 */
	public static void findWord(File sourceFile, Matcher match, String str)
			throws FileNotFoundException, ArrayIndexOutOfBoundsException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sourceFile));//reading character from file
			String line = null;

//This code block is used to read the contents of a file line by line and extract all the words (as defined by the regular expression pattern used in Matcher match) from each line.
			while ((line = reader.readLine()) != null) {
				match.reset(line);
//he while loop inside this outer loop uses find() to find the next occurrence of the pattern in the line, and adds each match to the ArrayList<String> called key.				
				while (match.find()) {
					key.add(match.group());
				}
			}

			reader.close();
			for (int p = 0; p < key.size(); p++) {
				numbers.put(key.get(p), EditDistance.editDistance(str.toLowerCase(), key.get(p).toLowerCase()));
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	/**
	 * method uses EditDistance algorithm to find out the distances of every word in the file
	 * @param word :the misspelled word 
	 */
	
	public static void suggestAlternativeWord(String word) {
		String line = " ";
		String re = "[a-z0-9]+";
		Pattern pat = Pattern.compile(re);//method is used to create a regular expression pattern that matches any sequence of two or more repeated characters.
		Matcher matcher = pat.matcher(line);//The Matcher class is then used to search for instances of this pattern in the input word
		

		File dir = new File(textDirectoryPath); //read the text files
		File[] fileArray = dir.listFiles(); //put the files name in an array
		for (File file: fileArray) {
			try {
				findWord(file, matcher, word); //find
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		Integer allowedDistance = 1; 
		boolean Found = false; 
		int i = 0;
		for (@SuppressWarnings("rawtypes") Map.Entry entry : numbers.entrySet()) {
			if (allowedDistance == entry.getValue()) {
				i++;
				if(i==1)
				System.out.println("Wrong input..\nYou might have mispelled, Try these words again.. ");
				System.out.print(i + ". " + entry.getKey() + "\n");
				Found = true;
			}
		}
		if(Found) {

			System.out.println("Please search again with correct word \n ");

		}
		
		if (!Found)
			System.out.println("Can't find the word...");
		
	}

	
	
}
