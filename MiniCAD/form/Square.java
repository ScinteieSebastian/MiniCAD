package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Square extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected static final int ARG_6 = 6;
    protected static final int ARG_7 = 7;
    protected final Point leftCoordonate;
    protected final int dimSide;

    public Square(final ArrayList<String> list) {
        super(list.get(ARG_4), list.get(ARG_5), list.get(ARG_6), list.get(ARG_7));
        leftCoordonate = new Point(
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))
                );
        this.dimSide = Integer.parseInt(list.get(ARG_3));
    }
    public final Point getPoint() {
        return this.leftCoordonate;
    }
    public final int getDimSide() {
        return this.dimSide;
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }
}
