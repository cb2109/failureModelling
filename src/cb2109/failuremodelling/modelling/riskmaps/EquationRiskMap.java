package cb2109.failuremodelling.modelling.riskmaps;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.riskmaps.equations.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class EquationRiskMap implements RiskMap {

    private ArrayList<Equation> equations;

    public EquationRiskMap() {
        equations = new ArrayList<>();
    }

    public void addNewCircle(Point center, double radius, double intensity, double level) {
        equations.add(new CircleEquation(center, radius, intensity, level));
    }

    public void addNewRectangle(Point topLeftCorner, Point bottomRightCorner, double intensity, double level) {
        equations.add(new RectangleEquation(topLeftCorner, bottomRightCorner, intensity, level));
    }

    @Override
    public double getRiskFor(Point p) {
        Equation currentEquation = new NoEquation();
        for (Equation equation : equations) {
            if (equation.contains(p)) {
                if (equation.getLevel() > currentEquation.getLevel()) {
                    currentEquation = equation;
                }
            }
        }
        return currentEquation.getIntensity();
    }

    @Override
    public double getRiskFor(Line l) {
        Equation currentEquation = new NoEquation();
        for (Equation equation : equations) {
            if (equation.contains(l)) {
                if (equation.getLevel() > currentEquation.getLevel()) {
                    currentEquation = equation;
                }
            }
        }
        return currentEquation.getIntensity();
    }

}
