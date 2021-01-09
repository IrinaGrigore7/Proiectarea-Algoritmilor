import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static class Obj implements Comparable<Obj>{
        public int weight;
        public int price;

        public Obj() {
            weight = 0;
            price = 0;
        }

         public int compareTo(Obj objs){
        
    			if((double)this.price/this.weight < (double)objs.price/objs.weight)
    				return 1; 
    			if((double)this.price/this.weight > (double)objs.price/objs.weight) 
    				return -1;
    			else                   
    				return 0;
         }

    };

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, w;
        Obj[] objs;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                w = sc.nextInt();
                objs = new Obj[n];
                for (int i = 0; i < n; i++) {
                    objs[i] = new Obj();
                    objs[i].weight = sc.nextInt();
                    objs[i].price = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double getResult() {
            // TODO: Aflati profitul maxim care se poate obtine cu
            // obiectele date.
            double a = 0;
            int i = 0;
         
        	Arrays.sort(objs);
        	
        	for(int j = 0; j < n; j++){
        		if(objs[j].weight <= w){
        			a = a + objs[j].price;
        			w = w - objs[j].weight;
        			i = j + 1;
        		}
        	}

        	if(w > 0){
        		System.out.println(w);
        	 	a = a + (double)w / objs[i].weight * objs[i].price;
        	}
        		
            return a;
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
