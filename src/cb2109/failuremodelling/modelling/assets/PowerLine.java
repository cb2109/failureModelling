package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;
import cb2109.failuremodelling.modelling.riskmaps.riskmapcombiner.CombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.riskmapcombiner.MultiplierCombinedRiskMap;

import java.util.Collection;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class PowerLine extends LineAsset {

    public PowerLine(Line l) {
        super(l);
    }

    public RiskMap multiplyRiskMap(RiskMap m) {
        return m;
    }

    public CombinedRiskMap combineRiskMaps(Collection<RiskMap> riskMaps) {
        return new MultiplierCombinedRiskMap(riskMaps);
    }

    public String getName() {
        return "Power Line";
    }
}
