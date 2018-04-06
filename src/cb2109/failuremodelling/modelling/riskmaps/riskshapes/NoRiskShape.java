package cb2109.failuremodelling.modelling.riskmaps.riskshapes;

import cb2109.failuremodelling.modelling.Line;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 *
 * The base level on our Map. At level 0 and with 0 intensity, this indicates that there is no risk from this
 * particular "threat"
 */
public class NoRiskShape implements RiskShape {

    @Override
    public boolean contains(Point p) {
        return true;
    }

    @Override
    public boolean contains(Line l) {
        return true;
    }

    @Override
    public double getLevel() {
        return 0;
    }

    @Override
    public double getIntensity() {
        return 0;
    }

    @Override
    public void plot(Graphics g) { }


}
