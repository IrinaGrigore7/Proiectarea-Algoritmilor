import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Bani {
	static class Task {
		public static final String INPUT_FILE = "bani.in";
		public static final String OUTPUT_FILE = "bani.out";

		int set; /*retin ce tip de set trebuie aplicat*/
		int banknoteNumber; /*reprezinta numarul bancnotelor*/

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
					INPUT_FILE)));
				set = sc.nextInt();
				banknoteNumber = sc.nextInt();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					OUTPUT_FILE)));
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/*calculeaza solutia pentru primul tip de set, 
		mai exact se calculeaza 2^(banknoteNumber -1)*/
		private long solution_first_set() {
			long result;
			int exponent = banknoteNumber - 1;
			int mod = 1000000007;
			long base = 2;

			if (exponent == 0) {
				return 5;
			}

			long aux = 1;
			while (exponent != 1) {
				if (exponent % 2 == 0) { 
					base = (1L * base * base) % (1L * mod);
					exponent = exponent >> 1; /*impart exponentul la 2*/
				} else { 
					aux = (1L * aux * base) % (1L * mod);
					exponent--;
				}
			}

			result = 5 * (1L * aux * base) % (1L * mod);/*compun rezultatul final*/
			return result;
		}

		/*calculez solutia pentru cel de-al doilea set*/
		private long solution_second_set() {
			int[] solution = {1, 1, 1, 1, 1}; /*retin in cate moduri s-au putut 
												aranja bancotele la pasul anterior*/
			int mod = 1000000007;
			int sum10, sum50, sum100, sum200, sum500; /*retin in cate moduri se pot aranja 
					bancnotele la pasul curent, folosindu-ma de informatia de la pasul anterior*/
			for (int i = 1; i < banknoteNumber; i++) {
				sum10 = (((solution[1] % mod) + (solution[2] % mod)) % mod
						+ (solution[4] % mod)) % mod;/*provine din bancnotele de 50, 100 si 500*/
				sum50 = ((solution[0] % mod) + (solution[3] % mod)) % mod;
				sum100 = (((solution[0] % mod) + (solution[2] % mod)) % mod 
						+ (solution[3] % mod)) % mod;
				sum200 = ((solution[1] % mod) + (solution[4] % mod)) % mod;
				sum500 = solution[3] % mod;
				solution[0] = sum10;
				solution[1] = sum50;
				solution[2] = sum100;
				solution[3] = sum200;
				solution[4] = sum500;
			}
			
			long totalSum = 0; /*compun solutia*/
			for (int i = 0; i < 5; i++) {
				totalSum = ((totalSum % mod) + (solution[i] % mod)) % mod;
			}
			return totalSum;
		}

		private long compute_solution() {
			if (set == 1) {
				return solution_first_set();
			}
			if (set == 2) {
				return solution_second_set();
			}
			return 0;
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