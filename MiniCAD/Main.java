


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import FactoryShape.Factory;
import Visitor.VisitorDraw;
import form.Shape;


public class Main {
    public static ArrayList<String> newArrayInst() {
        return new ArrayList<String>();
    }

    public static void main(final String[] args) throws IOException {

        ArrayList<String> inputSplit;
        VisitorDraw vis = new VisitorDraw();
        Scanner scan = new Scanner(new File(args[0]));
        int n  = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n; i++) {
            String s = scan.nextLine();
            String[] sir = s.split(" ");
            inputSplit = newArrayInst();
            for (String a : sir) {
                inputSplit.add(a);
            }
            Shape shape = Factory.INSTANCE.createForm(inputSplit);
            shape.accept(vis);
        }
        ImageIO.write(vis.getBuffered(), "PNG", new File("drawing.png"));
        scan.close();
    }
}
