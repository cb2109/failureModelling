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

        private final double[] leftLine;
        private final double[] topLine;
        private final double[] rightLine;
        private final double[] bottomLine;


        RectangleEquation(Point topLeftCorner, Point bottomRightCorner, double intensity, double level) {
            super(intensity, level);
            this.topLeftCorner = topLeftCorner;
            this.bottomRightCorner = bottomRightCorner;
            leftLine = calculateLineEquation(topLeftCorner, new Point(topLeftCorner.x, bottomRightCorner.y));
            topLine = calculateLineEquation(topLeftCorner, new Point(topLeftCorner.x, bottomRightCorner.y));
            rightLine = calculateLineEquation(topLeftCorner, new Point(topLeftCorner.x, bottomRightCorner.y));
            bottomLine = calculateLineEquation(topLeftCorner, new Point(topLeftCorner.x, bottomRightCorner.y));

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
            double[] lineEq = calculateLineEquation(l.p1, l.p2);
            double[][] squareEdges = new double[][] {
                    leftLine,
                    topLine,
                    rightLine,
                    bottomLine
            };
            for (double[] squareEdge : squareEdges) {
                double[] intersectPoint = calculateIntersection(lineEq, squareEdge);
                // check that the intersection point is within the bounds of the square
                if (topLeftCorner.x <= intersectPoint[0] && intersectPoint[0] <= bottomRightCorner.x
                        && topLeftCorner.y <= intersectPoint[1] && intersectPoint[1] <= bottomRightCorner.y) {
                    return true;
                }
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

        private double[] calculateIntersection(double[] l1, double[] l2) {
            // find out the point where m1x + c1 = m2x + c2
            // m1x + c1 - c2 = m2x
            double cDiff = l1[1] - l2[1];
            // c1 - c2 = (m2 - m1)x
            double mDiff = l2[0] - l1[0];
            // x = (c1 - c2) / (m2 - m1)
            double x = cDiff / mDiff;
            // plug calculated x into l1 to get y
            double y = l1[0] * x + l1[1];
            return new double[] {x, y};
        }
    }
}
