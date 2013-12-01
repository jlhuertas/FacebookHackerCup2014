package com.jlhuertas.fbhackercup.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Problem: https://www.facebook.com/hackercup/problems.php?pid=403525256396727&round=185564241586420
 * Solution: https://www.facebook.com/notes/facebook-hacker-cup/qualification-round-solutions/598486173500621
 * 
 * @author jlhuertas
 *
 */
public class BalancedSmileys {


	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputFile = new Scanner(new FileInputStream(args[0]));	
	    int T = inputFile.nextInt();
	    inputFile.nextLine();
	    
//	    long start = System.currentTimeMillis();
	    for (int testCase = 1; testCase <= T; testCase++) {
	    	String message = inputFile.nextLine();
	    	System.out.println("Case #" + testCase + ": " + (checkBalanced(message, 0, 0) ? "YES" : "NO"));
	    	//System.out.println("Case #" + testCase + ": " + (officialApproach(message) ? " YES" : " NO"));
	    }
//	    long end = System.currentTimeMillis();
//	    System.out.println("Total time: " + (end - start) + " ms.");
	}



	private static boolean checkBalanced(String message, int from, int bracketsBalance) {
		
		//base cases
		if (bracketsBalance < 0) {
			//found more closing than opening brackets, don't continue, impossible to balance
			return false;
		}
		
		if (from == message.length()) {
			//end of the string, opening and closing brackets should be balanced
			return (bracketsBalance == 0);
		}
		
		//non base cases
		char currentChar = message.charAt(from);
		if (currentChar == '(') {
			if (from > 0 && message.charAt(from - 1) == ':') {
				//possible smiley (or nor), we have to check the rest considering both alternatives
				return checkBalanced(message, from + 1, bracketsBalance + 1) 
						|| checkBalanced(message, from + 1, bracketsBalance); 
			} else {
				return checkBalanced(message, from + 1, bracketsBalance + 1);
				
			}
			
		} else if (currentChar == ')') {
			if (from > 0 && message.charAt(from - 1) == ':') {
				//possible smiley (or nor), we have to check the rest considering both alternatives
				return checkBalanced(message, from + 1, bracketsBalance - 1) 
						|| checkBalanced(message, from + 1, bracketsBalance); 
			} else {
				return checkBalanced(message, from + 1, bracketsBalance - 1);
				
			}
			
		} else {
			//any other character, check the rest of the string
			return checkBalanced(message, from + 1, bracketsBalance);
		}
		

	}
	
	private static boolean officialApproach(String message) {
		int minOpen = 0;
		int maxOpen = 0;
		for (int i = 0; i < message.length(); i++) {
			char currentChar = message.charAt(i);
			if (currentChar == '(') {
				if (i > 0 && message.charAt(i - 1) == ':') {
					//possible smiley 
					maxOpen++;
				} else {
					minOpen++;
					maxOpen++;
				}
				
			} else if (currentChar == ')') {
				if (i > 0 && message.charAt(i - 1) == ':') {
					//possible smiley
					minOpen = Math.max(0, minOpen -1 );
				} else {
					minOpen = Math.max(0, minOpen -1 );
					maxOpen--;
				}
				
			}			
		}
			
		return (maxOpen >= 0 && minOpen == 0);

	}

}
