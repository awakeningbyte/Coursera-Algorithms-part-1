import java.io.Console;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
public class PercolationStats {
    double[] data;
    int _trails;
    public PercolationStats(int n, int trails){
        if ((n < 0) || (trails < 0)) {
            throw new IllegalArgumentException();
        }
        _trails = trails;

        data = new double[trails];
        for (int i =0; i < trails; i++) {
            Stopwatch sw = new Stopwatch();
            Percolation pc = new Percolation(n);
            
           while(!pc.percolates())  {
            //for (int j =0; j <10;j++){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                pc.open(row, col);
            }
            double et =sw.elapsedTime();
            data[i] = et;
        }
    }

    public double mean() {
        return StdStats.mean(data);
    }
    public double stddev() {
        return StdStats.stddev(data);
    }
    public double confidenceLo() {
        return mean()-1.96*stddev()/Math.sqrt(_trails);
    }
    public double confidenceHi() {
        return mean()-1.96*stddev()/Math.sqrt(_trails);
    }
    public static void main(String[] args) {
        //System.out.println("0:"+args[0]+" 1:"+ args[1]);
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt( args[1]);
        PercolationStats stats = new PercolationStats(n, trails);
        System.out.println("mean = "+ stats.mean());
        System.out.println("stddev ="+ stats.stddev());
        System.out.println("95% confidnece interval = [" + stats.confidenceLo() +","+stats.confidenceHi()+"]");
    }
}