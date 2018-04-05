package cb2109.failuremodelling;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.assets.PowerLine;
import cb2109.failuremodelling.modelling.riskmaps.EquationRiskMap;
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
        EquationRiskMap map = new EquationRiskMap();
        map.addNewRectangle(new Point(10, 10), new Point(20, 20), 1, 1);

        Asset intersectingLine = new PowerLine(new Line(new Point(5, 5), new Point(15, 15)));
        Asset reversedLine = new PowerLine(new Line(new Point(15, 15), new Point(5, 5)));
        Asset topLine = new PowerLine(new Line(new Point(5, 10), new Point(25, 10)));
        Asset leftLine = new PowerLine(new Line(new Point(10, 5), new Point(10, 25)));
        Asset bottomLine = new PowerLine(new Line(new Point(5, 20), new Point(25, 20)));
        Asset rightLine = new PowerLine(new Line(new Point(20, 5), new Point(20, 25)));

        Asset outsideLine = new PowerLine(new Line(new Point(25, 25), new Point(30, 30)));
        Asset outsideLine2 = new PowerLine(new Line(new Point(4, 5), new Point(4, 25)));


        Assert.assertEquals("Intersecting line failed", 0, intersectingLine.calculateRisk(map), 1);
        Assert.assertEquals("Reversed intersecting line failed", 0, reversedLine.calculateRisk(map), 1);
        Assert.assertEquals("Top line failed", 0, topLine.calculateRisk(map), 1);
        Assert.assertEquals("Left line failed", 0, leftLine.calculateRisk(map), 1);
        Assert.assertEquals("Bottom line failed", 0, bottomLine.calculateRisk(map), 1);
        Assert.assertEquals("Right line failed", 0, rightLine.calculateRisk(map), 1);

        Assert.assertEquals("Outside line intersected (incorrectly)", 0, outsideLine.calculateRisk(map), 0);
        Assert.assertEquals("Outside line2 intersected (incorrectly)", 0, outsideLine2.calculateRisk(map), 0);
    }
}
