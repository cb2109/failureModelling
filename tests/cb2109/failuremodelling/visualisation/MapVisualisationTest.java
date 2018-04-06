package cb2109.failuremodelling.visualisation;

import cb2109.failuremodelling.modelling.Line;
import cb2109.failuremodelling.modelling.assets.Asset;
import cb2109.failuremodelling.modelling.assets.PowerLine;
import cb2109.failuremodelling.modelling.assets.SubStationAsset;
import cb2109.failuremodelling.modelling.riskmaps.ShapeRiskMap;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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


        ShapeRiskMap rm = new ShapeRiskMap();
        rm.addNewCircle(new Point(500, 500), 100, 0.9, 1);
        rm.addNewRectangle(new Point(250, 250), new Point(400, 400), 0.5, 1);


        Collection<Asset> assets = new ArrayList<>();
        SubStationAsset s1 = new SubStationAsset(new Point(400, 400));
        SubStationAsset s2 = new SubStationAsset(new Point(800, 800));
        assets.add(new PowerLine(new Line(s1.getLocation(), s2.getLocation())));
        assets.add(s1);
        assets.add(s2);
        v.plot(assets, rm);

        ImageIO.write(img, "jpg", new File(outputPath + "woodedMap.jpg"));

    }
}
