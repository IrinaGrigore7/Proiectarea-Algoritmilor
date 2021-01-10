import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Gard {
	static class Pair implements Comparable<Pair> {
		int start;
		int end;

		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format(start + " " + end);
		}
		
		@Override
		public int compareTo(Pair pair) {
			if (this.start != pair.start) {
				return this.start - pair.start;
			}
			return pair.end - this.end;	
		}
	}
	static class Task {
		public static final String INPUT_FILE = "gard.in";
		public static final String OUTPUT_FILE = "gard.out";

		int N;
		ArrayList<Pair> intervals = new ArrayList<Pair>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
					INPUT_FILE)));
				N = sc.nextInt();
				int start, end;
				String line;
				for (int i = 0; i < N; i++) {
					start = sc.nextInt();
					end = sc.nextInt();
					intervals.add(new Pair(start, end));
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
			Collections.sort(intervals); /*sortez vectorul de intervale crescator dupa start, iar 
				in cazul in care sunt mai multe intervale care au valoarile de start egale, 
				le sortez descrescator dupa valoarea de final*/
			int count = 0; /*retin numarul gardurilor redundante*/
			int end = intervals.get(0).end;
			for (int i = 1; i < N; i++) {
				if (end >= intervals.get(i).end) {
					count++; /*cresc numarul de garduri redundante*/
				} else {
					end = intervals.get(i).end; /*s-a gasit un gard cu valoarea de final mai mare
										ceea ce inseamna ca nu este inclus in niciun alt interval*/
				}
			}
			return count;
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