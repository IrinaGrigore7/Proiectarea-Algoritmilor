import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";
        public static final int NMAX = 200005;
        public static final Integer INF = Integer.MAX_VALUE;

        int n;
        int m;

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

                for (int i = 1; i <= n; i++)
                    adj[i] = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    int x, y, w;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                    adj[y].add(new Edge(x, w));
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            /*
            TODO: Calculati costul minim al unui arbore de acoperire
            folosind algoritmul lui Prim.
            */
            int costMin = 0;
            
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            boolean[] visited = new boolean[n + 1];
            int[] d = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                d[i] = INF;
                visited[i] = false;
            }
            d[1] = 0;
            queue.add(new Edge(1, 0));
            while (!queue.isEmpty()) {
                Edge u = queue.poll();
                visited[u.node] = true;
                for (int i = 0; i < adj[u.node].size(); i++) {
                    Edge v = adj[u.node].get(i);
                    if (d[v.node] > v.cost && !visited[v.node]) {
                        d[v.node] = v.cost;
                        queue.add(v);
                    }
                }
            }
            for (int i = 0; i < d.length; i++) {
                if (d[i] != INF)
                    costMin += d[i];
            }
            return costMin;
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
