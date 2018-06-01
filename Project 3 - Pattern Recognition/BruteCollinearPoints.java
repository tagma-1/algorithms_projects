import java.util.Arrays;

public class BruteCollinearPoints {
   
   private int segmentCounter = 0;
   private LineSegment[] lineSegments;
   
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) {
       int n = points.length;
       
       // initialise an array with a length equal to the maximum number of possible line segments 
       lineSegments = new LineSegment[n / 4];
       
       // sort the array by y-coordinates (natural order)
       Arrays.sort(points, 0, n);
       
       // iterate through the array 4 times to find possible combinations (not permutations)
       for (int i = 0; i < n - 3; i++) {
           
           for (int j = i + 1; j < n - 2; j++) {
               
               double secondPointSlope = points[i].slopeTo(points[j]);
               
               for (int k = j + 1; k < n - 1; k++) {
                   
                   double thirdPointSlope = points[i].slopeTo(points[k]);
                   if (secondPointSlope != thirdPointSlope) continue; // skip the fourth loop if the current three points are not collinear
                   
                   for (int l = k + 1; l < n; l++) {
                       
                       double fourthPointSlope = points[i].slopeTo(points[l]);
                       if (fourthPointSlope == secondPointSlope) {
                           segmentCounter++;
                           Point minPoint = points[i];
                           Point maxPoint = points[l];
                           lineSegments[segmentCounter - 1] = new LineSegment(minPoint, maxPoint);
                       }
                   }
               }
           }
       }
   }
    
   public int numberOfSegments() { return segmentCounter; }
   
   public LineSegment[] segments() {
       return lineSegments;
   }
   
}