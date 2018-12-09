package Devoir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe modélisant un nuage de points et son enveloppe convexe
 */
public class EnveloppeConvexe {
    /** Nuage de points */
    List<Point> points;

    /** Points de l'enveloppe convexe */
    List<Point> pointsConv = new ArrayList<Point>();


    public EnveloppeConvexe(List<Point> points) { this.points = points; }

    //public int getSize() { return pointsSize; }

    /* TODO : Utiliser dans le main et non pas comme méthode de la classe */
    public void sortX() {
        Collections.sort(points);
    }

    /**
     * Fonction récursive qui créer l'enveloppe convexe d'un nuage de points
     */

    public void createConvexe() {
        if(this.points.size() > 3) {
            List<Point> pointsRight = new ArrayList<Point>();
            List<Point> pointsLeft = new ArrayList<Point>();

            for(int index = 0; index < points.size(); index++) {
                if(index < points.size()/2)
                    pointsLeft.add(points.get(index));
                else
                    pointsRight.add(points.get(index));
            }

            EnveloppeConvexe ConvLeft = new EnveloppeConvexe(pointsLeft);
            EnveloppeConvexe ConvRight = new EnveloppeConvexe(pointsRight);

            ConvLeft.createConvexe();
            ConvRight.createConvexe();
        }
        else {
            if(this.points.size() == 2) {
                /* TODO : probleme avec le sens */
                pointsConv = points;
            }
            else if(this.points.size() == 3) {
                double uX =points.get(1).x - points.get(0).x;
                double uY = points.get(1).y - points.get(0).y;
                double vX = points.get(2).x - points.get(0).x;
                double vY = points.get(2).y - points.get(0).y;

                if(vectorielProduct(uX, uY, vX, vY) <= 0)
                    pointsConv = points;
                else {
                    pointsConv.add(points.get(0));
                    pointsConv.add(points.get(2));
                    pointsConv.add(points.get(1));
                }
            }
        }
    }

    public double vectorielProduct(double uX, double uY, double vX, double vY) {
        return (uX*vY - uY*vX);
    }

    /*public boolean parcoursBottomLeft(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public boolean parcoursBottomRight(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public boolean parcoursTopLeft(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public boolean parcoursTopRight(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }*/

    public EnveloppeConvexe fusionConvexe(EnveloppeConvexe convLeft, EnveloppeConvexe convRight) {
        /*TODO : Fusionner les deux en enlevant les points qui ne sont plus dans la nouvelle enveloppe convexe*/
        EnveloppeConvexe fusion = new EnveloppeConvexe(Point.fusion(convLeft.points, convRight.points));

        int maxIndexLeft = convLeft.pointsConv.size() - 1;
        int minIndexRight = 0;
        int maxIndexRight = convRight.pointsConv.size() - 1;

        boolean echecLeft = false, echecRight = false;
        boolean side = true; /** true = left side, false = right side */

        int si = maxIndexLeft, sj = minIndexRight;
        while (!(echecLeft && echecRight)) { /** TOP */
            if (side) {
                int size = maxIndexLeft;
                double uX = convLeft.pointsConv.get(si % size).x - convRight.pointsConv.get(sj).x;
                double uY = convLeft.pointsConv.get(si % size).y - convRight.pointsConv.get(sj).y;
                double vX = convLeft.pointsConv.get((si - 1) % size).x - convRight.pointsConv.get(sj).x;
                double vY = convLeft.pointsConv.get((si - 1) % size).y - convRight.pointsConv.get(sj).y;

                if (vectorielProduct(uX, uY, vX, vY) <= 0) {
                    si--;
                    echecRight = false;
                } else {
                    echecLeft = true;
                    side = false;
                }
            } else {
                int size = maxIndexRight;
                double uX = convLeft.pointsConv.get(sj % size).x - convRight.pointsConv.get(si).x;
                double uY = convLeft.pointsConv.get(sj % size).y - convRight.pointsConv.get(si).y;
                double vX = convLeft.pointsConv.get((sj + 1) % size).x - convRight.pointsConv.get(si).x;
                double vY = convLeft.pointsConv.get((sj + 1) % size).y - convRight.pointsConv.get(si).y;

                if (vectorielProduct(uX, uY, vX, vY) <= 0) {
                    sj++;
                    echecLeft = false;
                } else {
                    echecRight = true;
                    side = true;
                }
            }
        }
        Point topLeft = convLeft.pointsConv.get(si);
        Point topRight = convRight.pointsConv.get(sj);

        side = true;
        si = maxIndexLeft;
        sj = minIndexRight;
        while (!(echecLeft && echecRight)) { /** BOTTOM */
            if (side) {
                int size = maxIndexLeft;
                double uX = convLeft.pointsConv.get(si % size).x - convRight.pointsConv.get(sj).x;
                double uY = convLeft.pointsConv.get(si % size).y - convRight.pointsConv.get(sj).y;
                double vX = convLeft.pointsConv.get((si + 1) % size).x - convRight.pointsConv.get(sj).x;
                double vY = convLeft.pointsConv.get((si + 1) % size).y - convRight.pointsConv.get(sj).y;

                if (vectorielProduct(uX, uY, vX, vY) >= 0) {
                    si++;
                    echecRight = false;
                } else {
                    echecLeft = true;
                    side = !side;
                }
            } else {
                int size = maxIndexRight;
                double uX = convLeft.pointsConv.get(sj % size).x - convRight.pointsConv.get(si).x;
                double uY = convLeft.pointsConv.get(sj % size).y - convRight.pointsConv.get(si).y;
                double vX = convLeft.pointsConv.get((sj - 1) % size).x - convRight.pointsConv.get(si).x;
                double vY = convLeft.pointsConv.get((sj - 1) % size).y - convRight.pointsConv.get(si).y;

                if (vectorielProduct(uX, uY, vX, vY) >= 0) {
                    sj--;
                    echecLeft = false;
                } else {
                    echecRight = true;
                    side = !side;
                }
            }
        }
        Point bottomLeft = convLeft.pointsConv.get(si);
        Point bottomRight = convRight.pointsConv.get(sj);

        /*TODO : fusionner les enveloppes en fonction  des quatres points */
        int index;
        for(index = 0; index < convLeft.pointsConv.size(); index++) {
            if(index > convLeft.pointsConv.indexOf(topLeft) && index < convLeft.pointsConv.indexOf(bottomLeft))
                convLeft.pointsConv.remove(index);
        }

        for(index = 0; index < convRight.pointsConv.size(); index++) {
            if(index < convRight.pointsConv.indexOf(topLeft) || index > convRight.pointsConv.indexOf(bottomLeft))
                convRight.pointsConv.remove(index);
        }

        /*TODO : fusionner les deux listes */
        side = true;
        for(index = 0; index < convRight.pointsConv.size()+convLeft.pointsConv.size(); index++) {
            if(side) {
                if (index == convLeft.pointsConv.indexOf(topLeft)) {
                    fusion.pointsConv.add(convLeft.pointsConv.get(index));
                    side = false;
                }
                fusion.pointsConv.add(convLeft.pointsConv.get(index));
            }
            else {

            }
        }

        return fusion;
    }
//salut

}
