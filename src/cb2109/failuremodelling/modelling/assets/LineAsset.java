package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public abstract class LineAsset implements Asset {

    private final Line line;

    public LineAsset(Line l) {
        this.line = l;
    }

    public double calculateRisk(RiskMap m) {
        return m.getRiskFor(line);
    }

    @Override
    public void plot(Graphics g) {
        g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
    }

}
