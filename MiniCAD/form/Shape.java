package form;

import java.awt.Color;

import Visitor.Visitor;

public abstract class Shape {
    protected static final int ARG_3 = 3;
    protected static final int ARG_5 = 5;
    protected static final int ARG_7 = 7;
    protected static final int ARG_16 = 16;
    protected int edgeColor;
    protected int edgeOpacity;
    protected int interiorColor;
    protected int interiorOpacity;
    private Color colorEdge;
    private Color colorInterior;
//supraincarc constructorului acestei clase pentru a evita codul duplicat
//fiecare dintre acestia fiind folosit pentru initializarea claselor care extind aceasta clasa
    public Shape(final int edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Shape(final String interiorColor, final String interiorOpacity) {
        this.colorInterior = ColorHex.getColor(interiorColor, interiorOpacity);
        this.interiorColor = colorInterior.getRGB();
        this.edgeColor = colorInterior.getRGB();
    }

    public Shape(
            final String edgeColor, final String edgeOpacity,
            final String interiorColor, final String interiorOpacity) {

        this.colorEdge = ColorHex.getColor(edgeColor, edgeOpacity);
        this.edgeOpacity = Integer.parseInt(edgeOpacity);
        this.edgeColor = colorEdge.getRGB();

        this.colorInterior = ColorHex.getColor(interiorColor, interiorOpacity);
        this.interiorColor = colorInterior.getRGB();
        this.interiorOpacity = Integer.parseInt(interiorOpacity);
    }
//clasa interna ColorHex converteste culoare primita ca parametru(String) in Color
    static class ColorHex {
        static Color getColor(final String colorHEX, final String opacity) {
            return new Color(
                    Integer.valueOf(colorHEX.substring(1, ARG_3), ARG_16),
                    Integer.valueOf(colorHEX.substring(ARG_3, ARG_5), ARG_16),
                    Integer.valueOf(colorHEX.substring(ARG_5, ARG_7), ARG_16),
                    Integer.parseInt(opacity));
        }
    }
    public final int getEdgeCol() {
        return this.edgeColor;
    }
    public final int getEdgeOpacity() {
        return this.edgeOpacity;
    }
    public final int getIntCol() {
        return this.interiorColor;
    }
    public final int getIntOpacity() {
        return this.interiorOpacity;
    }
    public abstract void accept(Visitor visit);
}
