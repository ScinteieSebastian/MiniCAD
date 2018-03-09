package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Poligon extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected final int numbPoints;
    protected Point[] points;
    protected int k = 0;

    public Poligon(final ArrayList<String>  list) {
        //transmit culorile constructorului super clasei si salvez in points
        //coordonatele varfurilor poligonului
        super(list.get(2 * Integer.parseInt(list.get(1)) + 2),
                list.get(2 * Integer.parseInt(list.get(1)) + ARG_3),
                list.get(2 * Integer.parseInt(list.get(1)) + ARG_4),
                list.get(2 * Integer.parseInt(list.get(1)) + ARG_5));
        this.numbPoints = Integer.parseInt(list.get(1));
        this.points = new Point[this.numbPoints];
        for (int i = 2; i < 2 + 2 * this.numbPoints; i += 2) {
            int x = Integer.parseInt(list.get(i));
            int y = Integer.parseInt(list.get(i + 1));
            this.points[k] = new Point(x, y);
            k++;
        }
    }
    public final Point getPoints(final int index) {
        return this.points[index];
    }
    public final int getNumPoints() {
        return this.numbPoints;
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }
}
