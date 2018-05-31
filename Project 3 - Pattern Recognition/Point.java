import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Point implements Comparable<Point>{

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    
    public double slopeTo(Point that) {
        double rise = that.y - this.y;
        double run = that.x - this.x;
        if (rise == 0 && run == 0) return Double.NEGATIVE_INFINITY; // equal
        if (run == 0) return Double.POSITIVE_INFINITY; // vertical line
        if (rise == 0) return 0.0; // horizontal line 
        return rise / run;    
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y == that.y) {
            if (this.x == that.x) return 0;
            if (this.x < that.x) return -1;
            return 1;
        } else if (this.y < that.y) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point> () {
            public int compare(Point a, Point b) {
                return Double.compare(slopeTo(a), slopeTo(b));
            }
        }; 
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
   public static void main(String[] args) {
       Point a = new Point(1, 1); 
       Point b = new Point(4, 12); // positive gradient (~3.66)
       Point c = new Point(5, -3); // negative gradient (-1)
       Point d = new Point(-3, -3); // positive gradient (1)
       Point e = new Point(1, 1); // equal point (negative infinity)
       Point f = new Point(1, 5); // vertical line (positive infinity)
       Point g = new Point(5, 1); // horizontal line (positive 0.0)
       
       List<Point> test = new ArrayList<Point>();
       test.add(a);
       test.add(b);
       test.add(c);
       test.add(d);
       test.add(e);
       test.add(f);
       test.add(g);
       
       // test slopeTo() 
       System.out.println("\nslopeTo() method (gradient) test");
       System.out.println("a.slopeTo(b) == 11/3: " + (a.slopeTo(b) == 11.0 / 3.0 ));
       System.out.println("a.slopeTo(c) == -1: " + (a.slopeTo(c) == -1 ));
       System.out.println("a.slopeTo(d) == 1: " + (a.slopeTo(d) == 1 ));
       System.out.println("a.slopeTo(e) == negative infinity: " + (a.slopeTo(e) == Double.NEGATIVE_INFINITY));
       System.out.println("a.slopeTo(f) == positive infinity: " + (a.slopeTo(f) == Double.POSITIVE_INFINITY ));
       System.out.println("a.slopeTo(g) == 0.0: " + (a.slopeTo(g) == 0.0 ));

       // test comparable
       Collections.sort(test);
       System.out.println("\nComparable sorting position test");
       System.out.println("first == d: " + (test.get(0) == d));
       System.out.println("second == c: " + (test.get(1) == c));
       System.out.println("third == a: " + (test.get(2) == a));
       System.out.println("fourth == e: " + (test.get(3) == e));
       System.out.println("fifth == g: " + (test.get(4) == g));
       System.out.println("sixth == f: " + (test.get(5) == f));
       System.out.println("seventh == b: " + (test.get(6) == b));

       // test comparator
       Collections.sort(test, a.slopeOrder());
       System.out.println("\nComparator sorting position test");
       System.out.println("first == a: " + (test.get(0) == a));
       System.out.println("second == e: " + (test.get(1) == e));
       System.out.println("third == c: " + (test.get(2) == c));
       System.out.println("fourth == g: " + (test.get(3) == g));
       System.out.println("fifth == d: " + (test.get(4) == d));
       System.out.println("sixth == b: " + (test.get(5) == b));
       System.out.println("seventh == f: " + (test.get(6) == f));
       
   }
}