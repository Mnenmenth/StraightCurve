package com.mnenmenth.straightcurve;

import com.mnenmenth.libgraph.Coordinate;
import com.mnenmenth.libgraph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class StraightCurve {

    public static void main(String[] args) {
        JFrame frame = new JFrame("StraightCurve");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getRootPane().registerKeyboardAction(e -> {
            frame.dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        RenderPane r = new RenderPane();
        frame.add(r);
        frame.setVisible(true);

        Graph g = new Graph(frame.getSize(), 100, -100, 100, -100);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                r.lines.clear();
                int x = 0;
                int y = 100;
                int numLines = 100;
                int posinc = (int) g.p2c(e.getPoint()).getX();
                int inc;
                if (posinc > 0) {
                    inc = posinc;
                } else if (posinc < 0) {
                    inc = -posinc;
                } else {
                    inc = 1;
                }
                
                while (x <= numLines) {
                    //Quadrant I
                    Point f = g.c2p(new Coordinate(0, y));
                    Point s = g.c2p(new Coordinate(x, 0));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant II
                    f = g.c2p(new Coordinate(0, y));
                    s = g.c2p(new Coordinate(-x, 0));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant III
                    f = g.c2p(new Coordinate(0, -y));
                    s = g.c2p(new Coordinate(-x, 0));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant IV
                    f = g.c2p(new Coordinate(0, -y));
                    s = g.c2p(new Coordinate(x, 0));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));


                    //Quadrant I
                    f = g.c2p(new Coordinate(numLines, y));
                    s = g.c2p(new Coordinate(x, numLines));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant II
                    f = g.c2p(new Coordinate(-numLines, y));
                    s = g.c2p(new Coordinate(-x, numLines));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant III
                    f = g.c2p(new Coordinate(-numLines, -y));
                    s = g.c2p(new Coordinate(-x, -numLines));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    //Quadrant IV
                    f = g.c2p(new Coordinate(numLines, -y));
                    s = g.c2p(new Coordinate(x, -numLines));
                    r.lines.add(new Line2D.Double(f.x, f.y, s.x, s.y));

                    x += inc;
                    y -= inc;
                }
            }
        };
        frame.addMouseListener(adapter);
        frame.addMouseMotionListener(adapter);


    }

}
