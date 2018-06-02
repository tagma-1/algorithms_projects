import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
   
   private int segmentCounter = 0;
   private LineSegment[] lineSegments;
   
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) {
  
       if (points == null) throw new IllegalArgumentException("Array of points must be provided.");
       int n = points.length; 
       
       // check for null points prior to sorting
       for (int i = 0; i < n; i++) {
           if (points[i] == null) throw new IllegalArgumentException("Array at index " + i + " is null.");
       }  
                  
       // initialise an array with a length equal to the maximum number of possible line segments 
       lineSegments = new LineSegment[(n - 1) / 3];
       
       // sort the array by y-coordinates (natural order) then check for repeated points
       Arrays.sort(points, 0, n);
       for (int i = 0; i < n; i++) {
           if (i > 0 && points[i] == points[i - 1]) throw new IllegalArgumentException("Array contains repeated points.");
       } 

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
   
   public LineSegment[] segments() { return lineSegments; }
   
   public static void main(String[] args) {

       // read the n points from a file
       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }
       
       // draw the points
       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       for (Point p : points) {
           p.draw();
       }
       StdDraw.show();
       
       // print and draw the line segments
       BruteCollinearPoints collinear = new BruteCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       StdDraw.show();
   }   
   
}