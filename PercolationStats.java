import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    private Percolation percolationTrial;
    private double[] percolationThresholds;
    private int trialsCount;
    private double mean;
    private double stddev;
    
   // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Both the grid size and the number of trials selected must be greater than zero.");
        percolationThresholds = new double[trials];
        trialsCount = trials;
        for(int i = 0; i < trials; i++) {
            percolationTrial = new Percolation(n);
            while( !percolationTrial.percolates() ) {
                int randomRow = StdRandom.uniform(n) + 1;
                int randomCol = StdRandom.uniform(n) + 1;
                percolationTrial.open(randomRow, randomCol);
            }
            double openSites = percolationTrial.numberOfOpenSites();
            percolationThresholds[i] = openSites / (n * n);
        }
    }
       
   // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(percolationThresholds);
        return mean;
    }
      
   // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(percolationThresholds);
        return stddev;
    }
  
   // low  endpoint of 95% confidence interval    
    public double confidenceLo() {
        return mean - 1.96 * ( stddev / Math.sqrt(trialsCount) );
    }
       
   // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + 1.96 * ( stddev / Math.sqrt(trialsCount) );
    }
   
   // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, t);
        double mean = test.mean();
        double stddev = test.stddev();
        System.out.println("mean = " + mean);
        System.out.println("stddev = " + stddev);
        System.out.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }     
}