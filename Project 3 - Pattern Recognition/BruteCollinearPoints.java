import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
   
   private int segmentCounter = 0;
   private ArrayList<LineSegment> lineSegments;
   
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) {
  
       if (points == null) throw new IllegalArgumentException("Array of points must be provided.");
       int n = points.length;  
       lineSegments = new ArrayList<LineSegment>();
               
       // iterate through the array 4 times to find possible combinations (not permutations)
       for (int i = 0; i < n; i++) {
           
           for (int j = i + 1; j < n; j++) {
               if (points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Array contains repeated points.");
               if (points[i] == null || points[j] == null) throw new IllegalArgumentException("Array cannot contain null points.");
               double secondPointSlope = points[i].slopeTo(points[j]);
               
               for (int k = j + 1; k < n; k++) { 
                   double thirdPointSlope = points[i].slopeTo(points[k]);
                   if (secondPointSlope != thirdPointSlope) continue; // skip the fourth loop if the current three points are not collinear
                   
                   for (int m = k + 1; m < n; m++) {
                       double fourthPointSlope = points[i].slopeTo(points[m]);
                       if (fourthPointSlope == secondPointSlope) {
                           Point[] segmentArray = { points[i], points[j], points[k], points[m] };
                           Arrays.sort(segmentArray, 0, 4);
                           lineSegments.add(new LineSegment(segmentArray[0], segmentArray[3]));
                           segmentCounter++;
                       }
                   }
               }
           }
       }
   }
    
   public int numberOfSegments() { return segmentCounter; }

   public LineSegment[] segments() { 
       LineSegment[] segmentArray = new LineSegment[segmentCounter];
       for(int i = 0; i < segmentCounter; i++) {
           segmentArray[i] = lineSegments.get(i);
       }
       return segmentArray;
   }
   
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