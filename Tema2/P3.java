import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;


public class P3 {
	static class Task {
		public static final String INPUT_FILE = "p3.in";
		public static final String OUTPUT_FILE = "p3.out";
		public static final int NMAX = 5000005;
		public static final Double MINUS_INF = Double.NEGATIVE_INFINITY;
		int n;
		int m;
		double initialEnergy;

		class Edge implements Comparable<Edge> {
			public int node;
			public double cost;

			Edge(int _node, double _cost) {
				node = _node;
				cost = _cost;
			}
			
			@Override
			public int compareTo(Edge edge) {
				if (this.cost > edge.cost) {
					return -1;
				} else if (this.cost < edge.cost) {
					return 1;
				} else {
					return 0;
				}
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];
		ArrayList<Integer> parent = new ArrayList<>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				initialEnergy = sc.nextDouble();

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

		private void writeOutput(double energy, ArrayList<Integer> path) {
			try {
				StringBuilder sb = new StringBuilder();
				sb.append(initialEnergy * energy);
				sb.append('\n');
				for (int i = 0; i < path.size(); i++) {
					sb.append(path.get(i)).append(' ');
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private double getEnergy() {
			ArrayList<Double> d = new ArrayList<>();
			
			//initializez vectorul de distante si pe cel de parinti
			for (int i = 0; i <= n; i++) {
				d.add(i, MINUS_INF);
				parent.add(-1);
			}

			d.add(1, 1.0);
			PriorityQueue<Edge> queue = new PriorityQueue<>();
			queue.add(new Edge(1, 1.0));

			while (!queue.isEmpty()) {
				int u = queue.peek().node;
				double minCost = queue.poll().cost;
				
				for (Edge edge : adj[u]) {
					double newCost = minCost * (double) (1 - edge.cost / 100);
					if (newCost > d.get(edge.node)) {
						//adaug energia pe care o are la nodul respectiv
						d.set(edge.node, newCost);
						queue.add(new Edge(edge.node, d.get(edge.node)));
						parent.set(edge.node, u);
					}
				}
			}
			return d.get(n);
		}

		private ArrayList<Integer> getPath(ArrayList<Integer> parent) {
			//construiesc drumul parcurs de la destinatie la sursa folosindu-ma de
			//vectorul de parinti
			ArrayList<Integer> path = new ArrayList<>();
			path.add(n);
			int u = parent.get(n);
			while (u != -1) {
				path.add(u);
				u = parent.get(u);
			}

			//inversez vectorul, pentru a obtine drumul de la sursa la destinatie
			Collections.reverse(path);
			return path;
		}

		public void solve() {
			readInput();
			writeOutput(getEnergy(), getPath(parent));
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}