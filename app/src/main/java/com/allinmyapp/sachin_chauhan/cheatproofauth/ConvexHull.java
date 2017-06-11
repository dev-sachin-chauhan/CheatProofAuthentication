package com.allinmyapp.sachin_chauhan.cheatproofauth;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sachin_Chauhan on 1/24/2016.
 */
public class ConvexHull {

    public ConvexHull() {

    }

    public boolean isWithinHull(ArrayList<Point> points, Point pwd) {
        Point pt;
        points.add(pwd);
        pt = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            if (pt.y > points.get(i).y || ((pt.y == points.get(i).y) && pt.x < points.get(i).x)) {
                pt = points.get(i);
            }
        }
        if (pt.equals(pwd)) {
            return false;
        }
        swap(points, 0, points.indexOf(pt));

        final Point basept = new Point();
        basept.set(points.get(0).x, points.get(0).y);
        Log.i(this.toString(), "isWithinHull " + points.toString());
        for (Point temppt : points) {
            Log.i(this.toString(), "isWithinHull " + divide(temppt, basept));
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                double slope1 = divide(lhs, basept);
                double slope2 = divide(rhs, basept);
                if ((slope1 < 0 && slope2 < 0) || (slope1 > 0 && slope2 > 0)) {
                    if (slope1 > slope2)
                        return 1;
                    else if (slope1 < slope2)
                        return -1;
                    else
                        return 0;
                } else if (slope1 < 0 && slope2 > 0) {
                    return 1;
                } else if (slope1 > 0 && slope2 < 0) {
                    return -1;
                } else if (slope1 == 0 && slope2 != 0) {
                    return -1;
                } else if (slope2 == 0 && slope1 != 0) {
                    return 1;
                }

                return 0;
            }
        });
        Log.i(this.toString(), "isWithinHull " + points.toString());
        points.add(points.size(), basept);
        Point previous = basept;
        ArrayList<Point> respoint = new ArrayList<>();
        respoint.add(basept);
        for (int i = 1; i < points.size() - 1; i++) {
            while (i != points.size() - 1 && !isLeft(previous, points.get(i), points.get(i + 1))) {
                i++;
            }
            respoint.add(points.get(i));
            previous = points.get(i);
        }
        if (respoint.contains(pwd) == true)
            return false;
        else
            return true;
    }

    double divide(Point lhs, Point rhs) {
        if ((lhs.y - rhs.y) == 0)
            return 0;
        if ((lhs.x - rhs.x) == 0)
            return Integer.MAX_VALUE;
        return ((double) (lhs.y - rhs.y) / (lhs.x - rhs.x));
    }

    void swap(ArrayList<Point> points, int ind1, int ind2) {
        Point temppt;
        temppt = points.get(ind1);
        points.set(ind1, points.get(ind2));
        points.set(ind2, temppt);
    }

    public boolean isLeft(Point a, Point b, Point c) {
        return ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)) > 0;
    }

}
