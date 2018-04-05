package cb2109.failuremodelling.modelling.riskmaps.equations;

import cb2109.failuremodelling.modelling.Line;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public interface Equation {
    boolean contains(Point p);

    boolean contains(Line l);

    double getLevel();

    double getIntensity();

    void plot(Graphics g);


}
