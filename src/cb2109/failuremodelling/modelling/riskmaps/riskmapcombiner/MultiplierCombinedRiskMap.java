package cb2109.failuremodelling.modelling.riskmaps.riskmapcombiner;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;
import cb2109.failuremodelling.visualisation.RiskColorModel;

import java.awt.*;
import java.util.Collection;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class MultiplierCombinedRiskMap implements CombinedRiskMap {

    private final Collection<RiskMap> riskMaps;

    public MultiplierCombinedRiskMap(Collection<RiskMap> riskMaps) {
        this.riskMaps = riskMaps;
    }

    @Override
    public double getRiskFor(Point p) {
        double totalRisk = 1;
        for (RiskMap m : riskMaps) {
            totalRisk *= m.getRiskFor(p);
        }
        return totalRisk;
    }

    @Override
    public double getRiskFor(Line l) {
        double totalRisk = 1;
        for (RiskMap m : riskMaps) {
            totalRisk *= m.getRiskFor(l);
        }
        return totalRisk;
    }

    @Override
    public void plot(Graphics map, RiskColorModel colorModel) {
        for (RiskMap m : riskMaps) {
            m.plot(map, colorModel);
        }
    }
}
