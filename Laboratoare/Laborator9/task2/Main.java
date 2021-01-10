import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 50005;
		public static final Integer INF = Integer.MAX_VALUE;

		int n;
		int m;
		int source;

		public class Edge implements Comparable<Edge>{
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
			@Override
			public int compareTo(Edge edge){
				if(this.cost > edge.cost)
					return 1;
				else if(this.cost < edge.cost)
					return -1;
				else
					return 0;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];

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

		private void writeOutput(ArrayList<Integer> result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				if (result.size() == 0) {
					sb.append("Ciclu negativ!\n");
				} else {
					for (int i = 1; i <= n; i++) {
						sb.append(result.get(i)).append(' ');
					}
					sb.append('\n');
				}
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private ArrayList<Integer> getResult() {
			// TODO: Gasiti distantele minime de la nodul source la celelalte noduri
			// folosind BellmanFord pe graful orientat cu n noduri, m arce stocat in
			// adj.
			//	d[node] = costul minim / lungimea minima a unui drum de la source la
			//	nodul node;
			//	d[source] = 0;
			//	d[node] = -1, daca nu se poate ajunge de la source la node.

			// Atentie:
			// O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
			//	adj[x].get(i).node = nodul adiacent lui x,
			//	adj[x].get(i).cost = costul.

			// In cazul in care exista ciclu de cost negativ, returnati un vector gol:
			//	return new ArrayList<Integer>();
			PriorityQueue<Edge> queue = new PriorityQueue<>();
			ArrayList<Integer> d = new ArrayList<>();
			int[] distNeg = new int[n+1];
			boolean[] visited = new boolean[n + 1];
			for (int i = 0; i <= n; i++) {
				d.add(i, INF);
			}

			d.add(source, 0);
			Arrays.fill(visited, false);
			queue.add(new Edge(source, 0));
			distNeg[source] = -1;

			while(!queue.isEmpty()) {
				int indexNode = queue.peek().node;
				visited[indexNode] = true;
				int minCost = queue.poll().cost;
				for(Edge edge : adj[indexNode]){
					int newDist = d.get(indexNode) + edge.cost;
					if(newDist < d.get(edge.node)) {
						d.set(edge.node, newDist);
						queue.add(new Edge(edge.node, d.get(edge.node)));
					}
					distNeg[edge.node]++;
					if(distNeg[edge.node] > n)
						return new ArrayList<Integer>();
				}
			}

			for (int i = 0; i <= n; i++) {
				if(d.get(i) == INF)
					d.set(i, -1);
			}

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
