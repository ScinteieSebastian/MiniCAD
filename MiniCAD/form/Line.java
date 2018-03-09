package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Line extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected static final int ARG_6 = 6;
    protected Point[] points;
//folosim doi constructori, unul pentru initializare(cand primeste o lista)
//si unul pentru cand capetele si culoarea se cunosc
    public Line(final ArrayList<String> list) {
        super(list.get(ARG_5), list.get(ARG_6));
        points = new Point[2];
        points[0] = new Point(
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))
                );
        points[1] = new Point(
                Integer.parseInt(list.get(ARG_3)), Integer.parseInt(list.get(ARG_4))
                );
    }
    public Line(
            final int startX, final int startY,
            final int finalX, final int finalY,
            final int edgeColor) {
        super(edgeColor);
        points = new Point[2];
        points[0] = new Point(startX, startY);
        points[1] = new Point(finalX, finalY);
    }
    public final Point getPoints(final int index) {
        return this.points[index];
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }
}
