package form;
//salveaza coordonatele unui pixel
public class Point {
    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    public final int getX() {
        return x;
    }
    public final int getY() {
        return y;
    }
}
