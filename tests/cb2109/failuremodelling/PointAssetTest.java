package cb2109.failuremodelling;

import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.assets.SubStationAsset;
import cb2109.failuremodelling.modelling.riskmaps.EquationRiskMap;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class PointAssetTest {

    @Test
    public void circleIntersectionTest() {
        Asset assetTopLeft = new SubStationAsset(new Point(0, 0));
        Asset assetBottomRight = new SubStationAsset(new Point(4, 4));
        Asset assetTopCenter = new SubStationAsset(new Point(2, 0));
        Asset assetMiddleCircle = new SubStationAsset(new Point(2, 2));

        EquationRiskMap map = new EquationRiskMap();
        map.addNewCircle(new Point(2, 2), 2, 1, 1);

        Assert.assertEquals("Top left point is within bounds", 0, assetTopLeft.calculateRisk(map), 0);
        Assert.assertEquals("Bottom right point is within bounds", 0, assetBottomRight.calculateRisk(map), 0);
        Assert.assertEquals("Top center point is NOT within bounds", 0, assetTopCenter.calculateRisk(map), 1);
        Assert.assertEquals("Center of the circle is NOT within bounds", 0, assetMiddleCircle.calculateRisk(map), 1);

    }

    @Test
    public void squareIntersectionTest() {
        Asset assetAbove = new SubStationAsset(new Point(2, 0));
        Asset assetAboveInside = new SubStationAsset(new Point(2, 1));
        Asset assetLeft = new SubStationAsset(new Point(0, 2));
        Asset assetLeftInside = new SubStationAsset(new Point(1, 2));
        Asset assetBelow = new SubStationAsset(new Point(2, 4));
        Asset assetBelowInside = new SubStationAsset(new Point(2, 3));
        Asset assetRight = new SubStationAsset(new Point(4, 2));
        Asset assetRightInside= new SubStationAsset(new Point(3, 2));
        Asset fullyInside = new SubStationAsset(new Point(2, 2));

        EquationRiskMap map = new EquationRiskMap();
        map.addNewRectangle(new Point(1, 1), new Point(3,3), 1, 1);

        Assert.assertEquals("Above point is within bounds", 0, assetAbove.calculateRisk(map), 0);
        Assert.assertEquals("Above inside point is NOT within bounds", 0, assetAboveInside.calculateRisk(map), 1);
        Assert.assertEquals("Left point is within bounds", 0, assetLeft.calculateRisk(map), 0);
        Assert.assertEquals("Left inside point is NOT within bounds", 0, assetLeftInside.calculateRisk(map), 1);
        Assert.assertEquals("Below point is within bounds", 0, assetBelow.calculateRisk(map), 0);
        Assert.assertEquals("Below inside point is NOT within bounds", 0, assetBelowInside.calculateRisk(map), 1);
        Assert.assertEquals("Right point is within bounds", 0, assetRight.calculateRisk(map), 0);
        Assert.assertEquals("Right inside is NOT within bounds", 0, assetRightInside.calculateRisk(map), 1);
        Assert.assertEquals("Fully inside is NOT within bounds", 0, fullyInside.calculateRisk(map), 1);
    }

}