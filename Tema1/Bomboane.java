import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bomboane {

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format(start + " " + end);
		}

	}

	static class Task {
		public static final String INPUT_FILE = "bomboane.in";
		public static final String OUTPUT_FILE = "bomboane.out";

		int childrenNumber;
		int candyNumber;
		Interval[] intervals;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
					INPUT_FILE)));
				childrenNumber = sc.nextInt();
				candyNumber = sc.nextInt();
				intervals = new Interval[childrenNumber];
				int start, end;
				for (int i = 0; i < childrenNumber; i++) {
					start = sc.nextInt();
					end = sc.nextInt();
					intervals[i] = new Interval(start, end);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					OUTPUT_FILE)));
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int compute_solution() {
			int[] dp = new int[candyNumber + 1]; /*vectorul de la pasul anterior*/
			int[] dpResult = new int[candyNumber + 1]; /*vectorul de la pasul curent*/
			int startInterval = intervals[0].start; /*capetele de interval ale primului copil*/
			int endInterval = intervals[0].end;
			int mod = 1000000007;
			if (endInterval > candyNumber) { /*intervalul depaseste numarul maxim de bomboane*/
				endInterval = candyNumber; /*aleg ca si nou capat de interval max. boamboane*/
			}
			for (int i = startInterval; i <= endInterval; i++) { /*cazul de baza*/
				dp[i] = 1;
			}
			if (childrenNumber == 1) {
				dpResult = dp; /*pasul curent este egal cu ce am avut la pasul anterior*/
			}

			int sum;
			for (int i = 1; i < childrenNumber; i++) { /*parcurg toate intervalele copiilor*/
				startInterval = intervals[i].start;
				endInterval = intervals[i].end;
				for (int j = 0; j <= candyNumber; j++) {
					sum = 0;
					int startSubinterval = j - startInterval; /*extrag noile subintervale*/
					int endSubinterval = j - endInterval;
					if (startSubinterval < 0) { /*am mai multe bomboane decat numarul curent*/
						dpResult[j] = 0;
						continue;
					}
					if (endSubinterval < 0) { /*depaseste numarul minim de bomboane*/
						endSubinterval = 0;
					}
					for (int k = startSubinterval; k >= endSubinterval; k--) {
						sum  = ((sum % mod) + (dp[k] % mod)) % mod;
					}
					dpResult[j] = sum; /*adaug numarul de moduri de impartire a bomboanelor*/
				}
				for (int m = 0; m <= candyNumber; m++) {
					dp[m] = dpResult[m]; /*actualizez pasul anterior*/
				}
			}
			return (dpResult[candyNumber]);
		}

		public void solve() {
			readInput();
			
			writeOutput(compute_solution());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}