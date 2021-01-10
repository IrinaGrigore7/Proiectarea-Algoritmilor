import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 100005; // 10^5

		int n;
		int m;

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];

		int source;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				for (int i = 1; i <= n; i++) {
					pw.printf("%d%c", result[i], i == n ? '\n' : ' ');
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int[] getResult() {
			int d[] = new int[n + 1];
            int p[] = new int[n + 1];
            Boolean visited[] = new Boolean[n + 1];
            int u;
            LinkedList<Integer> queue = new LinkedList<Integer>(); 
			for (int i = 0; i <= n; i++) {
                p[i] = -1;
                visited[i] = false;
                d[i] = -1;
            }
            d[source] = 0;
            visited[source] = true;
            queue.add(source);
            while(queue.size() != 0) {
                u = queue.pollFirst();
                for(int i = 0;  i < adj[u].size(); i++)
                {  
                    int v = adj[u].get(i);
                    if (visited[v] == false) 
                    { 
                        visited[v] = true; 
                        p[v] = u;
                        d[v] = d[u] + 1;
                        queue.add(v); 
                    } 
                } 
            }
			// TODO: Faceti un BFS care sa construiasca in d valorile d[i] = numarul
			// minim de muchii de parcurs de la nodul source la nodul i.
			// d[source] = 0
			// d[x] = -1 daca nu exista drum de la source la x.
			// *******
			// ATENTIE: nodurile sunt indexate de la 1 la n.
			// *******

			return d;
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
