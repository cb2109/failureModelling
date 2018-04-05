package cb2109.failuremodelling.modelling.riskmaps;

import cb2109.failuremodelling.modelling.Line;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public interface RiskMap {
    double getRiskFor(Point p);
    double getRiskFor(Line l);
}
