import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        private final static int MOD = 1000000007;

        int n;
        char[] expr;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                String s = sc.next().trim();
                s = " " + s;
                expr = s.toCharArray();
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
            // TODO: Calculati numarul de moduri in care se pot aseza
            // parantezele astfel incat rezultatul expresiei sa fie TRUE.
            // Numarul de moduri se va calcula moulo MOD (1000000007).
            String simb = "";
            String op = "";
           
            for(int i = 1; i <= n; i++){
                if(expr[i] == 'T')
                    simb = simb + "T";
                else
                    if(expr[i] == 'F')
                        simb = simb + "F";
                    else
                        op = op + expr[i];       
            }

            char[] operanzi = simb.toCharArray();
            char[] operatori = op.toCharArray();

            int len = operanzi.length;
            int[][] False = new int[len][len];
            int[][] True = new int[len][len];
            int k;

            for(int i = 0; i < len; i++){
                if(operanzi[i] == 'T'){
                    True[i][i] = 1;
                    False[i][i] = 0;
                }
                else if(operanzi[i] == 'F'){
                    True[i][i] = 0;
                    False[i][i] = 1;
                }
            }

            for(int h = 1; h < len; ++h){
                for(int i = 0, j = h; j < len; ++i, ++j){
                    True[i][j] = 0;
                    False[i][j] = 0;
                    for(int l = 0; l < h; l++){
                        k = i + l;
                        int Total_ik = True[i][k] + False[i][k];
                        int Total_kj = True[k + 1][j] + False[k + 1][j];

                        switch(operatori[k]){
                            case '&':
                                True[i][j] = True[i][j] + True[i][k] * True[k + 1][j];
                                False[i][j] = False[i][j] + (Total_ik * Total_kj) - (True[i][k] * True[k + 1][j]); 
                                break;
                            case '|':
                                False[i][j] = False[i][j] + (False[i][k] * False[k + 1][j]); 
                                True[i][j] = True[i][j] + (Total_ik * Total_kj) - (False[i][k] * False[k + 1][j]); 
                                break;
                            case '^':
                                True[i][j] =  True[i][j] + (False[i][k] * True[k + 1][j] + True[i][k] * False[k + 1][j]); 
                                False[i][j] = False[i][j] + (True[i][k] * True[k + 1][j] + False[i][k] * False[k + 1][j]); 
                                break;

                        }
                    }
                }
            }
            int rez = True[0][len -1] % 1000000007;

            return rez;        }

        public void solve() {
            readInput();
            writeOutput(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
