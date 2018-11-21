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

    private int size;

    public EnveloppeConvexe(Point[] points) {
        this.points = points;
        this.size = points.length;
    }

    public int getSize() { return size; }

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

    public void fusionConvexe(EnveloppeConvexe ConvLeft, EnveloppeConvexe ConvRight) {
        /* TODO : fusion des deux tableaux convexes dans celui de this */

    }
}
