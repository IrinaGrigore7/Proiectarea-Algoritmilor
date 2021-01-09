import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, k;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                k = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
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

        void back(ArrayList<ArrayList<Integer>> all, ArrayList<Integer> domain,  ArrayList<Integer> solution, int start, int n){
            if(start == n){
                ArrayList<Integer> auxSol = new ArrayList <Integer>(solution);
                all.add(auxSol);
                return;
            }
           
            for( int i = 0; i < domain.size();i++){
                Integer tmp = domain.get(i);
                int aux = i;
                
                solution.add(domain.get(i));
                domain.remove(i);
                
                back(all,domain,solution, start + 1, n);
               
                domain.add(aux,tmp);
                solution.remove(tmp);
            }  
    }

        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();

            // TODO: Construiti toate aranjamentele de N luate cate K ale
            // multimii {1, ..., N}.
            ArrayList<Integer> domain= new ArrayList<>();
             ArrayList<Integer> solution = new ArrayList<>();

            for(int i = 0; i < n; i++)
                domain.add(i+1);
           
            back(all, domain, solution, 0, k);



            // Pentru a adauga un nou aranjament:
            //   ArrayList<Integer> aranjament;
            //   all.add(aranjament);

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
