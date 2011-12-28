package org.berdilaovidiu.app.gui;

import org.berdilaovidiu.app.gui.core.TextItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/23/11
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomItemList extends JComponent implements ItemsListener, Scrollable{
    private PanelItemsModel model;

    public CustomItemList(PanelItemsModel model) {
        this.model = model;
        model.addListener(this);
    }

    public Dimension getPreferredScrollableViewportSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onNewItem(TextItem item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
