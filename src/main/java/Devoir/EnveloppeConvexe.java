package Devoir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe modélisant un nuage de points et son enveloppe convexe
 */
public class EnveloppeConvexe {
    /** Nuage de points */
    ArrayList<Point> points;

    /** Points de l'enveloppe convexe */
    ArrayList<Point> pointsConv = new ArrayList<Point>();


    public EnveloppeConvexe(ArrayList<Point> points) { this.points = points; }

    /**
     * Fonction récursive qui créer l'enveloppe convexe d'un nuage de points
     */

    public double vectorielProduct(double uX, double uY, double vX, double vY) {
        return (uX*vY - uY*vX);
    }


    public double angle(Point p1, Point p2, Point p3) {
        double uX, uY, vX, vY;

        uX = p1.x - p3.x;
        uY = p1.y - p3.y;
        vX = p2.x - p3.x;
        vY = p2.y - p3.y;

        return vectorielProduct(uX, uY, vX, vY);
    }

    double surface(Point p0, Point p1, Point p2) {
        // retourne la surface
        // négatif si on tourne dans le sens contraire des aiguilles d'une montre
        // positif sinon
        return ((p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y)) / 2.;
    }

    void partition(ArrayList<Point> lp, ArrayList<Point> env){
        env.clear();
        System.out.println("Entree dans partition");
        // ordonner les points par ordre croissant des abcisses
        Collections.sort(lp);
        if(lp.size()<=3) {
            env.addAll(lp);
            if(  lp.size()==3 &&
                    angle(env.get(0), env.get(1), env.get(2))>0){
                Point p = env.get(1);
                env.remove(p); env.add(p);
            }
            return;
        }
        //On divise en deux
        ArrayList<Point> e1 = new ArrayList<Point>();
        ArrayList<Point> e2 = new ArrayList<Point>();
        for(int i = 0; i<lp.size()/2; ++i)         e1.add(lp.get(i));
        for(int i = lp.size()/2; i<lp.size(); ++i) e2.add(lp.get(i));
        // On calcule les enveloppes de chaque morceau
        ArrayList<Point> env1 = new ArrayList<Point>();
        ArrayList<Point> env2 = new ArrayList<Point>();
        partition(e1, env1);
        System.out.println("sorti partition1");
        partition(e2, env2);
        System.out.println(("sorti partition2"));
        // et on fusionne !
        env.addAll(fusion(env1, env2) );
    }

    public ArrayList<Point> fusion(ArrayList<Point> convLeft, ArrayList<Point> convRight){
        // recherche du point d'abcisse le plus grand dans e1;
        int iMax = 0;
        for( int i = 1; i < convLeft.size(); ++i) if(convLeft.get(i).x > convLeft.get(iMax).x) iMax = i;
        // le point d'abcisse le plus petit dans e2 est en 0
        int iMin = 0;
        // le segment du haut
        int si1 = iMax;
        int sj1 = iMin;
        boolean b = true;
        while(b){
            b = false;

            if(surface(convRight.get(sj1), convLeft.get(si1), convLeft.get((si1+1)%convLeft.size())) > 0){
                si1 = (si1+1)%convLeft.size();
                b = true;
            }
            if(surface(convLeft.get(si1), convRight.get(sj1), convRight.get((sj1-1+convRight.size())%convRight.size())) < 0){
                sj1 = (sj1-1+convRight.size())%convRight.size();
                b = true;
            }
        }
        // le segment du bas
        int si2 = iMax;
        int sj2 = iMin;
        b = true;
        while(b){
            b = false;

            if(surface((convRight.get(sj2)), convLeft.get(si2), convLeft.get((si1-1+convLeft.size())%convLeft.size())) >= 0){
                si2 = (si2-1+convLeft.size())%convLeft.size();
                b = true;
            }
            if(surface(convLeft.get(si2), convRight.get(sj2), convRight.get((sj2+1)%convRight.size())) > 0){
                sj2 = (sj2+1)%convRight.size();
                b = true;
            }
        }
        // fabrication de l'enveloppe
        ArrayList<Point> env = new ArrayList();
        for( int i = 0; i<=si2; ++i)            env.add(convLeft.get(i));
        if(sj1==0){
            for( int i = sj2; i<convRight.size(); ++i) env.add(convRight.get(i));
            env.add(convRight.get(0));
        }
        else
            for( int i = sj2;  i<=sj1; ++i)            env.add(convRight.get(i));
        if(si1!=0) for( int i = si1; i<convLeft.size(); ++i) env.add(convLeft.get(i));

        return env;
    }
}
