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
    public void firstTest() {
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

}