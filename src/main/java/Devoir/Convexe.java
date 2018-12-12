package Devoir;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

public class Convexe {

    /** Signe de l'angle formé par p0p1 et p0p2
     *
     * @return positif si sens inverse trigo
     *         negatif sinon
     */
    double angle(Point p0, Point p1, Point p2) {
        double uX = p1.x - p0.x;
        double uY = p1.y - p0.y;
        double vX = p2.x - p0.x;
        double vY = p2.y - p0.y;

        return (uX*vY - uY*vX);
    }

    void makeConvex(ArrayList<Point> points, ArrayList<Point> env) {
        env.clear();

        /** On ordonne les points par ordre croissant sur l'axe des abscisses */
        Collections.sort(points);
        if (points.size() <= 3) {
            env.addAll(points);
            if (points.size() == 3 &&
                    angle(env.get(0), env.get(1), env.get(2)) > 0) {
                Point p = env.get(1);
                env.remove(p);
                env.add(p);
            }
            return;
        }

        /** On divise l'ensemble des points en 2 */
        ArrayList<Point> e1 = new ArrayList<>();
        ArrayList<Point> e2 = new ArrayList<>();
        for (int i = 0; i < points.size() / 2; ++i)
            e1.add(points.get(i));
        for (int i = points.size() / 2; i < points.size(); ++i)
            e2.add(points.get(i));


        /** On applique l'algo sur chacune des deux sous enveloppe */
        ArrayList<Point> left = new ArrayList<Point>();
        ArrayList<Point> right = new ArrayList<Point>();
        makeConvex(e1, left);
        makeConvex(e2, right);

        env.addAll(fusion(left, right));
    }

    public ArrayList<Point> fusion(ArrayList<Point> left, ArrayList<Point> right) {
        int iMax = 0;
        int iMin = 0;
        for (int i = 1; i < left.size(); ++i) if (left.get(i).x > left.get(iMax).x) iMax = i;


        /** TOP */
        int si1 = iMax;
        int sj1 = iMin;
        boolean noFail = true;
        while (noFail) {
            noFail = false;
            if (angle(right.get(sj1), left.get(si1), left.get((si1 + 1) % left.size())) > 0) {
                si1 = (si1 + 1) % left.size();
                noFail = true;
            }
            if (angle(left.get(si1), right.get(sj1), right.get((sj1 - 1 + right.size()) % right.size())) < 0) {
                sj1 = (sj1 - 1 + right.size()) % right.size();
                noFail = true;
            }
        }

        /** BOTTOM */
        int si2 = iMax;
        int sj2 = iMin;
        noFail = true;
        while (noFail) {
            noFail = false;
            if (angle(right.get(sj2), left.get(si2), left.get((si2 - 1 + left.size()) % left.size())) < 0) {
                si2 = (si2 - 1 + left.size()) % left.size();
                noFail = true;
            }
            if (angle(left.get(si2), right.get(sj2), right.get((sj2 + 1) % right.size())) > 0) {
                sj2 = (sj2 + 1) % right.size();
                noFail = true;
            }
        }


        ArrayList<Point> env = new ArrayList();
        for (int i = 0; i <= si2; ++i) env.add(left.get(i));
        if (sj1 == 0) {
            for (int i = sj2; i < right.size(); ++i) env.add(right.get(i));
            env.add(right.get(0));
        } else
            for (int i = sj2; i <= sj1; ++i) env.add(right.get(i));
        if (si1 != 0) for (int i = si1; i < left.size(); ++i) env.add(left.get(i));

        return env;
    }

    public void draw(ArrayList<Point> env, GraphicsContext graphicsContext) {
        int index;
        for(index = 0; index < env.size()-1; index++) {
            graphicsContext.strokeLine(env.get(index).x, env.get(index).y, env.get(index+1).x, env.get(index+1).y);
        }
        graphicsContext.strokeLine(env.get(index).x, env.get(index).y, env.get(0).x, env.get(0).y);
    }
}
