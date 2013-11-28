package com.jlhuertas.fbhackercup.qualifying;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Problem: https://www.facebook.com/hackercup/problems.php?pid=740733162607577&round=598486203541358
 * Solution: https://www.facebook.com/notes/facebook-hacker-cup/2014-qualification-round-solutions/775180192497884
 * 
 * 
 * @author jlhuertas
 *
 */
public class BasketballGame {


	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputFile = new Scanner(new FileInputStream(args[0]));
	    int T = inputFile.nextInt();
	    
	    for (int testCase = 1; testCase <= T; testCase++) {
	    	int N = inputFile.nextInt();
	    	int M = inputFile.nextInt();
	    	int P = inputFile.nextInt();
	    	inputFile.nextLine();
	    	
	    	//create list of players and sort it by shot percentage and then height
	    	List<Player> playerList = new ArrayList<Player>();
	    	for (int i = 0; i < N; i++) {
	    		Player player = new Player();
	    		player.name = inputFile.next();
	    		player.shotPercentage = inputFile.nextInt();
	    		player.height = inputFile.nextInt();
	    		playerList.add(player);
	    	}
	    	Collections.sort(playerList);

	    	//divide odd and even players in two teams
	    	List<BasketballGame.Player> teamA = new ArrayList<BasketballGame.Player>();
	    	List<BasketballGame.Player> teamB = new ArrayList<BasketballGame.Player>();
	    	for (int i = 0; i < N; i++) {
	    		if (i % 2 == 0) {
	    			teamA.add(playerList.get(i));
	    		} else {
	    			teamB.add(playerList.get(i));
	    		}
	    	}
	    	
	    	//Figure out if a player will be in the field or in the bench after M rotations 
	    	List<String> currentPlayers = new ArrayList<String>();
	    	for (int i = 0; i < teamA.size(); i++) {
	    		int mod = M % teamA.size();
	    		if (i < P) {
	    			mod  = (mod + i) % teamA.size();
	    		} else {
	    			mod = (mod + (teamA.size() - i + P - 1)) % teamA.size();
	    		}
	    		
	    		if (mod < P) {
	    			//player will be in the field, add to the result list
	    			currentPlayers.add(teamA.get(i).name);
	    		}
			}
	    	
	    	for (int i = 0; i < teamB.size(); i++) {
	    		int mod = M % teamB.size();
	    		if (i < P) {
	    			mod  = (mod + i) % teamB.size();
	    		} else {
	    			mod = (mod + (teamB.size() - i + P - 1)) % teamB.size();
	    		}
	    		
	    		if (mod < P) {
	    			//player will be in the field, add to the result list
	    			currentPlayers.add(teamB.get(i).name);
	    		}
	    	}
	    	
	    	//sort the result list lexicographically and print the results
	    	Collections.sort(currentPlayers);
	    	
	    	System.out.print("Case #" + testCase + ":");
	    	for (String name : currentPlayers) {
	    		System.out.print(" " + name);
			}
	    	System.out.println();
	    	
	    	
	    }
	}

	public static class Player implements Comparable<Player> {
		String name;
		int shotPercentage;
		int height;
		
		@Override
		public int compareTo(Player otherPlayer) {
			return (otherPlayer.shotPercentage * 1000 + otherPlayer.height)  - (this.shotPercentage * 1000 + this.height);
		}
	}
}
