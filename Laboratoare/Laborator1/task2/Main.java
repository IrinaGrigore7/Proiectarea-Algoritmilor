import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        double n;
        double prec = 0.001;
        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextDouble();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double x) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", x);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double computeSqrt() {
            // TODO: Calculeaza sqrt(n) cu o precizie de 10^-3.
            // Precizie de 10^(-x) inseamna |valoarea_ta - valoarea_reala| < 10^(-x).
        	
        	double s = 0;
        	double d;
        	
        	if(n < 1)
        		d = n + 1;
        	else 
        		d = n;
        	
        	double mid = (s+d)/2;
        	
        	while(Math.abs(mid*mid - n) > prec)
        	{	
        		mid = (s+d)/2;
        		if(mid*mid > n)
        			d = mid;
        		else
        			s = mid;	
        	}
        	return mid;
        	
            
        }

        public void solve() {
            readInput();
            writeOutput(computeSqrt());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
