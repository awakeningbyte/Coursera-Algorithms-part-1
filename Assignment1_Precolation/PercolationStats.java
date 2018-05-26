import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] data;
    private int trails;
    private double mean;
    private double stddev;
    public PercolationStats(int n, int trials) {
        if ((n < 1) || (trials < 1)) {
            throw new IllegalArgumentException("Invalid grid size");
        }
        trails = trials;

        data = new double[trials];
        for (int i = 0; i < trials; i++) {
            mean = 0;
            stddev = 0;
            Percolation pc = new Percolation(n);
            while (!pc.percolates())  {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                pc.open(row, col);
            }
        double et = (double)pc.numberOfOpenSites() / (n * n);
            data[i] = et;
        }
    }

    public double mean() {
        if (mean == 0) {
            mean = StdStats.mean(data);
        }
        return mean;
    }
    public double stddev() {
        if (stddev == 0) {
            stddev = StdStats.stddev(data);
        }
        return stddev;
    }
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trails);
    }
    public double confidenceHi() {
        return mean() - 1.96 * stddev() / Math.sqrt(trails);
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trails);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev =" + stats.stddev());
        System.out.println("95% confidnece interval = [" + stats.confidenceLo() + "," + stats.confidenceHi() + "]");
    }
}