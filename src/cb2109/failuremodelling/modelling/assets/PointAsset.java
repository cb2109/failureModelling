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
        return m.getRiskFor(location);
    }

    @Override
    public void plot(Graphics g) {
        // the oval is drawn from the top left not the center like the point we have
        g.fillOval(location.x - 5, location.y - 5, 10, 10);
    }

    public Point getLocation() {
        return location;
    }
}
