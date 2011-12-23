package org.berdilaovidiu.app.gui;

import net.miginfocom.swing.MigLayout;
import org.berdilaovidiu.app.gui.core.TextItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/9/11
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application extends JFrame{
    public PanelItems panelItems;
    private JPanel panelMenu;
    private JPanel panelDetails;

    public Application() throws HeadlessException {
        panelItems = new PanelItems(new PanelItemsModel());
        panelMenu = new JPanel();
        panelDetails= new JPanel();

        this.setLayout(new MigLayout("debug, fill"));
        this.add(panelItems, "grow, span 1 2");
        this.add(panelMenu, "grow, wrap");
        this.add(panelDetails, "grow");
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final Application frame = new Application();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setVisible(true);

                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.panelItems.getModel().addTextItem(new TextItem());
                    }
                });
                timer.start();
            }
        });
    }
}
