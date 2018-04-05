package cb2109.failuremodelling.modelling.riskmaps.equations;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public abstract class ShapeEquation implements Equation {
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
