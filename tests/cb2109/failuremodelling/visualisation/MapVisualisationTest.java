package cb2109.failuremodelling.visualisation;

import cb2109.failuremodelling.modelling.riskmaps.EquationRiskMap;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 05/04/2018
 */
public class MapVisualisationTest {

    private String resourcePath = "tests/resources/";
    private String outputPath = "tests/output/";

    @Test
    public void drawSimpleMap() throws IOException {
        BufferedImage img = ImageIO.read(new File(resourcePath + "woodedMap.jpg"));

        Graphics g = img.getGraphics();
        Visualizer v = new Visualizer(g);


        EquationRiskMap rm = new EquationRiskMap();
        rm.addNewCircle(new Point(500, 500), 200, 0.9, 1);
        rm.addNewRectangle(new Point(250, 250), new Point(400, 400), 0.5, 1);
        v.plot(rm);

        ImageIO.write(img, "jpg", new File(outputPath + "woodedMap.jpg"));

    }
}
