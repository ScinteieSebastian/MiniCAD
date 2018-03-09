package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Diamond extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected static final int ARG_6 = 6;
    protected static final int ARG_7 = 7;
    protected static final int ARG_8 = 8;
    protected final Point centre;
    protected final int dimDiagO;
    protected final int dimDiagV;
    protected final Point[] points = new Point[ARG_4];

    public Diamond(final ArrayList<String> list) {

        super(list.get(ARG_5), list.get(ARG_6), list.get(ARG_7), list.get(ARG_8));
        this.centre = new Point(
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))
                );
        this.dimDiagO = Integer.parseInt(list.get(ARG_3));
        this.dimDiagV = Integer.parseInt(list.get(ARG_4));
        //atribuim valori varfurilor rombului in functie de diagonale si centru
        points[0] = new Point(
                centre.getX(), centre.getY() + this.dimDiagV / 2
                );
        points[1] = new Point(
                centre.getX() + this.dimDiagO / 2, centre.getY()
                );
        points[2] = new Point(
                centre.getX(), centre.getY() - this.dimDiagV / 2
                );
        points[ARG_3] = new Point(
                centre.getX() - this.dimDiagO / 2, centre.getY()
                );
    }
    public final Point getPoints(final int index) {
        return this.points[index];
    }
    public final Point getCentre() {
        return this.centre;
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }
}
