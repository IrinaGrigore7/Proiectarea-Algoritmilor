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

        int n;
        int[] v;
        int x;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                v = new int[n];
                for (int i = 0; i < n; i++) {
                    v[i] = sc.nextInt();
                }
                x = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int count) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", count);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int findFirst() {
            // TODO: Cautati prima pozitie din v pe care se afla valoarea x.
            // In cazul in care nu exista in sir, returnati -1.
        	int left = 0, right = n - 1, mid, res = -1;
			while (left <= right) {
				mid = (left + right) / 2;
				if (v[mid] >= x) {
					res = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			return res;
        }

        private int findLast() {
            // TODO: Cautati ultima pozitie din v pe care se afla valoarea x.
            // In cazul in care nu exista in sir, returnati -1.
        	int left = 0, right = n - 1, mid, res = -1;
			while (left <= right) {
				mid = (left + right) / 2;
				if (v[mid] <= x) {
					res = mid;
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			return res;
        }

        private int getAnswer() {
            // TODO: Calculati numarul de aparitii a numarului x in vectorul v.
        	int first = findFirst();
        	int last = findLast();
		int contor = 0;
		if(first == -1)
			return 0;
        	for(int i = first; i <= last; i++) {
        		if(v[i] == x)
        			contor++;
        	}
        	return contor;	
            
        }

        public void solve() {
            readInput();
            writeOutput(getAnswer());
        }
    }

    public static void main(String[] args) {

        new Task().solve();
	
    }
}
