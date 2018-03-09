package Visitor;

import form.Canvas;
import form.Circle;
import form.Diamond;
import form.Line;
import form.Poligon;
import form.Rectangle;
import form.Square;
import form.Triangle;

public interface Visitor {
    void visit(Canvas v);
    void visit(Circle v);
    void visit(Diamond v);
    void visit(Line v);
    void visit(Poligon v);
    void visit(Rectangle v);
    void visit(Square v);
    void visit(Triangle v);
}