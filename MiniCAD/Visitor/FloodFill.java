package Visitor;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import form.Point;

// algoritmul de floodfill care porneste dintr -un punct din interiorul figurii
// este implementat iterativ
public final class FloodFill {
    public static final FloodFill INSTANCE = new FloodFill();
    private FloodFill() { }

    public void floodFill(final BufferedImage img, final int x, final int y,
            final int edgeColor, final int interiorColor) {

        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point p = queue.remove();
            if (p.getX() < img.getHeight() && p.getY() < img.getWidth()
                    && p.getX() >= 0 && p.getY() >= 0) {
                if (img.getRGB(p.getY(), p.getX()) != edgeColor
                        && img.getRGB(p.getY(), p.getX()) != interiorColor) {

                    img.setRGB(p.getY(), p.getX(), interiorColor);
                    queue.add(new Point(p.getX() - 1, p.getY()));
                    queue.add(new Point(p.getX() + 1, p.getY()));
                    queue.add(new Point(p.getX(), p.getY() - 1));
                    queue.add(new Point(p.getX(), p.getY() + 1));
                }
            }
        }
        return;
    }
}