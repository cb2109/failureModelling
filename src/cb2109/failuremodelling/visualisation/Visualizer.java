package cb2109.failuremodelling.visualisation;

import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;
import java.util.ArrayList;
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
        Collection<RiskMap> rms = new ArrayList<>();
        rms.add(rm);
        plot(assets, rms);
    }
    
    public void plot(Collection<Asset> assets, Collection<RiskMap> rms) {
        RiskColorModel cm = new RiskColorModel();
        cm.setColorMode(ColorMode.RISKMAP);
        for (RiskMap rm : rms) {
            rm.plot(map, cm);
        }

        cm.setColorMode(ColorMode.ASSET);
        for (Asset a : assets) {
            RiskMap rm = a.combineRiskMaps(rms);
            double risk = a.calculateRisk(rm);
            map.setColor(cm.getColor(risk));
            a.plot(map);
        }
    }


}
