package cb2109.failuremodelling.visualisation;

import cb2109.failuremodelling.modelling.riskmaps.EquationRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.equations.Equation;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class Visualizer {

    private final Graphics map;
    private final int intensityColorSteps = 25;
    private final int intensityAlpha = 200;
    private final Color[] intensityColours = new Color[] {
            new Color(0,0,0, 0),
            new Color(255, 10 *intensityColorSteps,0, intensityAlpha),
            new Color(255, 9 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 8 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 7 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 6 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 5 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 4 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 3 * intensityColorSteps,0, intensityAlpha),
            new Color(255, 2 * intensityColorSteps,0, intensityAlpha),
            new Color(255, intensityColorSteps,0, intensityAlpha)
    };

    public Visualizer(Graphics map) {
        this.map = map;
    }

    public void plot(EquationRiskMap rm) {
        for (Equation e : rm.getEquations()) {
            int intensityLevel = stratifyIntensity(e.getIntensity());
            map.setColor(intensityColours[intensityLevel]);
            e.plot(map);
        }
    }

    private int stratifyIntensity(double intensity) {
        if (intensity < 0) {
            return 0;
        } else if (intensity > 1) {
            return 1;
        } else {
            return (int) Math.ceil(intensity * 10);
        }
    }
}
