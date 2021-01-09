import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size() + 1);
                for (ArrayList<Integer> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%d%c", arr.get(i), i + 1 == arr.size() ?
                                '\n' : ' ');
                    }
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         public boolean valid( ArrayList<Integer> solution, int k){
        if(k == 0)
            return true;
        if(solution.get(k) > solution.get(k-1))
            return true;
        return false;
    }
    
    void back(ArrayList<ArrayList<Integer>> all, ArrayList<Integer> solution, int k, int n){
        
        for(int i = 0; i < n; i++){
            solution.add(k, i + 1);
            if(valid(solution, k)){
                 ArrayList<Integer> auxSol = new ArrayList<Integer>();
                for(int j = 0; j <= k; j++)
                        auxSol.add(solution.get(j));
                all.add(auxSol);
                back(all, solution,k+1, n);
            }
        }
    }
        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();
             ArrayList<Integer> solution = new ArrayList<Integer>();
            back(all, solution, 0, n);
            // TODO: Construiti toate submultimele multimii {1, ..., N}.

            // Pentru a adauga o noua submultime:
            //   ArrayList<Integer> submultime;
            //   all.add(submultime);

            return all;
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
