package cb2109.failuremodelling.modelling.riskmaps;

import cb2109.failuremodelling.modelling.Line;

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

    private interface Equation {
        boolean contains(Point p);

        boolean contains(Line l);

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
        public boolean contains(Line l) {
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

        @Override
        public boolean contains(Line l) {
            return false;
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

        /*
         * Calculate the intersect between each edge of the square and the line
         * If the line intersects anywhere between the topLeftCorner and bottomRightCorner
         * then the line is within the bounds of the square
         */
        @Override
        public boolean contains(Line l) {
            int maxLineX = Math.max(l.p1.x, l.p2.x);
            int minLineX = Math.min(l.p1.x, l.p2.x);
            int maxLineY = Math.max(l.p1.y, l.p2.y);
            int minLineY = Math.min(l.p1.y, l.p2.y);

            double[] lineEq = calculateLineEquation(l.p1, l.p2);
            double leftIntersectionX = xIntersection(lineEq, topLeftCorner.y);
            // the line intersects the left side of the square within bounds
            if (topLeftCorner.x <= leftIntersectionX && leftIntersectionX <= bottomRightCorner.x
                    && minLineX <= leftIntersectionX && leftIntersectionX <= maxLineX) {
                return true;
            }

            double rightIntersectionX = xIntersection(lineEq, bottomRightCorner.y);
            // the line intersects the right side of the square within bounds
            if (topLeftCorner.x <= rightIntersectionX && rightIntersectionX <= bottomRightCorner.x
                    && minLineX <= rightIntersectionX && rightIntersectionX <= maxLineX) {
                return true;
            }

            double topIntersectionY = yIntersection(lineEq, topLeftCorner.x);
            // the line intersects the top side of the square within bounds
            if (topLeftCorner.y <= topIntersectionY && topIntersectionY <= bottomRightCorner.y
                    && minLineY <= topIntersectionY && topIntersectionY <= maxLineY) {
                return true;
            }

            double bottomIntersectionY = yIntersection(lineEq, bottomRightCorner.x);
            // the line intersects the bottom side of the square within bounds
            if (topLeftCorner.y <= bottomIntersectionY && bottomIntersectionY <= bottomRightCorner.y
                    && minLineY <= bottomIntersectionY && bottomIntersectionY <= maxLineY) {
                return true;
            }

            return false;

        }

        /*
         * solve the equation for y = mx + c for p1 and p2
         * so that we have an equation for a line
         */
        private double[] calculateLineEquation(Point p1, Point p2) {
            double m = ((double) p2.y - p1.y) / (p2.x - p1.x);
            double c = p1.y - (m * p1.x);
            return new double[] {m, c};
        }

        private double xIntersection(double[] line, int yPoint) {
            double xIntersection;
            if (Double.isInfinite(line[0])) {
                // if equation is Infinite * x then we have a vertical line that intersects x at yPoint
                xIntersection = (double) yPoint;
            } else {
                // find out the point where yPoint = mx + c
                xIntersection = (yPoint - line[1]) / line[0];
            }
            return xIntersection;
        }

        private double yIntersection(double[] line, int xPoint) {
            if (Double.isInfinite(line[0])) {
                // if equation is Infinite * x then we have a vertical line that never intersects Y
                return Double.POSITIVE_INFINITY;
            } else {
                // find out the point where y = m * xPoint + c
                return (line[0] * xPoint) + line[1];
            }
        }
    }
}
