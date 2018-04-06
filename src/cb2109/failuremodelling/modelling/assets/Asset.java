package cb2109.failuremodelling.modelling.assets;

import cb2109.failuremodelling.modelling.riskmaps.RiskMap;

import java.awt.*;
import java.util.Collection;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public interface Asset {
    double calculateRisk(RiskMap m);

    RiskMap multiplyRiskMap(RiskMap m);

    RiskMap combineRiskMaps(Collection<RiskMap> riskMaps);

    void plot(Graphics map);

    String getName();
}
