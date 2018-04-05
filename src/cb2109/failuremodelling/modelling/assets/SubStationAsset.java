package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.riskmapcombiner.CombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmapcombiner.DefaultCombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;

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

    public CombinedRiskMap combineRiskMaps(RiskMap m1, RiskMap m2) {
        return new DefaultCombinedRiskMap(m1, m2);
    }
}
