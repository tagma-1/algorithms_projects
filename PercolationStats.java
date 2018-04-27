import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    StdStats stats;
    StdRandom random;
    Percolation percolationTrial;
    
   // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        double[] openSitesAtPercolation = new double[trials];
        for(int i = 0; i < trials; i++) {
            percolationTrial = new Percolation(n);
//            while( !percolationTrial.percolates() ) {
//                double randomRow =
//                double randomCol =
//            }

            // randomly open site
            // check for percolation, break if true
            // return number of open sites
        }
    }
       
   // sample mean of percolation threshold
//    public double mean() {}
//       
//   // sample standard deviation of percolation threshold
//    public double stddev() {}
//       
//   // low  endpoint of 95% confidence interval    
//    public double confidenceLo() {}
//       
//   // high endpoint of 95% confidence interval
//    public double confidenceHi() {}
//       
//   // test client
//    public static void main(String[] args) {}     
}