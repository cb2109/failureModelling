package cb2109.failuremodelling.visualisation;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 06/04/2018
 */
public class RiskColorModel {

    private final int intensityColorSteps = 25;
    private final int intensityAlpha = 150;
    private final int numberOfIntensitySteps = 10;
    private final Color[] riskMapColors = new Color[numberOfIntensitySteps];
    private final Color[] assetColors = new Color[numberOfIntensitySteps];
    private ColorMode cm = ColorMode.ASSET;

    public RiskColorModel() {
        riskMapColors[0] = Color.WHITE;
        assetColors[0] = Color.WHITE;
        for (int i = 1; i < numberOfIntensitySteps; i++) {
            riskMapColors[i] = new Color(255, (numberOfIntensitySteps - i) * intensityColorSteps, 0, intensityAlpha);
            assetColors[i] = new Color(255, (numberOfIntensitySteps - i) * intensityColorSteps, 0);
        }
    }
    public Color getColor(double intensity) {
        int intensityLevel = stratifyIntensity(intensity);
        return cm == ColorMode.ASSET ? assetColors[intensityLevel] : riskMapColors[intensityLevel];
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

    public void setColorMode(ColorMode cm) {
        this.cm = cm;
    }
}
