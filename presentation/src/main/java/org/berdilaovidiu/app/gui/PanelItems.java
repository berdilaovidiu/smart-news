package org.berdilaovidiu.app.gui;

import net.miginfocom.swing.MigLayout;
import org.berdilaovidiu.app.gui.core.Item;
import org.berdilaovidiu.app.gui.core.TextItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/9/11
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PanelItems extends JPanel implements ItemsListener{
    private PanelItemsModel model;
    private JPanel searchPanel;
    private JPanel listPanel;

    private JButton buttonSearch;
    private JTextField fieldSearch;

    public PanelItems(PanelItemsModel model) {
        this.model = model;
        initComponents();
        initLayouts();
        initActions();

        model.addListener(this);
    }

    private void initComponents() {
        searchPanel = new JPanel();
        listPanel = new JPanel();
        buttonSearch = new JButton("Search");
        fieldSearch = new JTextField("Type word here");
    }

    private void initLayouts() {
        searchPanel.setLayout(new MigLayout("wrap 2"));
        searchPanel.add(fieldSearch);
        searchPanel.add(buttonSearch);


        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(listPanel);

        this.setLayout(new MigLayout("wrap 1, fill"));
        this.add(searchPanel, "growx");
        this.add(scrollPane, "grow");
    }

    private void initActions() {


    }

    public PanelItemsModel getModel() {
        return model;
    }


    public void onNewItem(TextItem item) {
        Item comp = new Item(5, 20);
        comp.setSize(100, 50);
        comp.setPreferredSize(new Dimension(100, 50));
        listPanel.add(comp);
        listPanel.validate();
    }
}
