package determine;

import java.util.ArrayList;
import java.util.*;

public class Main {
	private int highestSquare;
	ArrayList<int[]> pairs;
	
	private int sqrt(int n){
		int x = n;
		while (true) {
			int y = (x + n/x)/2;
		    if( y >= x ) return x;
		    x = y;
		}
	}
	
	private int findHighestSquare(int i) {
		int x = sqrt(i);
		return x * x;
	}
	
	private ArrayList<int[]> determinePairs(int highestSquare, int i) {
		ArrayList<int[]> pairs = new ArrayList<int[]>();
		
		int[] squares = squaresUpTo(highestSquare);
		for (int k : squares) {
			for (int p : squares) {
				if (k + p == i) {
					//System.err.println("found " + k + " " + p); //testing
					boolean putIn = true;
					int[] possible = {k, p};
					for (int[] in : pairs) {
						if (in[0] == k && in[1] == p || in[0] == p && in[1] == k) {
							putIn = false;
						}
					}
					if (putIn) {
						pairs.add(possible);
					}
				}
			}
		}
		return pairs;
	}
	
	private int[] squaresUpTo(int highestSquare) {
		int bound = sqrt(highestSquare);
		int[] numbs = new int[bound];
		for (int i = 1; i <= bound; i++) {
			numbs[i-1] = (int)Math.ceil(Math.pow(i, 2));
		}
		return numbs;
	}
	
	public static void main(String[] args) {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		while (run) {
			System.out.print("int to find pairs of squares (0=exit): ");
			int i = sc.nextInt();
			if (i < 1) {
				run = false;
				break;
			}
			Main main = new Main(i);
			System.out.println("");
			System.out.println("pairs:");
			System.out.print("[");
			for (int k = 0; k < main.pairs.size(); k++) {
				System.out.print(Arrays.toString(main.pairs.get(k)) + (k == main.pairs.size() - 1 ? "" : ", "));
			}
			System.out.print("]");
			System.out.println("");
			System.out.println("");
		}
		sc.close();
	}
	
	public Main(int i) {
		highestSquare = findHighestSquare(i);
		pairs = determinePairs(highestSquare, i);
	}
}
