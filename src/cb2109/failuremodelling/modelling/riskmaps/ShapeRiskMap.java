package cb2109.failuremodelling.modelling.riskmaps;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.riskmaps.riskshapes.CircleRiskShape;
import cb2109.failuremodelling.modelling.riskmaps.riskshapes.RiskShape;
import cb2109.failuremodelling.modelling.riskmaps.riskshapes.NoRiskShape;
import cb2109.failuremodelling.modelling.riskmaps.riskshapes.RectangleRiskShape;
import cb2109.failuremodelling.visualisation.RiskColorModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class ShapeRiskMap implements RiskMap {

    private ArrayList<RiskShape> riskShapes;

    public ShapeRiskMap() {
        riskShapes = new ArrayList<>();
    }

    public void addNewCircle(Point center, double radius, double intensity, double level) {
        riskShapes.add(new CircleRiskShape(center, radius, intensity, level));
    }

    public void addNewRectangle(Point topLeftCorner, Point bottomRightCorner, double intensity, double level) {
        riskShapes.add(new RectangleRiskShape(topLeftCorner, bottomRightCorner, intensity, level));
    }

    @Override
    public double getRiskFor(Point p) {
        RiskShape currentRiskShape = new NoRiskShape();
        for (RiskShape riskShape : riskShapes) {
            if (riskShape.contains(p)) {
                if (riskShape.getLevel() > currentRiskShape.getLevel()) {
                    currentRiskShape = riskShape;
                }
            }
        }
        return currentRiskShape.getIntensity();
    }

    @Override
    public double getRiskFor(Line l) {
        RiskShape currentRiskShape = new NoRiskShape();
        for (RiskShape riskShape : riskShapes) {
            if (riskShape.contains(l)) {
                if (riskShape.getLevel() > currentRiskShape.getLevel()) {
                    currentRiskShape = riskShape;
                }
            }
        }
        return currentRiskShape.getIntensity();
    }

    @Override
    public void plot(Graphics map, RiskColorModel colorModel) {
        for (RiskShape e : riskShapes) {
            map.setColor(colorModel.getColor(e.getIntensity()));
            e.plot(map);
        }
    }


}
