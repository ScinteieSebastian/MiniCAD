package form;

import java.util.ArrayList;

import Visitable.Visitable;
import Visitor.Visitor;
//extinde Shape pentru a folosi construtorul si menbri acestei clase
//si implementeaza visitable pentru a putea suprascrie metoda accept
public class Canvas extends Shape implements Visitable {
    protected static final int ARG_3 = 3;
    protected static final int ARG_4 = 4;
    protected final int height;
    protected final int width;

    public Canvas(final ArrayList<String> list) {

        super(list.get(ARG_3), list.get(ARG_4));
        this.height = Integer.parseInt(list.get(1));
        this.width   = Integer.parseInt(list.get(2));

    }
    public final int getHeight() {
        return this.height;
    }
    public final int getWidth() {
        return this.width;
    }
    public final void accept(final Visitor visit) {
        visit.visit(this);
    }

}
