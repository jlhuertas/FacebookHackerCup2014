package com.jlhuertas.fbhackercup.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Problem: https://www.facebook.com/hackercup/problems.php?pid=475986555798659&round=185564241586420
 * Solution: https://www.facebook.com/notes/facebook-hacker-cup/qualification-round-solutions/598486173500621
 * 
 * @author jlhuertas
 *
 */
public class BeautifulStrings {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner inputFile = new Scanner(new FileInputStream(args[0]));
	    int T = inputFile.nextInt();
	    inputFile.nextLine();
	    
	    for (int testCase = 1; testCase <= T; testCase++) {

	    	String str = inputFile.nextLine();
	    	Map<Character, Integer> charFrecuency = new HashMap<Character, Integer>();
	    	//count number of appearances of each char
	    	for (int i = 0; i < str.length(); i++) {
	    		Character currentChar = Character.toUpperCase(str.charAt(i));
 
	    		if (Character.isLetter(currentChar)) {
		    		if (charFrecuency.get(currentChar) != null) {
		    			charFrecuency.put(currentChar, charFrecuency.get(currentChar) + 1);
		    		} else {
		    			charFrecuency.put(currentChar, 1);
		    		}	    			
	    		}

	    	}
	    	
	    	List<Integer> frequencies = new ArrayList<Integer>(charFrecuency.values());
	    	//soft by higher frequency
	    	Collections.sort(frequencies);
	    	Collections.reverse(frequencies);
	    	
	    	int MAX_BEAUTY = 26;
	    	int totalBeauty = 0;
	    	for (int i = 0; i < frequencies.size(); i++) {
	    		totalBeauty += frequencies.get(i) * (MAX_BEAUTY - i);
	    	}
	    		
	    	System.out.println("Case #" + testCase + ": " + totalBeauty);
    	}

	}

}
