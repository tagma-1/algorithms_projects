import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private boolean[][] sites;
   private int gridDimension;
   private int gridTotal;
   private int openSiteCount;
   WeightedQuickUnionUF dataStructureUF;
   
   // create n-by-n grid, with all sites blocked
   public Percolation(int n) {
       gridDimension = n;
       gridTotal = n * n;
       openSiteCount = 0;
       sites = new boolean[gridDimension][gridDimension];
       dataStructureUF = new WeightedQuickUnionUF(gridTotal + 2); // grid squared plus two virtual sites
   }
   
   // open site (row, col) if it is not open already
   public void open(int row, int col) {
       if (invalid(row, col)) throwError(row, col);
       int flatIndex = xyTo1D(row, col);
       boolean siteOpen = isOpen(row - 1, col - 1);
       int[][] indexModifiers = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} }; // an array of index modifiers to get each neighbouring site (left, right, above, below)
       if (!siteOpen) {
           sites[row - 1][col - 1] = true;
           openSiteCount++;
           for(int i = 0; i < 4; i++){
               int neighbourRow = row + indexModifiers[i][0];
               int neighbourCol = col + indexModifiers[i][1];
               if ( !invalid(neighbourRow, neighbourCol) && isOpen(neighbourRow - 1, neighbourCol - 1) ){
                   int flatNeighbourIndex = xyTo1D(neighbourRow, neighbourCol);
                   dataStructureUF.union(flatIndex, flatNeighbourIndex);
               }
           }
        if (row == 1) dataStructureUF.union(flatIndex, 0); // if the site is on the first row, connect it to the virtual site at index 0
        if (row == gridTotal) dataStructureUF.union(flatIndex, gridTotal); // if the site is on the last row, connect it to the virtual site at the end 
       }
   }
   
   // is site (row, col) open?
   public boolean isOpen(int row, int col){
       return sites[row][col];
   }
   
   // is site (row, col) full?
   public int isFull(int row, int col) {
       if (invalid(row, col)) throwError(row, col);
       int siteIndex = xyTo1D(row, col);
       int parentIdentifier = dataStructureUF.find(siteIndex);
       return parentIdentifier;
  }
   
   // number of open sites
   public int numberOfOpenSites(){
       return openSiteCount;
   }
//   
//   public boolean percolates(){
//       
//   }
   
   // convert 2 dimensional coordinates to array index
   private int xyTo1D(int x, int y){
       int rowModifier = x * gridDimension;
       return rowModifier + y;
   }
   
  // check if coordinates are invalid
  private boolean invalid(int row, int col) {
      return row < 1 || row > gridDimension || col < 1 || col > gridDimension;
  }
  
  // throw error for invalid coordinates
  private void throwError(int row, int col) {
      throw new IllegalArgumentException("coordinates " + row + ", " + col + " are not between 0 and " + (gridDimension - 1)); 
  }
  
  public static void main(String[] args) {
      Percolation test = new Percolation(5);
      test.open(1, 1);
      test.open(2, 1);
      test.open(3, 1);
      test.open(4, 1);
      int testFull = test.isFull(4, 1);
      System.out.println(testFull);
  }
  
}