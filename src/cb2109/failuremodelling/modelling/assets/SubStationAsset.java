package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.riskmaps.riskmapcombiner.CombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.riskmapcombiner.MultiplierCombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;
import java.util.Collection;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class SubStationAsset extends PointAsset {
    public SubStationAsset(Point location) {
        super(location);
    }

    public RiskMap multiplyRiskMap(RiskMap m) {
        return m;
    }

    public CombinedRiskMap combineRiskMaps(Collection<RiskMap> riskMaps) {
        return new MultiplierCombinedRiskMap(riskMaps);
    }

    public String getName() {
        return "Sub station";
    }
}
