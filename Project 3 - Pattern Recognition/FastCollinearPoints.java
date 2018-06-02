import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
   
   private int segmentCounter = 0;
   private ArrayList<LineSegment> lineSegments;
   
   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points) {
     
       if (points == null) throw new IllegalArgumentException("Array of points must be provided.");
       int n = points.length; 
       
       // check for null points prior to sorting
       for (int i = 0; i < n; i++) {
           if (points[i] == null) throw new IllegalArgumentException("Array at index " + i + " is null.");
       }  
       
       // sort the array by y-coordinates (natural order) then check for repeated points
       Arrays.sort(points, 0, n);
       for (int i = 0; i < n; i++) {
           if (i > 0 && points[i] == points[i - 1]) throw new IllegalArgumentException("Array contains repeated points.");
       } 
                  
       // initialise an ArrayList 
       lineSegments = new ArrayList<LineSegment>();
       
       /* (1) Iterate through the points, setting each point as the origin (p)and sorting the remainder of the array's
        * points after p according to their slope to p. 
        * (2) These remaining points are then iterated through to find groups of 3 or more with the same slope to p. 
        * (3) These groups of 3 or more are each combined with p into a segment sub-array which is sorted according to y-coordinates. 
        * (4) The first and last points in this sub-array are then used to make a LineSegment object which is added to LineSegment[].
        */
       
       for (int i = 0; i < n - 3; i++) {
           Point origin = points[i]; 
           Arrays.sort(points, i + 1, n, origin.slopeOrder()); 
           int groupSize = 1; 
           for(int j = i + 2; j < n; j++) {
               if ( origin.slopeTo(points[j]) == origin.slopeTo(points[j - 1]) ) { 
                   groupSize++;
                   if (j == n - 1 && groupSize > 2) createLineSegment(points, groupSize, j, origin);
               } else if (groupSize > 2) { 
                   createLineSegment(points, groupSize, j, origin);
                   groupSize = 1;   
               } else {
                   groupSize = 1;
               }
           }
       }
   }
   
   private void createLineSegment(Point[] points, int groupSize, int endPoint, Point origin) {
       
       // create a sub-array for the segment (including the origin point) and sort according to y-coordinates 
       Point[] segmentArray = new Point[groupSize + 1]; 
       segmentArray[0] = origin;
       int tail = 1;
       for (int k = endPoint - groupSize; k < endPoint; k++) {
           segmentArray[tail] = points[k];
           tail++;
       }
       Arrays.sort(segmentArray, 0, groupSize + 1); 
                   
       // create a new line segment
       Point minPoint = segmentArray[0];
       Point maxPoint = segmentArray[groupSize];
       lineSegments.add( new LineSegment(minPoint, maxPoint) );
       segmentCounter++;
   }
                                       
   public int numberOfSegments() { return segmentCounter; }
   
   public ArrayList<LineSegment> segments() { return lineSegments; }
   
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
       FastCollinearPoints collinear = new FastCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       StdDraw.show();
   }   

}