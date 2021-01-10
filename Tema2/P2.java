import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;


public class P2 {
	static class Task {
		public static final String INPUT_FILE = "p2.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 500005;
		public static final int INF = (int) 1e9;
		int n;
		int m;
		int source;
		int dest;

		class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();
				dest = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(Integer result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				sb.append(result);
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private Integer getResult() {
			ArrayList<Integer> d = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				d.add(0);
			}

			//in d am retinut distantele minime de la sursa la fiecare nod din graf
			bellman(source, d);
		
			return d.get(dest); //extrag distanta pana la nodul destinatie
		}

		private void bellman(int source, ArrayList<Integer> d) {
			//initializez distantele catre toate nodurile cu infinit
			for (int i = 1; i <= n; i++) {
				d.set(i, INF);
			}

			//setez distanta pana la sursa la 0
			d.set(source, 0);

			//in topsort retin nodurile in ordine topologica
			ArrayList<Integer> topsort = topSort();

			// relaxez toate muchiile
			for (int i = 0; i < n; i++) {
				Integer u = topsort.get(i);
				for (Edge e : adj[u]) {
					int v = e.node;
					int cost = e.cost;
					if (d.get(u) + cost < d.get(v)) {
						d.set(v, d.get(u) + cost);
					}
				}
			}
		}

		private ArrayList<Integer> topSort() {
			
			ArrayList<Integer> topsort = new ArrayList<>();
			Stack<Integer> stack = new Stack<Integer>(); 
			int[] in_muchii = new int[n + 1]; //contorizeaza gradul intern al nodurilor
		
			for (int i = 1; i <= n; i++) {
				ArrayList<Edge> aux =  adj[i];
				for (Edge v : aux) {
					in_muchii[v.node]++;
				}
			}
			
			//adaug in stiva toate nodurile care au gradul intern 0
			for (int i = 1; i <= n; i++) { 
				if (in_muchii[i] == 0) {
					stack.push(i); 
				}
			}

			while (!stack.empty()) {
				int u = stack.pop(); 
				//adaug nodul in vectorul solutie
				topsort.add(u); 
			
				//parcurg toti vecinii nodului si sterg arcele catre acestia
				for (Edge v : adj[u]) {
					in_muchii[v.node]--;
					if (in_muchii[v.node] == 0) { 
						stack.push(v.node); 
					}
				} 
			} 

			return topsort;
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