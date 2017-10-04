package com.mnenmenth.straightcurve;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class RenderPane extends JPanel {

    public ArrayList<Line2D> lines = new ArrayList<Line2D>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for (Line2D line : lines) {
            g2d.draw(line);
        }
        repaint();
    }
}
