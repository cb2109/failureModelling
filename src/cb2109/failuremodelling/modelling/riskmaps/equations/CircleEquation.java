package cb2109.failuremodelling.modelling.riskmaps.equations;

import cb2109.failuremodelling.modelling.Line;

import java.awt.*;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class CircleEquation extends ShapeEquation {
    private Point center;
    private final double radius;

    public CircleEquation(Point center, double radius, double intensity, double level) {
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
        // The following equation calculated intersection of a circle radius r and center (0, 0)
        // because the center is 0, 0 we have to adjust the points
        int x1 = l.p1.x - center.x;
        int y1 = l.p1.y - center.y;
        int x2 = l.p2.x - center.x;
        int y2 = l.p2.y - center.y;
        double dx = x2 - x1;
        double dy = y2 - y1;
        // this should be square rooted, but we always square it
        double dr2 = dx * dx + dy * dy;
        if (dr2 == 0) {
            // 0 size line, so just check to see if p1 is inside
            return contains(l.p1);
        }
        double determinant = x1 * y2 - x2 * y1;

        double discriminant = radius * radius * dr2 - determinant * determinant;
        // if the discriminant is < 0 then there is no intersection
        if (discriminant < 0) {
            return false;
        }
        double discriminantSqrt = Math.sqrt(discriminant);
        double determinantDy = determinant * dy;
        double determinantDx = determinant * dx;
        int ySign = dy >= 0 ? 1 : -1;
        // point 1
        double xInt1 = (determinantDy + ySign * dx * discriminantSqrt) / dr2;
        double yInt1 = (-1 * determinantDx + Math.abs(dy) * discriminantSqrt) / dr2;

        // point 2
        double xInt2 = (determinantDy - ySign * dx * discriminantSqrt) / dr2;
        double yInt2 = (-1 * determinantDx - Math.abs(dy) * discriminantSqrt) / dr2;

        // readjust the coordinates back to where the circle is actually positioned
        double realXInt1 = xInt1 + center.x;
        double realYInt1 = yInt1 + center.y;
        double realXInt2 = xInt2 + center.x;
        double realYInt2 = yInt2 + center.y;

        int maxLineX = Math.max(l.p1.x, l.p2.x);
        int minLineX = Math.min(l.p1.x, l.p2.x);
        int maxLineY = Math.max(l.p1.y, l.p2.y);
        int minLineY = Math.min(l.p1.y, l.p2.y);
        // if point 1 or point 2 are contained then it should work
        return minLineX <= realXInt1 && realXInt1 <= maxLineX
                && minLineY <= realYInt1 && realYInt1 <= maxLineY
                ||
                minLineX <= realXInt2 && realXInt2 <= maxLineX
                        && minLineY <= realYInt2 && realYInt2 <= maxLineY;
    }

    @Override
    public void plot(Graphics g) {
        int roundedRadius = (int) Math.round(radius);
        int size = roundedRadius * 2;
        g.fillOval(center.x - roundedRadius, center.y - roundedRadius, size, size);
    }
}
