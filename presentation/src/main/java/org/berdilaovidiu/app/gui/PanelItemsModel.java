package org.berdilaovidiu.app.gui;

import org.berdilaovidiu.app.gui.core.TextItem;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/9/11
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PanelItemsModel {
    private List<TextItem> list;
    private List<ItemsListener> listeners;

    public PanelItemsModel() {
        list = new ArrayList<TextItem>();
        listeners = new ArrayList<ItemsListener>();
    }

    public void addListener(ItemsListener listener){
        listeners.add(listener);
    }

    public List<TextItem> getItems(){
        return list;
    }

    public void addTextItem(TextItem item){
        list.add(item);
        for(ItemsListener listener: listeners){
            listener.onNewItem(item);
        }
    }

}
