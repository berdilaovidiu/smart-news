package org.berdilaovidiu.app.gui.core;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/9/11
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item extends JPanel {
    private int strokeWidth;
    private int arcWidth;
    private Color lineColor;
    private Color backgroundColor;

    public Item(int strokeWidth, int arcWidth) {
        super();
        this.strokeWidth = strokeWidth;
        this.arcWidth = arcWidth;
        lineColor = Color.ORANGE;
        backgroundColor = Color.BLACK;
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(lineColor);
        g2d.drawRoundRect(strokeWidth/2, strokeWidth/2, getWidth() - strokeWidth, getHeight() - strokeWidth, arcWidth,arcWidth);
        g2d.setPaint(new GradientPaint( strokeWidth, strokeWidth, Color.GRAY, getWidth(), getHeight(),backgroundColor));
        g2d.fillRoundRect(strokeWidth/2, strokeWidth/2, getWidth() - strokeWidth, getHeight() - strokeWidth, arcWidth, arcWidth);
        g2d.dispose();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new Item(10, 20));
                frame.setContentPane(panel);
                frame.setSize(300, 100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
