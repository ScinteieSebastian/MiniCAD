package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Triangle extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected static final int ARG_6 = 6;
    protected static final int ARG_7 = 7;
    protected static final int ARG_8 = 8;
    protected static final int ARG_9 = 9;
    protected static final int ARG_10 = 10;
    protected Point[] points;

    public Triangle(final ArrayList<String> list) {
        //atribui culorile folosid constructorul superclasei si salvez in points
        //varfurile triunghiului
        super(list.get(ARG_7), list.get(ARG_8), list.get(ARG_9), list.get(ARG_10));
        points = new Point[ARG_3];
        points[0] = new Point(
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))
                );
        points[1] = new Point(
                Integer.parseInt(list.get(ARG_3)), Integer.parseInt(list.get(ARG_4))
                );
        points[2] = new Point(
                Integer.parseInt(list.get(ARG_5)), Integer.parseInt(list.get(ARG_6))
                );
    }
    public final Point getPoints(final int index) {
        return this.points[index];
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }

}
