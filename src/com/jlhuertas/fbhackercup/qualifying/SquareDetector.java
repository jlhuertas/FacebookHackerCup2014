package com.jlhuertas.fbhackercup.qualifying;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Problem: https://www.facebook.com/hackercup/problems.php?pid=318555664954399&round=598486203541358
 * Suggested solution: https://www.facebook.com/notes/facebook-hacker-cup/2014-qualification-round-solutions/775180192497884
 * 
 * @author jlhuertas
 *
 */
public class SquareDetector {


	private static final int FINDING_CORNER = 0;
	
	private static final int CALCULATING_SIZE = 1;
	
	private static final int CHECKING_SQUARE = 2;
		
	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputFile = new Scanner(new FileInputStream(args[0]));
	    int T = inputFile.nextInt();
	    
	    for (int testCase = 1; testCase <= T; testCase++) {
	    	
	    	/*
	    	 * Three steps:
	    	 * 1) Finding the top-left corner
	    	 * 2) Finding the side of the square
	    	 * 3) Checking if everything inside the hypothetical bounds of the square is black and everything outside white.
	    	 */
	    	int N = inputFile.nextInt();
	    	inputFile.nextLine();
	    	
	    	int i = 0;
	    	int j = 0;
	    	String row = null;
	    	int left = 0;
	    	int top = 0;

	    	boolean isSquare = true;

	    	int status = FINDING_CORNER;
	    	int squareSize = 0;
	    	while (i < N) {
	    		row = inputFile.nextLine();
	    		j = 0;
	    		while (j < N && isSquare) {
	    			
	    			switch (status) {
					case FINDING_CORNER:
						if (row.charAt(j) == '#') {
		    				left = j;
		    				top = i;
		    				squareSize = 1;
		    				if (left == N - 1) {
		    					status = CHECKING_SQUARE;
		    				} else {
		    					status = CALCULATING_SIZE;
		    				}
	
						}
						break;
					case CALCULATING_SIZE:
	    				if (row.charAt(j) == '#') {
	    					squareSize++;
	    				} else {
	    					status = CHECKING_SQUARE;    
    	    			}						
					case CHECKING_SQUARE:
						if (top + squareSize > N || left + squareSize > N) {
							isSquare = false;
						} else {

							if (i >= top && i < top + squareSize && j >= left && j < left + squareSize ) {
								//inside theorical square bounds
								isSquare = (row.charAt(j)) == '#';
							} else {
								//outside theorical square bounds
								isSquare = (row.charAt(j)) == '.';
							}	
						}
						break;
					default:
						break;
					}
	    			j++;
	    		}
	    		if (status == CALCULATING_SIZE) {
	    			status = CHECKING_SQUARE;
	    		}
	    		i++;
	    	}
	    	
	    	String result = isSquare ? "YES" : "NO";
	    	System.out.println("Case #" + testCase + ": " + result);
	    }

	}

}
