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
		public static final int NMAX = 1005;
		public static final Integer INF = 1000000001;

		int n;
		int m;
		int p[];
		boolean visited[];

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];
		int C[][];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				
				C = new int[n + 1][n + 1];
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y, z;
					x = sc.nextInt();
					y = sc.nextInt();
					z = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
					C[x][y] += z;
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
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		private boolean bfs() {
            p = new int[n + 1];
			visited = new boolean[n + 1];
            int u;
            LinkedList<Integer> queue = new LinkedList<Integer>(); 
			for (int i = 0; i <= n; i++) {
                p[i] = -1;
                visited[i] = false;  
            }
            
            visited[1] = true;
            queue.add(1);
            while(queue.size() != 0) {
                u = queue.pollFirst();
                if(u != n) {
	                for(int i = 0;  i < adj[u].size(); i++) {  
	                    int v = adj[u].get(i);
	                    if (visited[v] == false && C[u][v] != 0) 
	                    { 
	                        visited[v] = true; 
	                        p[v] = u;
	                        queue.add(v); 
	                    } 
                	}
                } 
            	
            }
			return visited[n];
		}

		private int getResult() {
			int flow = 0;
            int currentFlow = 0;
            while(bfs() == true) {
            	for(int node : adj[n]){
	            if(visited[node] == true) {
	                currentFlow = INF;
	                p[n] = node;
	                int crtNode = n;
	                while(crtNode != 1) {
	                    if (currentFlow > C[p[crtNode]][crtNode])
	                        currentFlow = C[p[crtNode]][crtNode];
	                    crtNode = p[crtNode];
	                }

	                if(currentFlow == 0)
	                	continue;
	                else {
	                    for(crtNode = n; crtNode != 1; crtNode = p[crtNode]){
	                        C[p[crtNode]][crtNode] = C[p[crtNode]][crtNode] - currentFlow;
	                        C[crtNode][p[crtNode]] = C[crtNode][p[crtNode]] + currentFlow;
	                    }

	                    flow += currentFlow;
	                }
	            }
	        }
        }
            return flow;
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
