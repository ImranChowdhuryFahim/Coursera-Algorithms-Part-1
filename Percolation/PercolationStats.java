import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double COEF = 1.96;
    private final double[] trialsArray;
    private final int trialsCount;
    

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }

        trialsArray = new double[trials];
        trialsCount = trials;

        Percolation p;
        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }

            trialsArray[i] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialsArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialsArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((COEF * stddev()) / Math.sqrt(trialsCount));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((COEF * stddev()) / Math.sqrt(trialsCount));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 2;
        int trials = 2;

        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }

        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + "[" + ps.confidenceLo() +
                "," + ps.confidenceHi() + "]");

    }

}