import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count=0;
        for(edu.duke.Point point:s.getPoints())
        {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter=getPerimeter(s);
        int numberOfPoints=getNumPoints(s);
        double averageLength=perimeter/numberOfPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSideLength=0;
        Point previousPoint = s.getLastPoint();
        for (Point currentPoint : s.getPoints()) {
            double currentDistance = previousPoint.distance(currentPoint);
            if(largestSideLength<currentDistance)
                largestSideLength=currentDistance;
            previousPoint=currentPoint;
        }
        return largestSideLength;
    }

    public double getLargestX(Shape s) {
        Point lastPoint = s.getLastPoint();
        int lastPointX = lastPoint.getX();
        double largestX = (double) lastPointX;

        for(Point p : s.getPoints()){
            int newX = p.getX();
            if(newX > largestX) {
                largestX = (double) newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource directoryResource = new DirectoryResource();
        double largestPerimeter = 0.0;

        for(File file : directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            double perimeter = getPerimeter(shape);
            if(perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {

        DirectoryResource directoryResource = new DirectoryResource();
        double largestPerimeter = 0.0;
        File largestFile=null;
        for(File file : directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            double perimeter = getPerimeter(shape);
            if(perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                largestFile=file;
            }
        }
        return largestFile.getName();
    }



    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest Perimeter is "+getLargestPerimeterMultipleFiles());

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("The File with largest Perimeter is "+getFileWithLargestPerimeter());
    }
    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points="+getNumPoints(s));
        System.out.println("Average Length ="+getAverageLength(s));
        System.out.println("The largest side of the shape is "+getLargestSide(s));
        System.out.println("The largest x value is "+getLargestX(s));
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();

    }
    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}