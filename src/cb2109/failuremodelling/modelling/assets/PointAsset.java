package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public abstract class PointAsset implements Asset{


    private final Point location;

    public PointAsset(Point location) {
        this.location = location;
    }

    public double calculateRisk(RiskMap m) {
        return m.getRiskAt(location);
    }

}
