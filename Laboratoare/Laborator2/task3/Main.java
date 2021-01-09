import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    static class Homework implements Comparable<Homework> {
        public int deadline;
        public int score;

        public Homework() {
            deadline = 0;
            score = 0;
        }

        public int compareTo(Homework hom){
            
            if(this.score > hom.score)
                return 1; 
            if(this.score < hom.score)
                return -1;
            else                   
                return 0;
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        Homework[] hws;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                hws = new Homework[n];
                for (int i = 0; i < n; i++) {
                    hws[i] = new Homework();
                    hws[i].deadline = sc.nextInt();
                    hws[i].score = sc.nextInt();
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
            // TODO: Aflati punctajul maxim pe care il puteti obtine
            // planificand optim temele.
            Arrays.sort(hws);
            int deadline = 0;
            int sum = 0;
            for(int i = 0; i < n; i++){
                System.out.println(hws[i].deadline);
                if(hws[i].deadline > deadline)
                    deadline = hws[i].deadline;
            }
            int weeks[] = new int[deadline];
            for(int j = deadline - 1; j >= 0; j--){
                if(weeks[j] == 0){
                    n--;
                    weeks[j] = hws[n].score;

                }
                else{
                    n--;
                    weeks[j--] = hws[n].score;
                }
            }
            for(int k = 0; k < deadline; k++){
                sum += weeks[k];
          }

           return sum;
            // }
            // int index = deadline;
            // while(deadline >= 0){

            //     int nr = hws[--n].deadline;
              

            //     if( weeks[nr] == 0){
            //         deadline--;
            //         weeks[deadline] = hws[n].score;
                    
            //     }
            //     else {
            //         deadline--;
            //         weeks[deadline] = hws[n].score;
                    
            //     }
            // }
            //   for(int k = 0; k < index; k++){
            //     System.out.println(weeks[k]);
            //     sum += weeks[k];
            //  }
            
           // return sum;
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
