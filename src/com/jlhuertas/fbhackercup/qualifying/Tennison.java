package com.jlhuertas.fbhackercup.qualifying;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Problem: https://www.facebook.com/hackercup/problems.php?pid=373965339404375&round=598486203541358
 * Solution: https://www.facebook.com/notes/facebook-hacker-cup/2014-qualification-round-solutions/775180192497884
 * 
 * @author jlhuertas
 *
 */
public class Tennison {

	

	private double ps;
	private double pr;
	private int K;
	private double pi;
	private double pu;
	private double pw;
	private double pd;
	private double pl;
	
	private double[][][] resultCache;
	
	public Tennison(int K, double ps, double pr, double pi, double pu, double pw, double pd, double pl) {
		this.K = K;
		this.ps = ps;
		this.pr = pr;
		this.pi = pi;
		this.pu = pu;
		this.pw = pw;
		this.pd = pd;
		this.pl = pl;
		
		//init result cache
    	resultCache = new double[K][K][1001];
    	for (int i = 0; i < K; i++) {
    		for (int j = 0; j < K; j++) {
    			for (int k = 0; k < 1001; k++) {
    				resultCache[i][j][k] = -1.0;
    			}
    				
    		}
    	}
	}
	

	public double probWinMatch() {
		//return probability to win the match starting with 0 games won, 0 lost and sun probability pi
		return probWinMatch(0, 0, this.pi);
	}
	
	private double probWinMatch(int wonGames, int lostGames, double pi) {
		
		//base cases
		if (wonGames == K) {
			return 1.0;
		}
		if (lostGames == K) {
			return 0.0;
		}
		
		//check if result is cached
		if (resultCache[wonGames][lostGames][(int) (pi * 1000)] >= 0) {
			return resultCache[wonGames][lostGames][(int) (pi * 1000)];
		} 
		
		//sum of the probabilities of the 8 possible outcomes
		double pSunWinIncrease = pi * ps * pw * probWinMatch(wonGames + 1, lostGames, incProb(pi, pu));
		double pSunWinStay = pi * ps * (1 - pw) * probWinMatch(wonGames + 1, lostGames, pi);
		double pSunLoseDecrease = pi * (1 - ps) * pl * probWinMatch(wonGames, lostGames + 1, decProb(pi, pd));
		double pSunLoseStay = pi * (1 - ps) * (1 - pl) * probWinMatch(wonGames, lostGames + 1, pi);
		double pRainWinIncrease = (1 - pi) * pr * pw * probWinMatch(wonGames + 1, lostGames, incProb(pi, pu));
		double pRainWinStay = (1 - pi) * pr * (1 - pw) * probWinMatch(wonGames + 1, lostGames, pi);
		double pRainLoseDecrease = (1 - pi) * (1 - pr) * pl * probWinMatch(wonGames, lostGames + 1, decProb(pi, pd));
		double pRainLoseStay = (1 - pi) * (1 - pr) * (1 - pl) * probWinMatch(wonGames, lostGames + 1, pi);
		
		double prob = pSunWinIncrease + pSunWinStay + pSunLoseDecrease + pSunLoseStay + pRainWinIncrease + pRainWinStay + pRainLoseDecrease + pRainLoseStay;
		
		//cache result
		resultCache[wonGames][lostGames][(int) (pi * 1000)] = prob;
		
		return prob; 
		
		
	}
	
	private double incProb(double p1, double p2) {
		return Math.min(1.0, p1 + p2);
	}
	
	private double decProb(double p1, double p2) {
		return Math.max(0.0, p1 - p2);
	}

	

	public static void main(String[] args) throws FileNotFoundException {

		Scanner inputFile = new Scanner(new FileInputStream(args[0]));
		inputFile.useLocale(Locale.US);
	    int T = inputFile.nextInt();
	    
	    for (int testCase = 1; testCase <= T; testCase++) {
	    	
	    	int K = inputFile.nextInt();
	    	double ps = inputFile.nextDouble();
	    	double pr = inputFile.nextDouble();
	    	double pi = inputFile.nextDouble();
	    	double pu = inputFile.nextDouble();
	    	double pw = inputFile.nextDouble();
	    	double pd = inputFile.nextDouble();
	    	double pl = inputFile.nextDouble();
	    	Tennison match = new Tennison(K, ps, pr, pi, pu, pw, pd, pl);
	    	
	    	System.out.println("Case #" + testCase + ": " + String.format("%.6f", match.probWinMatch()) );
	    }
		
	}

}
