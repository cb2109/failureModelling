package cb2109.failuremodelling.modelling.riskmaps;

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
    public double getRiskAt(Point p) {
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

    private interface Equation {
        boolean contains(Point p);

        double getLevel();

        double getIntensity();
    }

    /*
     * The base level on our Map. At level 0 and with 0 intensity, this indicates that there is no risk from this
     * particular "threat"
     */
    private class NoEquation implements Equation {

        @Override
        public boolean contains(Point p) {
            return true;
        }

        @Override
        public double getLevel() {
            return 0;
        }

        @Override
        public double getIntensity() {
            return 0;
        }
    }

    private abstract class ShapeEquation implements Equation {
        private final double intensity;
        private final double level;

        ShapeEquation(double intensity, double level) {
            this.intensity = intensity;
            this.level = level;
        }

        @Override
        public double getLevel() {
            return this.level;
        }

        @Override
        public double getIntensity() {
            return this.intensity;
        }
    }

    private class CircleEquation extends ShapeEquation {
        private Point center;
        private final double radius;

        CircleEquation(Point center, double radius, double intensity, double level) {
            super(intensity, level);
            this.center = center;
            this.radius = radius;
        }

        @Override
        public boolean contains(Point p) {
            double xDist = center.x - p.x;
            double yDist = center.y - p.y;
            return Math.hypot(xDist, yDist) <= radius;
        }
    }

    private class RectangleEquation extends ShapeEquation {
        private final Point topLeftCorner;
        private final Point bottomRightCorner;

        RectangleEquation(Point topLeftCorner, Point bottomRightCorner, double intensity, double level) {
            super(intensity, level);
            this.topLeftCorner = topLeftCorner;
            this.bottomRightCorner = bottomRightCorner;
        }

        @Override
        public boolean contains(Point p) {
            return topLeftCorner.x <= p.x && p.x <= bottomRightCorner.x &&
                topLeftCorner.y <= p.y && p.y <= bottomRightCorner.y;
        }
    }
}
