package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.riskmapcombiner.CombinedRiskMap;
import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public interface Asset {
    double calculateRisk(RiskMap m);

    RiskMap multiplyRiskMap(RiskMap m);

    CombinedRiskMap combineRiskMaps(RiskMap m1, RiskMap m2);
}
