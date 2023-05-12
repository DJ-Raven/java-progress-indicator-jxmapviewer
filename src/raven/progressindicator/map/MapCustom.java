package raven.progressindicator.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;

public class MapCustom extends JXMapViewer {

    private final Image image;

    public MapCustom() {
        image = new ImageIcon(getClass().getResource("/raven/progressindicator/map/pin.png")).getImage();
    }

    public void init() {
        setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("", "https://b.tile.openstreetmap.fr/hot/")));
        setAddressLocation(new GeoPosition(11.636895, 104.883817));
        setZoom(12);
        // Init Event
        MouseInputListener mm = new PanMouseInputListener(this);
        addMouseListener(mm);
        addMouseMotionListener(mm);
        addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = getWidth() / 2 - 12;
        int y = getHeight() / 2 - 24;
        g2.drawImage(image, x, y, null);
        Area area = new Area(new Rectangle.Double(0, 0, getWidth(), getHeight()));
        area.subtract(new Area(new RoundRectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10, 20, 20)));
        g2.setColor(new Color(255, 255, 255));
        g2.fill(area);
        g2.dispose();
        
    }
}
