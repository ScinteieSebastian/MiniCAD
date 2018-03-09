package Visitor;

import java.awt.image.BufferedImage;

import form.Canvas;
import form.Circle;
import form.Diamond;
import form.Line;
import form.Poligon;
import form.Rectangle;
import form.Square;
import form.Triangle;

public class VisitorDraw implements Visitor {
    private BufferedImage img;
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_6 = 6;
    protected static final int ARG_10 = 10;
//impementarea metodei visit pentru fiecare forma
//pentru square, canvas si rectangele nu am folosit floodfill pentru umplere ci 2 for -uri
    @Override
    public final void visit(final Canvas c) {
        img = new BufferedImage(
                c.getWidth(), c.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        for (int x = 0; x < c.getWidth(); x++) {
            for (int y = 0; y < c.getHeight(); y++) {
                img.setRGB(x, y, c.getIntCol());
            }
        }
    }
//am folosit algoritmul lui Bresenham pentru desenarea unui cerc
//si am verificat mereu daca punctele sunt in Canvas
    @Override
    public final void visit(final Circle c) {
        int x = 0;
        int y = c.getRad();
        int d = ARG_3 - 2 * c.getRad();
        while (y >= x) {
            this.drawCircle(c, x, y);
            x++;
            if (d > 0) {
                y--;
                d = d + ARG_4 * (x - y) + ARG_10;
            } else {
                d = d + ARG_4 * x + ARG_6;
            }
            this.drawCircle(c, x, y);
        }
        int xc = c.getPoints().getX(),
                yc = c.getPoints().getY(),
                edgeCol = c.getEdgeCol(),
                intCol = c.getIntCol();
        FloodFill.INSTANCE.floodFill(img, yc, xc, edgeCol, intCol);
    }
// m -am folosit de Line pentru al desena si de floodfill pentru a colora interiorul
    @Override
    public final void visit(final Diamond d) {
        final int n = ARG_3;
        DrawLine line = new DrawLine();
        for (int i = 0; i < n; i++) {
            line.draw(new Line(
                    d.getPoints(i).getX(), d.getPoints(i).getY(),
                    d.getPoints(i + 1).getX(), d.getPoints(i + 1).getY(),
                    d.getEdgeCol()
                    ));
        }
        line.draw(new Line(
                d.getPoints(ARG_3).getX(), d.getPoints(ARG_3).getY(),
                d.getPoints(0).getX(), d.getPoints(0).getY(),
                d.getEdgeCol()
                ));
        int xc = d.getCentre().getX(),
                yc = d.getCentre().getY(),
                edgeCol = d.getEdgeCol(),
                intCol = d.getIntCol();
        FloodFill.INSTANCE.floodFill(img, yc, xc, edgeCol, intCol);
    }
    @Override
    public final void visit(final Line l) {
        DrawLine line = new DrawLine();
        line.draw(l);
    }
//m am folosit de Line pentru al desena si de floodfill pentru a umple interiorul
    @Override
    public final void visit(final Poligon p) {
        final int n = p.getNumPoints();
        DrawLine line  = new DrawLine();
        for (int i = 0; i < n - 1; i++) {
            line.draw(new Line(
                    p.getPoints(i).getX(), p.getPoints(i).getY(),
                    p.getPoints(i + 1).getX(), p.getPoints(i + 1).getY(),
                    p.getEdgeCol()
                    ));
        }
        line.draw(new Line(
                p.getPoints(n - 1).getX(), p.getPoints(n - 1).getY(),
                p.getPoints(0).getX(), p.getPoints(0).getY(),
                p.getEdgeCol()
                ));
        int cx = 0,
                cy = 0,
                edgeCol = p.getEdgeCol(),
                intCol = p.getIntCol();
        for (int i = 0; i < p.getNumPoints(); i++) {
            cx += p.getPoints(i).getX();
            cy += p.getPoints(i).getY();
        }
        cx = cx / p.getNumPoints();
        cy = cy / p.getNumPoints();

        FloodFill.INSTANCE.floodFill(img, cy, cx, edgeCol, intCol);
    }
// m -am folosit de Line pentru al desena si de 2 for -uri pentru a coloea interiorul
    @Override
    public final void visit(final Rectangle r) {
        DrawLine line = new DrawLine();
        int x = r.getPoint().getX(),
                y = r.getPoint().getY(),
                dimLength = r.getDimLenght(),
                dimHeight = r.getDimHeight(),
                edgeCol = r.getEdgeCol(),
                intCol = r.getIntCol();
        line.draw(new Line(
                x, y,
                x + dimLength - 1, y,
                edgeCol
                ));
        line.draw(new Line(
                x, y,
                x, y + dimHeight - 1,
                edgeCol
                ));
        line.draw(new Line(
                x + dimLength - 1, y + dimHeight - 1,
                x, y + dimHeight - 1,
                edgeCol
                ));
        line.draw(new Line(
                x + dimLength - 1, y,
                x + dimLength - 1, y + dimHeight - 1,
                edgeCol
                ));
        floodFillShape(img, x, y, dimLength, dimHeight, intCol);
    }
//m -am folosit de Line pentru al desena si de 2 for -uri pentru a colora interiorul
    @Override
    public final void visit(final Square v) {
        DrawLine line = new DrawLine();
        int x = v.getPoint().getX(),
                y = v.getPoint().getY(),
                dimSide = v.getDimSide(),
                edgeCol = v.getEdgeCol(),
                intCol = v.getIntCol();
        line.draw(new Line(
                x, y,
                x + dimSide - 1, y,
                edgeCol
                ));
        line.draw(new Line(
                x, y,
                x, y + dimSide - 1,
                edgeCol
                ));
        line.draw(new Line(
                x + dimSide - 1, y,
                x + dimSide - 1, y + dimSide - 1,
                edgeCol
                ));
        line.draw(new Line(
                x + dimSide - 1, y + dimSide - 1,
                x, y + dimSide - 1,
                edgeCol
                ));
        floodFillShape(img, x, y, dimSide, dimSide, intCol);
    }
//Line + floodfill pentru a obtine imaginea
    @Override
    public final void visit(final Triangle t) {
        DrawLine line = new DrawLine();
        int centreX = (t.getPoints(0).getX() + t.getPoints(1).getX()
                + t.getPoints(2).getX()) / ARG_3,
                centreY = (t.getPoints(0).getY() + t.getPoints(1).getY()
                        + t.getPoints(2).getY()) / ARG_3,
                edgeCol = t.getEdgeCol(),
                intCol = t.getIntCol();

        for (int i = 0; i < 2; i++) {
            line.draw(new Line(
                    t.getPoints(i).getX(), t.getPoints(i).getY(),
                    t.getPoints(i + 1).getX(), t.getPoints(i + 1).getY(),
                    edgeCol
                    ));
        }
        line.draw(new Line(
                t.getPoints(2).getX(), t.getPoints(2).getY(),
                t.getPoints(0).getX(), t.getPoints(0).getY(),
                edgeCol
                ));
        FloodFill.INSTANCE.floodFill(img, centreY, centreX, edgeCol, intCol);
    }

    public final BufferedImage getBuffered() {
        return this.img;
    }
//metoda care verifica daca un punct este in Canvas sau nu
    private boolean inCanvas(final BufferedImage img, final int x, final int y) {
        if (x >= 0 && x < img.getWidth()
                && y >= 0 && y < img.getHeight()) {
            return true;
        }
        return false;
    }
//metoda care face coloreaza interiorul unei forme prin 2 for -uri
    private void floodFillShape(final BufferedImage img, final int x, final int y,
            final int dimWidth, final int dimHeight, final int color) {

        for (int i = x + 1; i < x + dimWidth - 1; i++) {
            for (int j = y + 1; j < y + dimHeight - 1; j++) {
                if (inCanvas(img, i, j)) {
                    img.setRGB(i, j, color);
                }
            }
        }
    }
//metoda care coloreaza punctele de pe margine pentru a forma un cerc si verifica
//daca acele puncte sunt in Canvas
    private void drawCircle(final Circle c, final int x, final int y) {
        int xc = c.getPoints().getX(), yc = c.getPoints().getY();
        if (inCanvas(img, xc + x, yc + y)) {
            img.setRGB(xc + x, yc + y, c.getEdgeCol());
        }
        if (inCanvas(img, xc - x, yc + y)) {
            img.setRGB(xc - x, yc + y, c.getEdgeCol());
        }
        if (inCanvas(img, xc + x, yc - y)) {
            img.setRGB(xc + x, yc - y, c.getEdgeCol());
        }
        if (inCanvas(img, xc - x, yc - y)) {
            img.setRGB(xc - x, yc - y, c.getEdgeCol());
        }
        if (inCanvas(img, xc + y, yc + x)) {
            img.setRGB(xc + y, yc + x, c.getEdgeCol());
        }
        if (inCanvas(img, xc - y, yc + x)) {
            img.setRGB(xc - y, yc + x, c.getEdgeCol());
        }
        if (inCanvas(img, xc + y, yc - x)) {
            img.setRGB(xc + y, yc - x, c.getEdgeCol());
        }
        if (inCanvas(img, xc  - y, yc - x)) {
            img.setRGB(xc - y, yc - x, c.getEdgeCol());
        }
    }
//clasa interna pentru desenarea unei linii prin algoritmul lui Bresenham
//am folosit o clasa interna pentru a evita codul duplicat
    private class DrawLine {
        private void draw(final Line l) {
            int x = l.getPoints(1).getX();
            int y = l.getPoints(1).getY();
            int w = l.getPoints(0).getX() - l.getPoints(1).getX();
            int h = l.getPoints(0).getY() - l.getPoints(1).getY();
            int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;

            if (w < 0) {
                dx1 = -1;
            } else if (w > 0) {
                dx1 = 1;
            }
            if (h < 0) {
                dy1 = -1;
            } else if (h > 0) {
                dy1 = 1;
            }
            if (w < 0) {
                dx2 = -1;
            } else if (w > 0) {
                dx2 = 1;
            }
            int longest = Math.abs(w);
            int shortest = Math.abs(h);
            if (!(longest > shortest)) {
                longest = Math.abs(h);
                shortest = Math.abs(w);
                if (h < 0) {
                    dy2 = -1;
                } else if (h > 0) {
                    dy2 = 1;
                }
                dx2 = 0;
            }
            int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            if (inCanvas(img, x, y)) {
                img.setRGB(x, y, l.getEdgeCol());
            }

            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
        }
    }
}