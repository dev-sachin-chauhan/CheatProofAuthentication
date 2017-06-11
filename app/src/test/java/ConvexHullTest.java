import android.graphics.Point;

import com.allinmyapp.sachin_chauhan.cheatproofauth.ConvexHull;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

public class ConvexHullTest extends TestCase {

    ArrayList<Point> testPoints = null;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testPoints = new ArrayList<>();
        testPoints.add(new Point(10,212));
        testPoints.add(new Point(500,20));
        testPoints.add(new Point(20,150));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        testPoints.clear();
    }

    @Test
    public void test_ConvexHullmethodforsuccess() {
        Point testPoint = new Point(25,100);
        assertTrue("Error with point within hull", new ConvexHull().isWithinHull(testPoints, testPoint));
    }

    @Test
    public void test_ConvexHullmethodforfail() {
        Point testPoint = new Point(300,500);
        assertFalse("Error with point outside hull",new ConvexHull().isWithinHull(testPoints,testPoint));
    }
}