import java.io.Console;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public PercolationStats(int n, int trails){
        if ((n < 0) || (trails < 0)) {
            throw new IllegalArgumentException();
        }
    }

    public double mean() {
        return 0;
    }
    public double stddev() {
        return 0;
    }
    public double confidenceLo() {
        return 0;
    }
    public double confidenceHi() {
        return 0;
    }
    public static void main(String[] args) {
        //System.out.println("0:"+args[0]+" 1:"+ args[1]);
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt( args[1]);
        PercolationStats stats = new PercolationStats(n, trails);
    }
}