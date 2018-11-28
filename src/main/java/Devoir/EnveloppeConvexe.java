package Devoir;

import java.util.Arrays;

/**
 * Classe modélisant un nuage de points et son enveloppe convexe
 */
public class EnveloppeConvexe {
    /** Nuage de points */
    Point[] points;

    /** Points de l'enveloppe convexe */
    Point[] pointsConv;

    private int pointsSize;

    public EnveloppeConvexe(Point[] points) {
        this.points = points;
        this.pointsSize = points.length;
    }

    public int getSize() { return pointsSize; }

    /* TODO : Utiliser dans le main et non pas comme méthode de la classe */
    public void sortX() {
        Arrays.sort(points);
    }

    /**
     * Fonction récursive qui créer l'enveloppe convexe d'un nuage de points
     */

    public void createConvexe() {
        if(this.getSize() > 3) {
            Point[] pointsRight;
            Point[] pointsLeft = new Point[this.getSize()/2];
            if(this.getSize()%2 == 1) { pointsRight = new Point[this.getSize()/2 + 1]; }
            else { pointsRight = new Point[this.getSize()/2]; }

            for(int index=0; index < this.getSize(); index++) {
                if(index < this.getSize()/2)
                    pointsLeft[index] = new Point(points[index].x, points[index].y);
                else
                    pointsRight[index] = new Point(points[index].x, points[index].y);
            }

            EnveloppeConvexe ConvLeft = new EnveloppeConvexe(pointsLeft);
            EnveloppeConvexe ConvRight = new EnveloppeConvexe(pointsRight);

            ConvLeft.createConvexe();
            ConvRight.createConvexe();
        }
        else {
            if(this.getSize() == 2) {
                /* TODO : probleme avec le sens */
                pointsConv = points;
            }
            else if(this.getSize() == 3) {
                pointsConv = points;
            }
        }
    }

    public double vectorielProduct(double uX, double uY, double vX, double vY) {
        return (uX*vY - uY*vX);
    }

    public boolean parcoursBottomLeft(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public boolean parcoursBottomRight(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public boolean parcoursTopLeft(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {
        int maxIndexLeft = ConvLeft.pointsConv.length-1;
        int minIndexRight = 0;
        int si = maxIndexLeft , pivot = minIndexRight;
        int size = si;
        double uX = ConvLeft.pointsConv[si % size].x - ConvRight.pointsConv[sj].x;
        double uY = ConvLeft.pointsConv[si % size].y - ConvRight.pointsConv[sj].y;
        double vX = ConvLeft.pointsConv[(si - 1) % size].x - ConvRight.pointsConv[sj].x;
        double vY = ConvLeft.pointsConv[(si - 1) % size].y - ConvRight.pointsConv[sj].y;

        if(vectorielProduct(uX, uY, vX, vY) <= 0) {

        }
        else {

        }
    }

    public boolean parcoursTopRight(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {

    }

    public EnveloppeConvexe fusionConvexe(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {
        /*TODO : Fusionner les deux en enlevant les points qui ne sont plus dans la nouvelle enveloppe convexe*/

        int maxIndexLeft = ConvLeft.pointsConv.length-1;
        int minIndexRight = 0;
        int maxIndexRight = ConvRight.pointsConv.length-1;

        boolean echecLeft = false, echecRight = false;
        boolean side = true; /** true = left side, false = right side */

        int si = maxIndexLeft , sj = minIndexRight;
        while(!(echecLeft && echecRight)) { /** TOP */
            if(side) {
                int size = maxIndexLeft;
                double uX = ConvLeft.pointsConv[si % size].x - ConvRight.pointsConv[sj].x;
                double uY = ConvLeft.pointsConv[si % size].y - ConvRight.pointsConv[sj].y;
                double vX = ConvLeft.pointsConv[(si - 1) % size].x - ConvRight.pointsConv[sj].x;
                double vY = ConvLeft.pointsConv[(si - 1) % size].y - ConvRight.pointsConv[sj].y;

                if (vectorielProduct(uX, uY, vX, vY) <= 0) {
                    si--;
                    echecRight = false;
                }
                else {
                    echecLeft = true ;
                    side = !side;
                }
            }
            else {
                int size = maxIndexRight;
                double uX = ConvLeft.pointsConv[sj % size].x - ConvRight.pointsConv[si].x;
                double uY = ConvLeft.pointsConv[sj % size].y - ConvRight.pointsConv[si].y;
                double vX = ConvLeft.pointsConv[(sj + 1) % size].x - ConvRight.pointsConv[si].x;
                double vY = ConvLeft.pointsConv[(sj + 1) % size].y - ConvRight.pointsConv[si].y;

                if (vectorielProduct(uX, uY, vX, vY) <= 0) {
                    sj++;
                    echecLeft = false;
                }
                else {
                    echecRight = true;
                    side = !side;
                }
            }
        }

        side = true;
        si = maxIndexLeft ; sj = minIndexRight;
        while(!(echecLeft && echecRight)) { /** BOTTOM */
            if(side) {
                int size = maxIndexLeft;
                double uX = ConvLeft.pointsConv[si % size].x - ConvRight.pointsConv[sj].x;
                double uY = ConvLeft.pointsConv[si % size].y - ConvRight.pointsConv[sj].y;
                double vX = ConvLeft.pointsConv[(si + 1) % size].x - ConvRight.pointsConv[sj].x;
                double vY = ConvLeft.pointsConv[(si + 1) % size].y - ConvRight.pointsConv[sj].y;

                if (vectorielProduct(uX, uY, vX, vY) >= 0) {
                    si++;
                    echecRight = false;
                }
                else {
                    echecLeft = true ;
                    side = !side;
                }
            }
            else {
                int size = maxIndexRight;
                double uX = ConvLeft.pointsConv[sj % size].x - ConvRight.pointsConv[si].x;
                double uY = ConvLeft.pointsConv[sj % size].y - ConvRight.pointsConv[si].y;
                double vX = ConvLeft.pointsConv[(sj - 1) % size].x - ConvRight.pointsConv[si].x;
                double vY = ConvLeft.pointsConv[(sj - 1) % size].y - ConvRight.pointsConv[si].y;

                if (vectorielProduct(uX, uY, vX, vY) >= 0) {
                    sj--;
                    echecLeft = false;
                }
                else {
                    echecRight = true;
                    side = !side;
                }
            }
        }
    }
}
