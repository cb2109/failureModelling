package cb2109.failuremodelling.modelling;

import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.assets.PowerLine;
import cb2109.failuremodelling.modelling.riskmaps.ShapeRiskMap;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class LineAssetTest {

    @Test
    public void testLineIntersectionWithSquare() {
        ShapeRiskMap map = new ShapeRiskMap();
        map.addNewRectangle(new Point(10, 10), new Point(20, 20), 1, 1);

        Asset intersectingLine = new PowerLine(new Line(new Point(5, 5), new Point(15, 15)));
        Asset reversedLine = new PowerLine(new Line(new Point(15, 15), new Point(5, 5)));
        Asset throughLine = new PowerLine(new Line(new Point(5, 5), new Point(30, 30)));
        Asset topLine = new PowerLine(new Line(new Point(5, 10), new Point(25, 10)));
        Asset leftLine = new PowerLine(new Line(new Point(10, 5), new Point(10, 25)));
        Asset bottomLine = new PowerLine(new Line(new Point(5, 20), new Point(25, 20)));
        Asset rightLine = new PowerLine(new Line(new Point(20, 5), new Point(20, 25)));

        Asset outsideLine = new PowerLine(new Line(new Point(25, 25), new Point(30, 30)));
        Asset outsideLine2 = new PowerLine(new Line(new Point(4, 5), new Point(4, 25)));


        Assert.assertEquals("Intersecting line failed", 1, intersectingLine.calculateRisk(map), 0);
        Assert.assertEquals("Reversed intersecting line failed", 1, reversedLine.calculateRisk(map), 0);
        Assert.assertEquals("Through intersecting line failed", 1, throughLine.calculateRisk(map), 0);

        Assert.assertEquals("Top line failed", 1, topLine.calculateRisk(map), 0);
        Assert.assertEquals("Left line failed", 1, leftLine.calculateRisk(map), 0);
        Assert.assertEquals("Bottom line failed", 1, bottomLine.calculateRisk(map), 0);
        Assert.assertEquals("Right line failed", 1, rightLine.calculateRisk(map), 0);

        Assert.assertEquals("Outside line intersected (incorrectly)", 0, outsideLine.calculateRisk(map), 0);
        Assert.assertEquals("Outside line2 intersected (incorrectly)", 0, outsideLine2.calculateRisk(map), 0);
    }

    @Test
    public void testLineIntersectionWithCircle() {

        ShapeRiskMap map = new ShapeRiskMap();
        map.addNewCircle(new Point(20, 20), 5, 1, 1);

        Asset intersectingLine = new PowerLine(new Line(new Point(5, 5), new Point(20, 20)));
        Asset tangentalLine = new PowerLine(new Line(new Point(15, 15), new Point(15, 25)));

        Asset noWhereNearLine = new PowerLine(new Line(new Point(0, 0), new Point(10, 10)));
        Asset nearTangentLine = new PowerLine(new Line(new Point(15, 17), new Point(17, 15)));

        Assert.assertEquals("Intersecting line failed", 1, intersectingLine.calculateRisk(map), 0);
        Assert.assertEquals("Tangental line failed", 1, tangentalLine.calculateRisk(map), 0);

        Assert.assertEquals("No where near line intersected (incorrectly)", 0, noWhereNearLine.calculateRisk(map), 0);
        Assert.assertEquals("Nearly tangental line intersected (incorrectly)", 0, nearTangentLine.calculateRisk(map), 0);


    }
}
