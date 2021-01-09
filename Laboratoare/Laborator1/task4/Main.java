import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int base;
        int exponent;
        int mod;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                base = sc.nextInt();
                exponent = sc.nextInt();
                mod = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(long result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private long fastPow(int base, int exponent, int mod) {
            // TODO: Calculati (base^exponent) modulo mod in O(log exponent).
        	
        	long result = 1;
     
        	for(int i = 0; i < exponent/2; i++) {
        		result = (1L * (result % mod) * (base % mod)) % mod;
        		
        	}
        	
        	result = (1L * (result % mod) * (result % mod)) % mod;
        	
        	if(exponent % 2 == 1) {
        		result = (1L * (result % mod) * (base % mod)) % mod;
        	}
        	
            return result;
        }

        public void solve() {
            readInput();
            long result = fastPow(base, exponent, mod);
            writeOutput(result);
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
