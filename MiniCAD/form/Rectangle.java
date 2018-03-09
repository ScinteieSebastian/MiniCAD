package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Rectangle extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected static final int ARG_5 = 5;
    protected static final int ARG_6 = 6;
    protected static final int ARG_7 = 7;
    protected static final int ARG_8 = 8;
    protected final Point leftCoordinate;
    protected final int dimHeight;
    protected final int dimLenght;

    public Rectangle(final ArrayList<String> list) {
      //transmit culorile constructorului super clasei si salvez in leftCoordinate
        //coordonata varfului si dimensiunile inaltimii si latimii 
        super(list.get(ARG_5), list.get(ARG_6), list.get(ARG_7), list.get(ARG_8));
        this.leftCoordinate = new Point(
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))
                );
        this.dimHeight = Integer.parseInt(list.get(ARG_3));
        this.dimLenght = Integer.parseInt(list.get(ARG_4));
    }
    public final Point getPoint() {
        return this.leftCoordinate;
    }
    public final int getDimHeight() {
        return this.dimHeight;
    }
    public final int getDimLenght() {
        return this.dimLenght;
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }
}
