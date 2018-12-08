package Devoir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    List<Point> points ;
    int numberOfPoints;
    double minBound=0, maxBound=500;

    public Generator(int numberOfPoints){

        this.numberOfPoints = numberOfPoints;
        points = new ArrayList<Point>(numberOfPoints);
    }

    public void generate(){
        int i;
        double x,y;
        Random r = new Random();

        for(i=0; i<numberOfPoints; i++){
            x = minBound + (maxBound - minBound) * r.nextDouble();
            x = (double) Math.round(x * 100) / 100; // deux chiffres après la virgule
            y = minBound + (maxBound - minBound) * r.nextDouble();
            y = (double) Math.round(y * 100) / 100;

            points.add(new Point(x, y));
        }

        //return points;
    }

    public void printList(){
        for(Point point : points)
            System.out.println(point.x+" "+point.y+" ");
    }

}
