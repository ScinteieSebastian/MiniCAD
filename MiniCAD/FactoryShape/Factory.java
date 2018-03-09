package FactoryShape;

import java.util.ArrayList;

import form.Canvas;
import form.Circle;
import form.Diamond;
import form.Line;
import form.Poligon;
import form.Rectangle;
import form.Shape;
import form.Square;
import form.Triangle;
//implementare Factory Pattern
public final class Factory implements IFactory {
    //implementare Singleton Pattern, astfel aceasta clasa nu va mai fi instantiata in alta parte
    public static final Factory INSTANCE = new Factory();
    private Factory() { }
    @Override
    public Shape createForm(final ArrayList<String> list) {
        //in functie de ce tip primim vom returna acea figura
        switch (list.get(0)) {
                case "CANVAS" :
                    return new Canvas(list);
                case "LINE" :
                    return new Line(list);
                case "SQUARE" :
                    return new Square(list);
                case "RECTANGLE" :
                    return new Rectangle(list);
                case "DIAMOND" :
                    return new Diamond(list);
                case "CIRCLE" :
                    return new Circle(list);
                case "TRIANGLE" :
                    return new Triangle(list);
                case "POLYGON" :
                    return new Poligon(list);
                default :
                    return null;
            }
        }
    }