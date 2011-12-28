package org.berdilaovidiu.app.gui;

import org.berdilaovidiu.app.gui.core.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/23/11
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemList extends JList {
    @Override
    public ListCellRenderer getCellRenderer() {
        return new ListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Item comp = new Item(5, 20);
//                comp.setSize(100, 50);
                comp.setPreferredSize(new Dimension(300, 50));
                return comp;
            }
        };
    }


}
