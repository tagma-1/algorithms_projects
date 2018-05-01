import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    Percolation percolationTrial;
    public int[] openSitesAtPercolation;
    
   // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        openSitesAtPercolation = new int[trials];
        for(int i = 0; i < trials; i++) {
            percolationTrial = new Percolation(n);
            while( !percolationTrial.percolates() ) {
                int randomRow = StdRandom.uniform(n) + 1;
                int randomCol = StdRandom.uniform(n) + 1;
                percolationTrial.open(randomRow, randomCol);
            }
            openSitesAtPercolation[i] = percolationTrial.numberOfOpenSites();
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