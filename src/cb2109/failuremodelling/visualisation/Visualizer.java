package cb2109.failuremodelling.visualisation;

import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;
import java.util.Collection;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class Visualizer {

    private final Graphics map;

    public Visualizer(Graphics map) {
        this.map = map;
    }
    
    public void plot(Collection<Asset> assets, RiskMap rm) {
        RiskColorModel cm = new RiskColorModel();
        cm.setColorMode(ColorMode.RISKMAP);
        rm.plot(map, cm);

        cm.setColorMode(ColorMode.ASSET);
        for (Asset a : assets) {
            double risk = a.calculateRisk(rm);
            map.setColor(cm.getColor(risk));
            a.plot(map);
        }
    }


}
